
package com.begcode.report.console.designer;

import com.begcode.report.console.AbstractReportBasicController;
import com.begcode.report.console.common.R;
import com.begcode.report.console.exception.ReportDesignException;
import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.dataset.Field;
import com.begcode.report.core.definition.datasource.BuildinDatasource;
import com.begcode.report.core.definition.datasource.DataType;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.utils.ProcedureUtils;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping(value = "/datasource")
public class DatasourceController extends AbstractReportBasicController {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 加载内置数据源
     *
     * @param
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/loadBuildinDatasources", method = RequestMethod.GET)
    public R loadBuildinDatasources() throws ServletException, IOException {
        List<String> datasources = new ArrayList<>();
        for (BuildinDatasource datasource : Utils.getBuildinDatasources()) {
            datasources.add(datasource.name());
        }

        return R.ok().success(datasources);
    }

    /**
     * 加载Bean方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/loadMethods", method = RequestMethod.GET)
    public R loadMethods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String beanId = req.getParameter("beanId");
        Object obj = applicationContext.getBean(beanId);
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        List<String> result = new ArrayList<String>();
        for (Method method : methods) {
            Class<?>[] types = method.getParameterTypes();
            if (types.length != 3) {
                continue;
            }
            Class<?> typeClass1 = types[0];
            Class<?> typeClass2 = types[1];
            Class<?> typeClass3 = types[2];
            if (!String.class.isAssignableFrom(typeClass1)) {
                continue;
            }
            if (!String.class.isAssignableFrom(typeClass2)) {
                continue;
            }
            if (!Map.class.isAssignableFrom(typeClass3)) {
                continue;
            }
            result.add(method.getName());
        }
        return R.ok().success(result);
    }

    /**
     * 获取class字段
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/buildClass", method = RequestMethod.GET)
    public R buildClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clazz = req.getParameter("clazz");
        List<Field> result = new ArrayList<Field>();
        try {
            Class<?> targetClass = Class.forName(clazz);
            PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(targetClass);
            for (PropertyDescriptor pd : propertyDescriptors) {
                String name = pd.getName();
                if ("class".equals(name)) {
                    continue;
                }
                result.add(new Field(name));
            }

            return R.ok().success(result);
        } catch (Exception ex) {
            throw new ReportDesignException(ex);
        }
    }

    @RequestMapping(value = "/buildDatabaseTables", method = RequestMethod.POST)
    public R buildDatabaseTables(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = buildConnection(req);
            DatabaseMetaData metaData = conn.getMetaData();
            String url = metaData.getURL();
            String schema = null;
            if (url.toLowerCase().contains("oracle")) {
                schema = metaData.getUserName();
            }
            List<Map<String, String>> tables = new ArrayList<Map<String, String>>();
            rs = metaData.getTables(conn.getCatalog(), schema, null, new String[]{"TABLE", "VIEW"});
            while (rs.next()) {
                Map<String, String> table = new HashMap<String, String>();
                table.put("name", rs.getString("TABLE_NAME"));
                table.put("type", rs.getString("TABLE_TYPE"));
                tables.add(table);
            }

            return R.ok().success(tables);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeConnection(conn);
        }
    }

    @RequestMapping(value = "/buildFields", method = RequestMethod.POST)
    public R buildFields(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = req.getParameter("sql");
        String parameters = req.getParameter("parameters");
        Connection conn = null;
        final List<Field> fields = new ArrayList<Field>();
        try {
            conn = buildConnection(req);
            Map<String, Object> map = buildParameters(parameters);
            sql = parseSql(sql, map);
            if (ProcedureUtils.isProcedure(sql)) {
                List<Field> fieldsList = ProcedureUtils.procedureColumnsQuery(sql, map, conn);
                fields.addAll(fieldsList);
            } else {
                DataSource dataSource = new SingleConnectionDataSource(conn, false);
                NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
                PreparedStatementCreator statementCreator = getPreparedStatementCreator(sql, new MapSqlParameterSource(map));
                jdbc.getJdbcOperations().execute(statementCreator, new PreparedStatementCallback<Object>() {
                    @Override
                    public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ResultSet rs = null;
                        try {
                            rs = ps.executeQuery();
                            ResultSetMetaData metadata = rs.getMetaData();
                            int columnCount = metadata.getColumnCount();
                            for (int i = 0; i < columnCount; i++) {
                                String columnName = metadata.getColumnLabel(i + 1);
                                fields.add(new Field(columnName));
                            }
                            return null;
                        } finally {
                            JdbcUtils.closeResultSet(rs);
                        }
                    }
                });
            }

            return R.ok().success(fields);
        } catch (Exception ex) {
            throw new ReportDesignException(ex);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    protected PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource) {
        ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
        String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
        Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
        List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
        return pscf.newPreparedStatementCreator(params);
    }

    @RequestMapping(value = "/previewData", method = RequestMethod.POST)
    public R previewData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = req.getParameter("sql");
        String parameters = req.getParameter("parameters");
        Map<String, Object> map = buildParameters(parameters);
        sql = parseSql(sql, map);
        Connection conn = null;
        try {
            conn = buildConnection(req);
            List<Map<String, Object>> list = null;
            if (ProcedureUtils.isProcedure(sql)) {
                list = ProcedureUtils.procedureQuery(sql, map, conn);
            } else {
                DataSource dataSource = new SingleConnectionDataSource(conn, false);
                NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
                list = jdbc.queryForList(sql, map);
            }
            int size = list.size();
            int currentTotal = size;
            if (currentTotal > 500) {
                currentTotal = 500;
            }
            List<Map<String, Object>> ls = new ArrayList<>();
            for (int i = 0; i < currentTotal; i++) {
                ls.add(list.get(i));
            }
            DataResult result = new DataResult();
            List<String> fields = new ArrayList<>();
            if (size > 0) {
                Map<String, Object> item = list.get(0);
                for (String name : item.keySet()) {
                    fields.add(name);
                }
            }
            result.setFields(fields);
            result.setCurrentTotal(currentTotal);
            result.setData(ls);
            result.setTotal(size);

            return R.ok().success(result);
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String parseSql(String sql, Map<String, Object> parameters) {
        sql = sql.trim();
        Context context = new Context(applicationContext, parameters);
        if (sql.startsWith(ExpressionUtils.EXPR_PREFIX) && sql.endsWith(ExpressionUtils.EXPR_SUFFIX)) {
            sql = sql.substring(2, sql.length() - 1);
            Expression expr = ExpressionUtils.parseExpression(sql);
            sql = executeSqlExpr(expr, context);
            return sql;
        } else {
            String sqlForUse = sql;
            Pattern pattern = Pattern.compile("\\$\\{.*?\\}");
            Matcher matcher = pattern.matcher(sqlForUse);
            while (matcher.find()) {
                String substr = matcher.group();
                String sqlExpr = substr.substring(2, substr.length() - 1);
                Expression expr = ExpressionUtils.parseExpression(sqlExpr);
                String result = executeSqlExpr(expr, context);
                sqlForUse = sqlForUse.replace(substr, result);
            }
            Utils.logToConsole("DESIGN SQL:" + sqlForUse);
            return sqlForUse;
        }
    }

    private String executeSqlExpr(Expression sqlExpr, Context context) {
        String sqlForUse = null;
        ExpressionData<?> exprData = sqlExpr.execute(null, null, context);
        if (exprData instanceof ObjectExpressionData) {
            ObjectExpressionData data = (ObjectExpressionData) exprData;
            Object obj = data.getData();
            if (obj != null) {
                String s = obj.toString();
                s = s.replaceAll("\\\\", "");
                sqlForUse = s;
            }
        }
        return sqlForUse;
    }

    @RequestMapping(value = "/testConnection", method = RequestMethod.POST)
    public R testConnection(HttpServletRequest req) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String driver = req.getParameter("driver");
        String url = req.getParameter("url");
        Connection conn = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            map.put("result", true);
        } catch (Exception ex) {
            map.put("error", ex.toString());
            map.put("result", false);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return R.ok().success(map);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> buildParameters(String parameters) throws IOException, JsonParseException, JsonMappingException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(parameters)) {
            return map;
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = mapper.readValue(parameters, ArrayList.class);
        for (Map<String, Object> param : list) {
            String name = param.get("name").toString();
            DataType type = DataType.valueOf(param.get("type").toString());
            String defaultValue = (String) param.get("defaultValue");
            if (defaultValue == null || defaultValue.equals("")) {
                switch (type) {
                    case Boolean:
                        map.put(name, false);
                    case Date:
                        map.put(name, new Date());
                    case Float:
                        map.put(name, new Float(0));
                    case Integer:
                        map.put(name, 0);
                    case String:
                        if (defaultValue != null && defaultValue.equals("")) {
                            map.put(name, "");
                        } else {
                            map.put(name, "null");
                        }
                        break;
                    case List:
                        map.put(name, new ArrayList<Object>());
                }
            } else {
                map.put(name, type.parse(defaultValue));
            }
        }
        return map;
    }

    private Connection buildConnection(HttpServletRequest req) throws Exception {
        String type = req.getParameter("type");
        if (type.equals("jdbc")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String driver = req.getParameter("driver");
            String url = req.getParameter("url");

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } else {
            String name = req.getParameter("name");
            Connection conn = Utils.getBuildinConnection(name);
            if (conn == null) {
                throw new ReportDesignException("Buildin datasource [" + name + "] not exist.");
            }
            return conn;
        }
    }

}
