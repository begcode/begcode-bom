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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member, to support the
 * following query parameters:
 * <pre>
 *      fieldName.equals='something'
 *      fieldName.notEquals='somethingElse'
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in='something','other'
 *      fieldName.notIn='something','other'
 * </pre>
 */
public class Aggregate implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean count;

    /**
     * <p>Constructor for Filter.</p>
     */
    public Aggregate() {
    }

    public Aggregate(Boolean count) {
        this.count = count;
    }

    /**
     * <p>Constructor for Aggregate.</p>
     *
     * @param aggregate a {@link Aggregate} object.
     */
    public Aggregate(Aggregate aggregate) {
        count = aggregate.count;
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link Aggregate} object.
     */
    public Aggregate copy() {
        return new Aggregate(this);
    }

    /**
     * <p>Getter for the field <code>equals</code>.</p>
     *
     * @return a FIELD_TYPE object.
     */
    public Boolean getCount() {
        return count;
    }

    /**
     * <p>Setter for the field <code>equals</code>.</p>
     *
     * @param count a FIELD_TYPE object.
     * @return a {@link Aggregate} object.
     */
    public Aggregate setCount(Boolean count) {
        this.count = count;
        return this;
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
        Aggregate filter = (Aggregate) o;
        return Objects.equals(count, filter.count);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getFilterName() + " ["
                + (getCount() != null ? "count=" + getCount() + ", " : "")
                + "]";
    }

    /**
     * <p>getFilterName.</p>
     *
     * @return a {@link String} object.
     */
    protected String getFilterName() {
        return getClass().getSimpleName();
    }
}
