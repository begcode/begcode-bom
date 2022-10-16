package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.diboot.core.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

import static com.diboot.core.util.ContextHelper.getBean;

public class MybatisUtil {
    private static DbType dbType;
    public static DbType getDatabaseTypeEnum() {
        if (Objects.nonNull(dbType)) {
            return dbType;
        }
        try {
            DataSource dataSource = getBean(DataSource.class);
            dbType = JdbcUtils.getDbType(dataSource.getConnection().getMetaData().getURL());
            return dbType;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("获取数据库类型失败");
        }
    }
}

