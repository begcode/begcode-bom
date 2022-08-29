package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.RangeFilter;
import tech.jhipster.service.filter.StringFilter;

import java.util.Objects;
import java.util.function.Consumer;

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
                    if (filter.getIn() != null && filter.getIn().size() > 0) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotIn() != null && filter.getNotIn().size() > 0) {
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
                    if (filter.getEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.eq(field, filter.getEquals()));
                        } else {
                            queryWrapper.eq(field, filter.getEquals());
                        }
                        noFilter = false;
                    }
                    if (filter.getIn() != null && filter.getIn().size() > 0) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getNotIn() != null && filter.getNotIn().size() > 0) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notIn(field, filter.getNotIn()));
                        } else {
                            queryWrapper.notIn(field, filter.getNotIn());
                        }
                        noFilter = false;
                    }
                    if (filter.getContains() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.like(field, filter.getContains()));
                        } else {
                            queryWrapper.like(field, filter.getContains());
                        }
                        noFilter = false;
                    }
                    if (filter.getContainsLeft() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.likeLeft(field, filter.getContainsLeft()));
                        } else {
                            queryWrapper.likeLeft(field, filter.getContainsLeft());
                        }
                        noFilter = false;
                    }
                    if (filter.getContainsRight() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.likeRight(field, filter.getContainsRight()));
                        } else {
                            queryWrapper.likeRight(field, filter.getContainsRight());
                        }
                        noFilter = false;
                    }
                    if (filter.getDoesNotContain() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notLike(field, filter.getDoesNotContain()));
                        } else {
                            queryWrapper.notLike(field, filter.getDoesNotContain());
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
                    if (noFilter) {
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

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildRangeSpecification(RangeFilter<X> filter, String field) {
        return buildSpecification(filter, field, false);
    }

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildRangeSpecification(RangeFilter<X> filter, String field, Boolean useOr) {
        return buildSpecification(filter, field, useOr);
    }

    default <X extends Comparable<? super X>> Consumer<QueryWrapper<ENTITY>> buildSpecification(RangeFilter<X> filter, String field, Boolean useOr) {
        return (
                queryWrapper -> {
                    // 防止为空（）
                    if (useOr) {
                        queryWrapper.or(q -> q.eq("1", 1));
                    } else {
                        queryWrapper.eq("1", 1);
                    }
                    if (filter.getEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.eq(field, filter.getEquals()));
                        } else {
                            queryWrapper.eq(field, filter.getEquals());
                        }
                    }
                    if (filter.getIn() != null && filter.getIn().size() > 0) {
                        if (useOr) {
                            queryWrapper.or(q -> q.in(field, filter.getIn()));
                        } else {
                            queryWrapper.in(field, filter.getIn());
                        }
                    }
                    if (filter.getNotIn() != null && filter.getNotIn().size() > 0) {
                        if (useOr) {
                            queryWrapper.or(q -> q.notIn(field, filter.getNotIn()));
                        } else {
                            queryWrapper.notIn(field, filter.getNotIn());
                        }
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
                    }
                    if (filter.getNotEquals() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ne(field, filter.getNotEquals()));
                        } else {
                            queryWrapper.ne(field, filter.getNotEquals());
                        }
                    }
                    if (filter.getGreaterThan() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.gt(field, filter.getGreaterThan()));
                        } else {
                            queryWrapper.gt(field, filter.getGreaterThan());
                        }
                    }
                    if (filter.getGreaterThanOrEqual() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.ge(field, filter.getGreaterThanOrEqual()));
                        } else {
                            queryWrapper.ge(field, filter.getGreaterThanOrEqual());
                        }
                    }
                    if (filter.getLessThan() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.lt(field, filter.getLessThan()));
                        } else {
                            queryWrapper.lt(field, filter.getLessThan());
                        }
                    }
                    if (filter.getLessThanOrEqual() != null) {
                        if (useOr) {
                            queryWrapper.or(q -> q.le(field, filter.getLessThanOrEqual()));
                        } else {
                            queryWrapper.le(field, filter.getLessThanOrEqual());
                        }
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
                    }
                }
        );
    }
}
