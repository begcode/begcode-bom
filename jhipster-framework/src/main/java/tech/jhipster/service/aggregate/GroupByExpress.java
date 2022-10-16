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

import java.io.Serializable;

/**
 * Filter class for {@link Long} type attributes.
 *
 * @see RangeFilter
 */
public class GroupByExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean join;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public GroupByExpress() {
    }

    public GroupByExpress(Boolean join) {
        this.join = join;
    }

    public GroupByExpress(GroupByExpress groupByExpress) {
        this.join = groupByExpress.join;
    }
    /**
     * <p>copy.</p>
     *
     * @return a {@link GroupByExpress} object.
     */
    public GroupByExpress copy() {
        return new GroupByExpress(this);
    }

    public Boolean getJoin() {
        return join;
    }

    public void setJoin(Boolean join) {
        this.join = join;
    }

    public GroupByExpress join(Boolean join) {
        this.join = join;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupByExpress that = (GroupByExpress) o;

        return join.equals(that.join);
    }

    @Override
    public int hashCode() {
        return join.hashCode();
    }

    @Override
    public String toString() {
        return "GroupByExpress{" +
            "join=" + join +
            '}';
    }
}
