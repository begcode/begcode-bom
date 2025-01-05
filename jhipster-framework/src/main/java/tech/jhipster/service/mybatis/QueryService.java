package tech.jhipster.service.mybatis;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.aggregate.DateTimeGroupBy;
import tech.jhipster.service.aggregate.NumberAggregate;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.RangeFilter;
import tech.jhipster.service.filter.StringFilter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.diboot.core.binding.QueryBuilder.criteriaToWrapper;
import static com.diboot.core.binding.QueryBuilder.criteriaToWrapperNoJoin;
import static tech.jhipster.service.mybatis.AggregateUtil.buildAggregate;
import static tech.jhipster.service.mybatis.AggregateUtil.buildGroupBy;

public interface QueryService<ENTITY> {
    default <X> Consumer<QueryWrapper<ENTITY>> buildSpecification(Filter<X> filter, String field) {
        return buildSpecification(filter, field, false);
    }

    default <X> Consumer<QueryWrapper<ENTITY>> buildSpecification(Filter<X> filter, String field, Boolean useOr) {
        return (
                queryWrapper -> {
                    boolean noFilter = true;
                    if (filter.getEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.eq(field, filter.getEquals()));
                        } else {
                            queryWrapper.eq(field, filter.getEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notIn(field, filter.getNotIn()));
                        } else {
                            queryWrapper.notIn(field, filter.getNotIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ne(field, filter.getNotEquals()));
                        } else {
                            queryWrapper.ne(field, filter.getNotEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getSpecified() != null) {
                        if (filter.getSpecified()) {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNotNull(field));
                            } else {
                                queryWrapper.isNotNull(field);
                            }
                        } else {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNull(field));
                            } else {
                                queryWrapper.isNull(field);
                            }
                        }
                        noFilter = false;
                    }
                    if (noFilter){
                        // 防止为空（）
                        if (useOr) {
                            queryWrapper.or(q -> q.eq("1", 1));
                        } else {
                            queryWrapper.eq("1", 1);
                        }
                    }
                }
        );
    }

    default Consumer<QueryWrapper<ENTITY>> buildStringSpecification(StringFilter filter, String field) {
        return buildSpecification(filter, field, false);
    }

    default Consumer<QueryWrapper<ENTITY>> buildStringSpecification(StringFilter filter, String field, Boolean useOr) {
        return buildSpecification(filter, field, useOr);
    }

    default Consumer<QueryWrapper<ENTITY>> buildSpecification(StringFilter filter, String field, Boolean useOr) {
        return (
                queryWrapper -> {
                    boolean noFilter = true;
                    if (StringUtils.isNotBlank(filter.getEquals())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.eq(field, filter.getEquals()));
                        } else {
                            queryWrapper.eq(field, filter.getEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notIn(field, filter.getNotIn()));
                        } else {
                            queryWrapper.notIn(field, filter.getNotIn());
                        }
                        noFilter = false;
                    }
                    if (StringUtils.isNotBlank(filter.getContains())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.like(field, filter.getContains()));
                        } else {
                            queryWrapper.like(field, filter.getContains());
                        }
                        noFilter = false;
                    }
                    if (StringUtils.isNotBlank(filter.getContainsLeft())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.likeLeft(field, filter.getContainsLeft()));
                        } else {
                            queryWrapper.likeLeft(field, filter.getContainsLeft());
                        }
                        noFilter = false;
                    }
                    if (StringUtils.isNotBlank(filter.getContainsRight())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.likeRight(field, filter.getContainsRight()));
                        } else {
                            queryWrapper.likeRight(field, filter.getContainsRight());
                        }
                        noFilter = false;
                    }
                    if (StringUtils.isNotBlank(filter.getDoesNotContain())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notLike(field, filter.getDoesNotContain()));
                        } else {
                            queryWrapper.notLike(field, filter.getDoesNotContain());
                        }
                        noFilter = false;
                    }
                    if (StringUtils.isNotBlank(filter.getNotEquals())) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ne(field, filter.getNotEquals()));
                        } else {
                            queryWrapper.ne(field, filter.getNotEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getSpecified() != null) {
                        if (filter.getSpecified()) {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNotNull(field));
                            } else {
                                queryWrapper.isNotNull(field);
                            }
                        } else {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNull(field));
                            } else {
                                queryWrapper.isNull(field);
                            }
                        }
                        noFilter = false;
                    }
                    if (noFilter) {
                        queryWrapper.eq("1", 1);
                    }
                }
        );
    }

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildRangeSpecification(RangeFilter<X> filter, String field) {
        return buildSpecification(filter, field, false);
    }

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildRangeSpecification(RangeFilter<X> filter, String field, Boolean useOr) {
        return buildSpecification(filter, field, useOr);
    }

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildSpecification(RangeFilter<X> filter, String field, Boolean useOr) {
        return (
                queryWrapper -> {
                    boolean noFilter = true;
                    if (filter.getEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.eq(field, filter.getEquals()));
                        } else {
                            queryWrapper.eq(field, filter.getEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notIn(field, filter.getNotIn()));
                        } else {
                            queryWrapper.notIn(field, filter.getNotIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getSpecified() != null) {
                        if (filter.getSpecified()) {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNotNull(field));
                            } else {
                                queryWrapper.isNotNull(field);
                            }
                        } else {
                            if (useOr) {
                                queryWrapper.or(q -> q.isNull(field));
                            } else {
                                queryWrapper.isNull(field);
                            }
                        }
                        noFilter = false;
                    }
                    if (filter.getNotEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ne(field, filter.getNotEquals()));
                        } else {
                            queryWrapper.ne(field, filter.getNotEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getGreaterThan() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.gt(field, filter.getGreaterThan()));
                        } else {
                            queryWrapper.gt(field, filter.getGreaterThan());
                        }
                        noFilter = false;
                    }
                    if (filter.getGreaterThanOrEqual() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ge(field, filter.getGreaterThanOrEqual()));
                        } else {
                            queryWrapper.ge(field, filter.getGreaterThanOrEqual());
                        }
                        noFilter = false;
                    }
                    if (filter.getLessThan() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.lt(field, filter.getLessThan()));
                        } else {
                            queryWrapper.lt(field, filter.getLessThan());
                        }
                        noFilter = false;
                    }
                    if (filter.getLessThanOrEqual() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.le(field, filter.getLessThanOrEqual()));
                        } else {
                            queryWrapper.le(field, filter.getLessThanOrEqual());
                        }
                        noFilter = false;
                    }
                    if (filter.getBetween() != null) {
                        if (useOr) {
                            if (filter.getBetween().length >= 2) {
                                if (Objects.nonNull(filter.getBetween()[0]) && Objects.nonNull(filter.getBetween()[1])) {
                                    queryWrapper.or(q -> q.between(field, filter.getBetween()[0], filter.getBetween()[1]));
                                } else if (Objects.nonNull(filter.getBetween()[0])) {
                                    queryWrapper.or(q -> q.ge(field, filter.getBetween()[0]));
                                } else {
                                    queryWrapper.or(q -> q.le(field, filter.getBetween()[1]));
                                }
                            } else if (filter.getBetween().length == 1) {
                                queryWrapper.ge(field, filter.getBetween()[0]);
                            }
                        } else {
                            if (filter.getBetween().length >= 2) {
                                if (Objects.nonNull(filter.getBetween()[0]) && Objects.nonNull(filter.getBetween()[1])) {
                                    queryWrapper.between(field, filter.getBetween()[0], filter.getBetween()[1]);
                                } else if (Objects.nonNull(filter.getBetween()[0])) {
                                    queryWrapper.ge(field, filter.getBetween()[0]);
                                } else {
                                    queryWrapper.le(field, filter.getBetween()[1]);
                                }
                            } else if (filter.getBetween().length == 1) {
                                queryWrapper.ge(field, filter.getBetween()[0]);
                            }
                        }
                        noFilter = false;
                    }
                    if (noFilter) {
                        queryWrapper.eq("1", 1);
                    }
                }
        );
    }

    default <C extends Criteria> QueryWrapper<ENTITY> createQueryWrapper(QueryWrapper<ENTITY> queryWrapper, Boolean useOr, C criteria, Class<ENTITY> entityClass) {
        if (criteria != null) {
            if (useOr == null) {
                useOr = false;
            }
            Map<QueryWrapper<ENTITY>, Map<String, Object>> queryWrapperMapMap = criteriaToWrapper(criteria, entityClass);
            Map.Entry<QueryWrapper<ENTITY>, Map<String, Object>> queryWrapperMapEntry = queryWrapperMapMap
                .entrySet()
                .stream()
                .findFirst()
                .orElseThrow();
            Map<String, Object> fieldMap = queryWrapperMapEntry.getValue().entrySet().stream()
                .filter(entry -> {
                    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(entry.getValue(), false, true);
                    stringObjectMap.remove("aggregate");
                    stringObjectMap.remove("groupBy");
                    return !stringObjectMap.isEmpty();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (MapUtils.isNotEmpty(fieldMap)) {
                if (queryWrapper == null) {
                    queryWrapper = queryWrapperMapEntry.getKey();
                }
                QueryWrapper<ENTITY> finalQueryWrapper = queryWrapper;
                Boolean finalUseOr = useOr;
                fieldMap.forEach((fieldName, filter) -> {
                    if (filter instanceof StringFilter) {
                        CriteriaUtil.build(
                            finalUseOr,
                            finalQueryWrapper,
                            buildStringSpecification((StringFilter) filter, fieldName, finalUseOr)
                        );
                    } else if (filter instanceof RangeFilter) {
                        CriteriaUtil.build(
                            finalUseOr,
                            finalQueryWrapper,
                            buildRangeSpecification((RangeFilter) filter, fieldName, finalUseOr)
                        );
                    } else if (filter instanceof Filter) {
                        CriteriaUtil.build(finalUseOr, finalQueryWrapper, buildSpecification((Filter) filter, fieldName, finalUseOr));
                    }
                });
            }
            if (criteria.getAnd() != null) {
                Map<String, Object> stringObjectMap = BeanUtil.beanToMap(criteria.getAnd(), false, true);
                if (
                    !((stringObjectMap.containsKey("useOr") && stringObjectMap.keySet().size() == 1) ||
                        ObjectUtils.isEmpty(stringObjectMap))
                ) {
                    if (queryWrapper != null) {
                        queryWrapper.and(q -> createQueryWrapper(q, criteria.getAnd().getUseOr(), criteria.getAnd(), entityClass));
                    } else {
                        queryWrapper = createQueryWrapper(null, criteria.getAnd().getUseOr(), criteria.getAnd(), entityClass);
                    }
                }
            } else {
                if (criteria.getOr() != null) {
                    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(criteria.getOr(), false, true);
                    if (
                        !((stringObjectMap.containsKey("useOr") && stringObjectMap.keySet().size() == 1) ||
                            ObjectUtils.isEmpty(stringObjectMap))
                    ) {
                        if (queryWrapper != null) {
                            queryWrapper.or(q -> createQueryWrapper(q, criteria.getOr().getUseOr(), criteria.getOr(), entityClass));
                        } else {
                            queryWrapper = createQueryWrapper(null, criteria.getOr().getUseOr(), criteria.getOr(), entityClass);
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }

    default <C extends Criteria> QueryWrapper<ENTITY> createQueryWrapperNoJoin(QueryWrapper<ENTITY> queryWrapper, Boolean useOr, C criteria, Class<ENTITY> entityClass) {
        if (criteria != null) {
            if (useOr == null) {
                useOr = false;
            }
            Map<QueryWrapper<ENTITY>, Map<String, Object>> queryWrapperMapMap = criteriaToWrapperNoJoin(criteria, entityClass);
            Map.Entry<QueryWrapper<ENTITY>, Map<String, Object>> queryWrapperMapEntry = queryWrapperMapMap
                .entrySet()
                .stream()
                .findFirst()
                .orElseThrow();
            Map<String, Object> fieldMap = queryWrapperMapEntry.getValue();
            if (MapUtils.isNotEmpty(fieldMap)) {
                if (queryWrapper == null) {
                    queryWrapper = queryWrapperMapEntry.getKey();
                }
                QueryWrapper<ENTITY> finalQueryWrapper = queryWrapper;
                Boolean finalUseOr = useOr;
                fieldMap.forEach((fieldName, filter) -> {
                    if (filter instanceof StringFilter) {
                        CriteriaUtil.build(
                            finalUseOr,
                            finalQueryWrapper,
                            buildStringSpecification((StringFilter) filter, fieldName, finalUseOr)
                        );
                    } else if (filter instanceof RangeFilter) {
                        CriteriaUtil.build(
                            finalUseOr,
                            finalQueryWrapper,
                            buildRangeSpecification((RangeFilter) filter, fieldName, finalUseOr)
                        );
                    } else if (filter instanceof Filter) {
                        CriteriaUtil.build(finalUseOr, finalQueryWrapper, buildSpecification((Filter) filter, fieldName, finalUseOr));
                    }
                });
            }
            if (criteria.getAnd() != null) {
                Map<String, Object> stringObjectMap = BeanUtil.beanToMap(criteria.getAnd(), false, true);
                if (
                    !((stringObjectMap.containsKey("useOr") && stringObjectMap.keySet().size() == 1) ||
                        ObjectUtils.isEmpty(stringObjectMap))
                ) {
                    if (queryWrapper != null) {
                        queryWrapper.and(q -> createQueryWrapperNoJoin(q, criteria.getAnd().getUseOr(), criteria.getAnd(), entityClass));
                    } else {
                        queryWrapper = createQueryWrapperNoJoin(null, criteria.getAnd().getUseOr(), criteria.getAnd(), entityClass);
                    }
                }
            } else {
                if (criteria.getOr() != null) {
                    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(criteria.getOr(), false, true);
                    if (
                        !((stringObjectMap.containsKey("useOr") && stringObjectMap.keySet().size() == 1) ||
                            ObjectUtils.isEmpty(stringObjectMap))
                    ) {
                        if (queryWrapper != null) {
                            queryWrapper.or(q -> createQueryWrapperNoJoin(q, criteria.getOr().getUseOr(), criteria.getOr(), entityClass));
                        } else {
                            queryWrapper = createQueryWrapperNoJoin(null, criteria.getOr().getUseOr(), criteria.getOr(), entityClass);
                        }
                    }
                }
            }
        }
        return queryWrapper;
    }

    default void getAggregateAndGroupBy(Filter<?> filter, String columnName, String columnAlias, List<String> selects, List<String> groupBys) {
        if (filter.getAggregate() != null) {
            if (filter.getAggregate() instanceof NumberAggregate) {
                buildAggregate((NumberAggregate) filter.getAggregate(), columnName, columnAlias, selects);
            } else {
                buildAggregate(filter.getAggregate(), columnName, columnAlias, selects);
            }
        }
        if (filter.getGroupBy() != null) {
            if (filter.getGroupBy() instanceof DateTimeGroupBy) {
                buildGroupBy((DateTimeGroupBy) filter.getGroupBy(), columnName, columnAlias, groupBys, selects);
            } else {
                buildGroupBy(filter.getGroupBy(), columnName, columnAlias, groupBys, selects);
            }
        }
    }
}
