package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import tech.jhipster.service.aggregate.Aggregate;
import tech.jhipster.service.aggregate.DateTimeGroupBy;
import tech.jhipster.service.aggregate.GroupByExpress;
import tech.jhipster.service.aggregate.NumberAggregate;

import java.util.List;
import java.util.Objects;

public class AggregateUtil {
    public static void buildAggregate(Aggregate filter, String field, List<String> selects) {
        if (Objects.equals(filter.getCount(), true)) {
            selects.add("count(" + field + ") as " + field + "_count");
        }
    }

    public static void buildAggregate(NumberAggregate filter, String field, List<String> selects) {
        if (Objects.equals(filter.getSum(), true)) {
            selects.add("sum(" + field + ") as " + field + "_sum");
        }
        if (Objects.equals(filter.getAvg(), true)) {
            selects.add("avg(" + field + ") as " + field + "_avg");
        }
        if (Objects.equals(filter.getMin(), true)) {
            selects.add("min(" + field + ") as " + field + "_min");
        }
        if (Objects.equals(filter.getMax(), true)) {
            selects.add("max(" + field + ") as " + field + "_max");
        }
    }

    public static void buildGroupBy(GroupByExpress groupByExpress, String field, List<String> groupBys) {
        if (Objects.equals(groupByExpress.getJoin(), true)) {
            groupBys.add(field);
        }
    }

    public static void buildGroupBy(DateTimeGroupBy groupByExpress, String field, List<String> groupBys, List<String> selects) {
        if (Objects.nonNull(groupByExpress)) {
            DbType databaseTypeEnum = DbType.MYSQL;
            // todo 未能成功获得数据库类型
            boolean isAdded = false;
            if (Objects.equals(groupByExpress.getYear(), true)) {
                String fieldExpress = "";
                switch (databaseTypeEnum) {
                    case MYSQL:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case ORACLE:
                        fieldExpress = "to_char(" + field + ",'yyyy')";
                        break;
                    case SQL_SERVER:
                        fieldExpress = "datepart(year," + field + ")";
                        break;
                    case DB2:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case H2:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case POSTGRE_SQL:
                        fieldExpress = "date_part('year'," + field + ")";
                        break;
                    case HSQL:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case SQLITE:
                        fieldExpress = "strftime('%Y'," + field + ")";
                        break;
                    case DM:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case KINGBASE_ES:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case GAUSS:
                        fieldExpress = "to_char(" + field + ",'yyyy')";
                        break;
                    case GBASE:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case OCEAN_BASE:
                        fieldExpress = "year(" + field + ")";
                        break;
                    case OSCAR:
                        fieldExpress = "year(" + field + ")";
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                selects.add(fieldExpress + " as " + field + "_year");
                groupBys.add(field + "_year");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getMonth(), true)) {
                String fieldExpress = "";
                switch (databaseTypeEnum) {
                    case MYSQL:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case ORACLE:
                        fieldExpress = "to_char(" + field + ",'mm')";
                        break;
                    case SQL_SERVER:
                        fieldExpress = "datepart(month," + field + ")";
                        break;
                    case DB2:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case H2:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case POSTGRE_SQL:
                        fieldExpress = "date_part('month'," + field + ")";
                        break;
                    case HSQL:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case SQLITE:
                        fieldExpress = "strftime('%m'," + field + ")";
                        break;
                    case DM:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case KINGBASE_ES:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case GAUSS:
                        fieldExpress = "to_char(" + field + ",'mm')";
                        break;
                    case GBASE:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case OCEAN_BASE:
                        fieldExpress = "month(" + field + ")";
                        break;
                    case OSCAR:
                        fieldExpress = "month(" + field + ")";
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                selects.add(fieldExpress + " as " + field + "_month");
                groupBys.add(field + "_month");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getDay(), true)) {
                String fieldExpress = "";
                switch (databaseTypeEnum) {
                    case MYSQL:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case ORACLE:
                        fieldExpress = "to_char(" + field + ",'dd')";
                        break;
                    case SQL_SERVER:
                        fieldExpress = "datepart(day," + field + ")";
                        break;
                    case DB2:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case H2:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case POSTGRE_SQL:
                        fieldExpress = "date_part('day'," + field + ")";
                        break;
                    case HSQL:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case SQLITE:
                        fieldExpress = "strftime('%d'," + field + ")";
                        break;
                    case DM:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case KINGBASE_ES:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case GAUSS:
                        fieldExpress = "to_char(" + field + ",'dd')";
                        break;
                    case GBASE:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case OCEAN_BASE:
                        fieldExpress = "day(" + field + ")";
                        break;
                    case OSCAR:
                        fieldExpress = "day(" + field + ")";
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                selects.add(fieldExpress + " as " + field + "_day");
                groupBys.add(field + "_day");
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getHour(), true)) {
                String hourFieldExpress = "";
                switch (databaseTypeEnum) {
                    case MYSQL:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case ORACLE:
                        hourFieldExpress = "to_char(" + field + ",'hh24')";
                        break;
                    case SQL_SERVER:
                        hourFieldExpress = "datepart(hour," + field + ")";
                        break;
                    case DB2:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case H2:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case POSTGRE_SQL:
                        hourFieldExpress = "date_part('hour'," + field + ")";
                        break;
                    case HSQL:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case SQLITE:
                        hourFieldExpress = "strftime('%H'," + field + ")";
                        break;
                    case DM:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case KINGBASE_ES:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case GAUSS:
                        hourFieldExpress = "to_char(" + field + ",'hh24')";
                        break;
                    case GBASE:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case OCEAN_BASE:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    case OSCAR:
                        hourFieldExpress = "hour(" + field + ")";
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                selects.add(hourFieldExpress + " as " + field + "_hour");
                groupBys.add(hourFieldExpress + "_hour");
                isAdded = true;
            }
            if (!isAdded && Objects.equals(groupByExpress.getJoin(), Boolean.TRUE)) {
                groupBys.add(field);
            }
        }
    }
}
