package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import org.apache.commons.lang3.StringUtils;
import tech.jhipster.service.aggregate.Aggregate;
import tech.jhipster.service.aggregate.DateTimeGroupBy;
import tech.jhipster.service.aggregate.GroupByExpress;
import tech.jhipster.service.aggregate.NumberAggregate;

import java.util.List;
import java.util.Objects;

public class AggregateUtil {
    public static void buildAggregate(Aggregate filter, String columnName, String columnAlias, List<String> selects) {
        if (Objects.equals(filter.getCount(), true)) {
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add("count(" + columnName + ") as " + columnAlias + "_count");
            } else {
                String onlyFieldName = columnName.replace("self.", "");
                selects.add("count(" + columnName + ") as " + onlyFieldName + "_count");
            }
        }
    }

    public static void buildAggregate(NumberAggregate filter, String columnName, String columnAlias, List<String> selects) {
        String onlyFieldName = columnName.replace("self.", "");
        if (Objects.equals(filter.getSum(), true)) {
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add("sum(" + columnName + ") as " + columnAlias + "_sum");
            } else {
                selects.add("sum(" + columnName + ") as " + onlyFieldName + "_sum");
            }
        }
        if (Objects.equals(filter.getAvg(), true)) {
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add("avg(" + columnName + ") as " + columnAlias);
            } else {
                selects.add("avg(" + columnName + ") as " + onlyFieldName + "_avg");
            }
        }
        if (Objects.equals(filter.getMin(), true)) {
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add("min(" + columnName + ") as " + columnAlias + "_min");
            } else {
                selects.add("min(" + columnName + ") as " + onlyFieldName + "_min");
            }
        }
        if (Objects.equals(filter.getMax(), true)) {
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add("max(" + columnName + ") as " + columnAlias + "_max");
            } else {
                selects.add("max(" + columnName + ") as " + onlyFieldName + "_max");
            }
        }
    }

    public static void buildGroupBy(GroupByExpress groupByExpress, String columnName, String columnAlias, List<String> groupBys, List<String> selects) {
        if (Objects.equals(groupByExpress.getJoin(), true)) {
            groupBys.add(columnName);
            if (StringUtils.isNotBlank(columnAlias)) {
                selects.add(columnName + " as " + columnAlias);
            } else {
                selects.add(columnName);
            }
        }
    }

    public static void buildGroupBy(DateTimeGroupBy groupByExpress, String columnName, String columnAlias, List<String> groupBys, List<String> selects) {
        if (Objects.nonNull(groupByExpress)) {
            DbType databaseTypeEnum = MybatisUtil.getDatabaseTypeEnum();
            String onlyFieldName = columnName.replace("self.", "");
            // todo 未能成功获得数据库类型
            boolean isAdded = false;
            if (Objects.equals(groupByExpress.getYear(), true)) {
                String fieldExpress = switch (databaseTypeEnum) {
                    case MYSQL, H2, HSQL, DB2, GBASE, OCEAN_BASE, OSCAR, DM, KINGBASE_ES -> "year(" + columnName + ")";
                    case ORACLE, GAUSS -> "to_char(" + columnName + ",'yyyy')";
                    case SQL_SERVER -> "datepart(year," + columnName + ")";
                    case POSTGRE_SQL -> "date_part('year'," + columnName + ")";
                    case SQLITE -> "strftime('%Y'," + columnName + ")";
                    default -> throw new RuntimeException("不支持的数据库类型: " + databaseTypeEnum);
                };
                if (StringUtils.isNotBlank(columnAlias)) {
                    selects.add(fieldExpress + " as " + columnAlias + "_year");
                } else {
                    selects.add(fieldExpress + " as " + onlyFieldName + "_year");
                }
                groupBys.add(onlyFieldName + "_year");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getMonth(), true)) {
                String fieldExpress = switch (databaseTypeEnum) {
                    case MYSQL, H2, HSQL, DB2, GBASE, OCEAN_BASE, OSCAR, DM, KINGBASE_ES -> "month(" + columnName + ")";
                    case ORACLE, GAUSS -> "to_char(" + columnName + ",'mm')";
                    case SQL_SERVER -> "datepart(month," + columnName + ")";
                    case POSTGRE_SQL -> "date_part('month'," + columnName + ")";
                    case SQLITE -> "strftime('%m'," + columnName + ")";
                    default -> throw new RuntimeException("不支持的数据库类型: " + databaseTypeEnum);
                };
                if (StringUtils.isNotBlank(columnAlias)) {
                    selects.add(fieldExpress + " as " + columnAlias + "_month");
                } else {
                    selects.add(fieldExpress + " as " + onlyFieldName + "_month");
                }
                groupBys.add(onlyFieldName + "_month");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getDay(), true)) {
                String fieldExpress = switch (databaseTypeEnum) {
                    case MYSQL, H2, HSQL, DB2, GBASE, OCEAN_BASE, OSCAR, DM, KINGBASE_ES -> "day(" + columnName + ")";
                    case ORACLE, GAUSS -> "to_char(" + columnName + ",'dd')";
                    case SQL_SERVER -> "datepart(day," + columnName + ")";
                    case POSTGRE_SQL -> "date_part('day'," + columnName + ")";
                    case SQLITE -> "strftime('%d'," + columnName + ")";
                    default -> throw new RuntimeException("不支持的数据库类型: " + databaseTypeEnum);
                };
                if (StringUtils.isNotBlank(columnAlias)) {
                    selects.add(fieldExpress + " as " + columnAlias + "_day");
                } else {
                    selects.add(fieldExpress + " as " + onlyFieldName + "_day");
                }
                groupBys.add(onlyFieldName + "_day");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getHour(), true)) {
                String hourFieldExpress = switch (databaseTypeEnum) {
                    case MYSQL, H2, HSQL, DB2, GBASE, OCEAN_BASE, OSCAR, DM, KINGBASE_ES -> "hour(" + columnName + ")";
                    case ORACLE, GAUSS -> "to_char(" + columnName + ",'hh24')";
                    case SQL_SERVER -> "datepart(hour," + columnName + ")";
                    case POSTGRE_SQL -> "date_part('hour'," + columnName + ")";
                    case SQLITE -> "strftime('%H'," + columnName + ")";
                    default -> throw new RuntimeException("不支持的数据库类型: " + databaseTypeEnum);
                };
                if (StringUtils.isNotBlank(columnAlias)) {
                    selects.add(hourFieldExpress + " as " + columnAlias + "_hour");
                } else {
                    selects.add(hourFieldExpress + " as " + onlyFieldName + "_hour");
                }
                groupBys.add(onlyFieldName + "_hour");
                isAdded = true;
            }
            if (!isAdded && Objects.equals(groupByExpress.getJoin(), Boolean.TRUE)) {
                groupBys.add(onlyFieldName);
            }
        }
    }
}
