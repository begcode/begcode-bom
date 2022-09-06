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

package tech.jhipster.service.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter class for {@link java.time.LocalDate} type attributes.
 *
 * @see RangeFilter
 */
public class LocalDateFilter extends RangeFilter<LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for LocalDateFilter.</p>
     */
    public LocalDateFilter() {
    }

    public LocalDateFilter(String[] values) {
        if (values != null && values.length > 0) {
            LocalDate[] dates = Arrays
                .stream(values)
                .map(value -> LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE))
                .toArray(LocalDate[]::new);
            if (dates.length > 0) {
                this.setBetween(dates);
            }
        }
    }

    /**
     * <p>Constructor for LocalDateFilter.</p>
     *
     * @param filter a {@link LocalDateFilter} object.
     */
    public LocalDateFilter(LocalDateFilter filter) {
        super(filter);
    }

    /** {@inheritDoc} */
    @Override
    public LocalDateFilter copy() {
        return new LocalDateFilter(this);
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setEquals(LocalDate equals) {
        super.setEquals(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setNotEquals(LocalDate equals) {
        super.setNotEquals(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setIn(List<LocalDate> in) {
        super.setIn(in);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setNotIn(List<LocalDate> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setGreaterThan(LocalDate equals) {
        super.setGreaterThan(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setLessThan(LocalDate equals) {
        super.setLessThan(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setGreaterThanOrEqual(LocalDate equals) {
        super.setGreaterThanOrEqual(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setLessThanOrEqual(LocalDate equals) {
        super.setLessThanOrEqual(equals);
        return this;
    }

}
