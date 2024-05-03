/*
 * Copyright 2016-2024 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.jhipster.service.filter;

import dev.langchain4j.model.output.structured.Description;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * Filter class for Comparable types, where less than / greater than / etc relations could be interpreted. It can be
 * added to a criteria class as a member, to support the following query parameters:
 * <pre>
 *      fieldName.equals=42
 *      fieldName.notEquals=42
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=43,42
 *      fieldName.notIn=43,42
 *      fieldName.greaterThan=41
 *      fieldName.lessThan=44
 *      fieldName.greaterThanOrEqual=42
 *      fieldName.lessThanOrEqual=44
 * </pre>
 * Due to problems with the type conversions, the descendant classes should be used, where the generic type parameter
 * is materialized.
 *
 * @param <FIELD_TYPE> the type of filter.
 * @see IntegerFilter
 * @see DoubleFilter
 * @see FloatFilter
 * @see LongFilter
 * @see LocalDateFilter
 * @see InstantFilter
 * @see ShortFilter
 * @see ZonedDateTimeFilter
 */
public class RangeFilter<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends Filter<FIELD_TYPE> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Description("大于条件值。示例：{id:{greaterThan:1}}，表示id大于1")
    private FIELD_TYPE greaterThan;
    @Description("小于条件值。示例：{id:{lessThan:1}}，表示id小于1")
    private FIELD_TYPE lessThan;
    @Description("大于等于条件值。示例：{id:{greaterThanOrEqual:1}}，表示id大于等于1")
    private FIELD_TYPE greaterThanOrEqual;
    @Description("小于等于条件值。示例：{id:{lessThanOrEqual:1}}，表示id小于等于1")
    private FIELD_TYPE lessThanOrEqual;
    @Description("在两个值之间。示例：{id:{between: [1,3]}}表示id在1和3之间")
    private FIELD_TYPE[] between;

    /**
     * <p>Constructor for RangeFilter.</p>
     */
    public RangeFilter() {}

    /**
     * <p>Constructor for RangeFilter.</p>
     *
     * @param filter a {@link RangeFilter} object.
     */
    public RangeFilter(RangeFilter<FIELD_TYPE> filter) {
        super(filter);
        greaterThan = filter.greaterThan;
        lessThan = filter.lessThan;
        greaterThanOrEqual = filter.greaterThanOrEqual;
        lessThanOrEqual = filter.lessThanOrEqual;
        between = filter.between;
    }

    /** {@inheritDoc} */
    @Override
    public RangeFilter<FIELD_TYPE> copy() {
        return new RangeFilter<>(this);
    }

    /**
     * <p>Getter for the field <code>greaterThan</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public FIELD_TYPE getGreaterThan() {
        return greaterThan;
    }

    /**
     * <p>Setter for the field <code>greaterThan</code>.</p>
     *
     * @param greaterThan a FIELD_TYPE object.
     * @return a {@link RangeFilter} object.
     */
    public RangeFilter<FIELD_TYPE> setGreaterThan(FIELD_TYPE greaterThan) {
        this.greaterThan = greaterThan;
        return this;
    }

    /**
     * <p>Getter for the field <code>lessThan</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public FIELD_TYPE getLessThan() {
        return lessThan;
    }

    /**
     * <p>Setter for the field <code>lessThan</code>.</p>
     *
     * @param lessThan a FIELD_TYPE object.
     * @return a {@link RangeFilter} object.
     */
    public RangeFilter<FIELD_TYPE> setLessThan(FIELD_TYPE lessThan) {
        this.lessThan = lessThan;
        return this;
    }

    /**
     * <p>Getter for the field <code>greaterThanOrEqual</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public FIELD_TYPE getGreaterThanOrEqual() {
        return greaterThanOrEqual;
    }

    /**
     * <p>Setter for the field <code>greaterThanOrEqual</code>.</p>
     *
     * @param greaterThanOrEqual a FIELD_TYPE object.
     * @return a {@link RangeFilter} object.
     */
    public RangeFilter<FIELD_TYPE> setGreaterThanOrEqual(FIELD_TYPE greaterThanOrEqual) {
        this.greaterThanOrEqual = greaterThanOrEqual;
        return this;
    }

    /**
     * <p>Getter for the field <code>lessThanOrEqual</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public FIELD_TYPE getLessThanOrEqual() {
        return lessThanOrEqual;
    }

    /**
     * <p>Setter for the field <code>lessThanOrEqual</code>.</p>
     *
     * @param lessThanOrEqual a FIELD_TYPE object.
     * @return a {@link RangeFilter} object.
     */
    public RangeFilter<FIELD_TYPE> setLessThanOrEqual(FIELD_TYPE lessThanOrEqual) {
        this.lessThanOrEqual = lessThanOrEqual;
        return this;
    }

    public FIELD_TYPE[] getBetween() {
        return between;
    }

    public void setBetween(FIELD_TYPE[] between) {
        this.between = between;
    }

    public boolean hasDefinedFilter() {
        return super.hasDefinedFilter() && (greaterThan != null || lessThan != null || greaterThanOrEqual != null || lessThanOrEqual != null || between != null && between.length > 0);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RangeFilter<?> that = (RangeFilter<?>) o;
        return (
            Objects.equals(greaterThan, that.greaterThan) &&
            Objects.equals(lessThan, that.lessThan) &&
            Objects.equals(greaterThanOrEqual, that.greaterThanOrEqual) &&
            Objects.equals(lessThanOrEqual, that.lessThanOrEqual) &&
            Arrays.equals(between, that.between)
        );
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), greaterThan, lessThan, greaterThanOrEqual, lessThanOrEqual, Arrays.hashCode(between));
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (
            getFilterName() +
            " [" +
            (getEquals() != null ? "equals=" + getEquals() + ", " : "") +
            (getNotEquals() != null ? "notEquals=" + getNotEquals() + ", " : "") +
            (getSpecified() != null ? "specified=" + getSpecified() + ", " : "") +
            (getIn() != null ? "in=" + getIn() + ", " : "") +
            (getNotIn() != null ? "notIn=" + getNotIn() + ", " : "") +
            (getGreaterThan() != null ? "greaterThan=" + getGreaterThan() + ", " : "") +
            (getLessThan() != null ? "lessThan=" + getLessThan() + ", " : "") +
            (getGreaterThanOrEqual() != null ? "greaterThanOrEqual=" + getGreaterThanOrEqual() + ", " : "") +
            (getLessThanOrEqual() != null ? "lessThanOrEqual=" + getLessThanOrEqual() : "") +
            (getAggregate() != null ? "aggregate=" + getAggregate() : "") +
            (getGroupBy() != null ? "groupBy=" + getGroupBy() : "") +
            (getBetween() != null ? "between=" + Arrays.toString(getBetween()) : "") +
            "]"
        );
    }
}
