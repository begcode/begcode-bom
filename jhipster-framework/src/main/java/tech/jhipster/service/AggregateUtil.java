package tech.jhipster.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import tech.jhipster.service.aggregate.Aggregate;
import tech.jhipster.service.aggregate.DateTimeGroupBy;
import tech.jhipster.service.aggregate.GroupByExpress;
import tech.jhipster.service.aggregate.NumberAggregate;

import java.util.List;
import java.util.Objects;

public class AggregateUtil {
    public static void buildAggregate(Aggregate filter, String field, List<Selection<?>> selects, CriteriaBuilder cb, Root<?> root) {
        if (Objects.equals(filter.getCount(), true)) {
            selects.add(cb.countDistinct(root.get(field)).alias(field + "_count"));
        }
    }

    public static void buildAggregate(NumberAggregate filter, String field, List<Selection<?>> selects, CriteriaBuilder cb, Root<?> root) {
        if (Objects.equals(filter.getSum(), true)) {
            selects.add(cb.sum(root.get(field)).alias(field + "_sum"));
        }
        if (Objects.equals(filter.getAvg(), true)) {
            selects.add(cb.avg(root.get(field)).alias(field + "_avg"));
        }
        if (Objects.equals(filter.getMin(), true)) {
            selects.add(cb.min(root.get(field)).alias(field + "_min"));
        }
        if (Objects.equals(filter.getMax(), true)) {
            selects.add(cb.max(root.get(field)).alias(field + "_max"));
        }
    }

    public static void buildGroupBy(GroupByExpress groupByExpress, String field, List<Expression<?>> groupBys, List<Selection<?>> selects, CriteriaBuilder cb, Root<?> root) {
        if (Objects.equals(groupByExpress.getJoin(), true)) {
            groupBys.add(root.get(field));
            selects.add(root.get(field));
        }
    }

    public static void buildGroupBy(DateTimeGroupBy groupByExpress, String field, List<Expression<?>> groupBys, List<Selection<?>> selects, CriteriaBuilder cb, String databaseProductName, Root<?> root) {
        if (Objects.nonNull(groupByExpress)) {
            boolean isAdded = false;
            if (Objects.equals(groupByExpress.getYear(), true)) {
                Expression<Integer> fieldExpress = null;

                switch (databaseProductName.trim().toLowerCase()) {
                    case "mysql":
                    case "mariadb":
                    case "h2":
                    case "db2":
                    case "hsql":
                    case "dm":
                    case "kingbase_es":
                    case "gbase":
                    case "ocean_base":
                    case "oscar":
                        fieldExpress = cb.function("year", Integer.class, cb.literal(field));
                        break;
                    case "oracle":
                    case "gauss":
                        fieldExpress = cb.function("to_char", Integer.class, cb.literal(field), cb.literal("yyyy"));
                        break;
                    case "sql_server":
                        fieldExpress = cb.function("datepart", Integer.class, cb.literal("year"), cb.literal(field));
                        break;
                    case "postgresql":
                        fieldExpress = cb.function("date_part", Integer.class, cb.literal("year"), cb.literal(field));
                        break;
                    case "sqlite":
                        fieldExpress = cb.function("strftime", Integer.class, cb.literal("%Y"), cb.literal(field));
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                fieldExpress.alias(field + "_year");
                selects.add(fieldExpress);
                groupBys.add(fieldExpress);
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getMonth(), true)) {
                Expression<Integer> fieldExpress = null;
                switch (databaseProductName.trim().toLowerCase()) {
                    case "mysql":
                    case "db2":
                    case "h2":
                    case "hsql":
                    case "dm":
                    case "kingbase_es":
                    case "gbase":
                    case "ocean_base":
                    case "oscar":
                        fieldExpress = cb.function("month", Integer.class, cb.literal(field));
                        break;
                    case "oracle":
                    case "gauss":
                        fieldExpress = cb.function("to_char", Integer.class, cb.literal(field), cb.literal("mm"));
                        break;
                    case "sql_server":
                        fieldExpress = cb.function("datepart", Integer.class, cb.literal("month"), cb.literal(field));
                        break;
                    case "postgresql":
                        fieldExpress = cb.function("date_part", Integer.class, cb.literal("month"), cb.literal(field));
                        break;
                    case "sqlite":
                        fieldExpress = cb.function("strftime", Integer.class, cb.literal("%m"), cb.literal(field));
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }

                fieldExpress.alias(field + "_month");
                selects.add(fieldExpress);
                groupBys.add(fieldExpress);
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getDay(), true)) {
                Expression<Integer> fieldExpress = null;
                switch (databaseProductName.trim().toLowerCase()) {
                    case "mysql":
                    case "db2":
                    case "h2":
                    case "hsql":
                    case "dm":
                    case "kingbase_es":
                    case "gbase":
                    case "ocean_base":
                    case "oscar":
                        fieldExpress = cb.function("day", Integer.class, cb.literal(field));
                        break;
                    case "oracle":
                    case "gauss":
                        fieldExpress = cb.function("to_char", Integer.class, cb.literal(field), cb.literal("dd"));
                        break;
                    case "sql_server":
                        fieldExpress = cb.function("datepart", Integer.class, cb.literal("day"), cb.literal(field));
                        break;
                    case "postgresql":
                        fieldExpress = cb.function("date_part", Integer.class, cb.literal("day"), cb.literal(field));
                        break;
                    case "sqlite":
                        fieldExpress = cb.function("strftime", Integer.class, cb.literal("%d"), cb.literal(field));
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                fieldExpress.alias(field + "_day");
                selects.add(fieldExpress);
                groupBys.add(fieldExpress);
                isAdded = true;
            }
            if (Objects.equals(groupByExpress.getHour(), true)) {
                Expression<Integer> fieldExpress = null;
                switch (databaseProductName.trim().toLowerCase()) {
                    case "mysql":
                    case "db2":
                    case "h2":
                    case "hsql":
                    case "dm":
                    case "kingbase_es":
                    case "gbase":
                    case "ocean_base":
                    case "oscar":
                        fieldExpress = cb.function("hour", Integer.class, cb.literal(field));
                        break;
                    case "oracle":
                    case "gauss":
                        fieldExpress = cb.function("to_char", Integer.class, cb.literal(field), cb.literal("hh24"));
                        break;
                    case "sql_server":
                        fieldExpress = cb.function("datepart", Integer.class, cb.literal("hour"), cb.literal(field));
                        break;
                    case "postgresql":
                        fieldExpress = cb.function("date_part", Integer.class, cb.literal("hour"), cb.literal(field));
                        break;
                    case "sqlite":
                        fieldExpress = cb.function("strftime", Integer.class, cb.literal("%H"), cb.literal(field));
                        break;
                    default:
                        throw new RuntimeException("不支持的数据库类型");
                }
                fieldExpress.alias(field + "_hour");
                selects.add(fieldExpress);
                groupBys.add(fieldExpress);
                isAdded = true;
            }
            if (!isAdded && Objects.equals(groupByExpress.getJoin(), Boolean.TRUE)) {
                groupBys.add(root.get(field));
            }
        }
    }
}
