/*
 * Copyright 2016-2022 the original author or authors from the JHipster project.
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

package tech.jhipster.service.aggregate;

import tech.jhipster.service.filter.RangeFilter;

/**
 * Filter class for {@link Long} type attributes.
 *
 * @see RangeFilter
 */
public class DateTimeGroupBy extends GroupByExpress {

    private static final long serialVersionUID = 1L;

    private Boolean year;

    private Boolean month;

    private Boolean day;

    private Boolean hour;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public DateTimeGroupBy() {
    }

    public DateTimeGroupBy(DateTimeGroupBy filter) {
        super(filter);
        year = filter.year;
        month = filter.month;
        day = filter.day;
        hour = filter.hour;
    }
    /**
     * <p>copy.</p>
     *
     * @return a {@link DateTimeGroupBy} object.
     */
    public DateTimeGroupBy copy() {
        return new DateTimeGroupBy(this);
    }

    public Boolean getYear() {
        return year;
    }

    public void setYear(Boolean year) {
        this.year = year;
    }

    public Boolean getMonth() {
        return month;
    }

    public void setMonth(Boolean month) {
        this.month = month;
    }

    public Boolean getDay() {
        return day;
    }

    public void setDay(Boolean day) {
        this.day = day;
    }

    public Boolean getHour() {
        return hour;
    }

    public void setHour(Boolean hour) {
        this.hour = hour;
    }

    public DateTimeGroupBy year(Boolean year) {
        this.year = year;
        return this;
    }

    public DateTimeGroupBy month(Boolean month) {
        this.month = month;
        return this;
    }

    public DateTimeGroupBy day(Boolean day) {
        this.day = day;
        return this;
    }

    public DateTimeGroupBy hour(Boolean hour) {
        this.hour = hour;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DateTimeGroupBy that = (DateTimeGroupBy) o;

        if (!year.equals(that.year)) return false;
        if (!month.equals(that.month)) return false;
        if (!day.equals(that.day)) return false;
        return hour.equals(that.hour);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + month.hashCode();
        result = 31 * result + day.hashCode();
        result = 31 * result + hour.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DateTimeGroupBy{" +
            "year=" + year +
            ", month=" + month +
            ", day=" + day +
            ", hour=" + hour +
            '}';
    }
}
