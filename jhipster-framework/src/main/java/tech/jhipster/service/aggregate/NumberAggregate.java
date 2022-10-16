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

import org.apache.commons.lang3.StringUtils;
import tech.jhipster.service.filter.RangeFilter;

/**
 * Filter class for {@link Long} type attributes.
 *
 * @see RangeFilter
 */
public class NumberAggregate extends Aggregate {

    private static final long serialVersionUID = 1L;

    private Boolean sum;

    private Boolean avg;

    private Boolean min;

    private Boolean max;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public NumberAggregate() {
    }

    public NumberAggregate(NumberAggregate filter) {
        super(filter);
        sum = filter.sum;
        avg = filter.avg;
        max = filter.max;
        min = filter.min;
    }
    /**
     * <p>copy.</p>
     *
     * @return a {@link NumberAggregate} object.
     */
    public NumberAggregate copy() {
        return new NumberAggregate(this);
    }

    public Boolean getSum() {
        return sum;
    }

    public void setSum(Boolean sum) {
        this.sum = sum;
    }

    public Boolean getAvg() {
        return avg;
    }

    public void setAvg(Boolean avg) {
        this.avg = avg;
    }

    public Boolean getMin() {
        return min;
    }

    public void setMin(Boolean min) {
        this.min = min;
    }

    public Boolean getMax() {
        return max;
    }

    public void setMax(Boolean max) {
        this.max = max;
    }

    public NumberAggregate sum() {
        this.sum = true;
        return this;
    }

    public NumberAggregate avg() {
        this.avg = true;
        return this;
    }

    public NumberAggregate min() {
        this.min = true;
        return this;
    }

    public NumberAggregate max() {
        this.max = true;
        return this;
    }

    public NumberAggregate sum(Boolean sum) {
        this.sum = sum;
        return this;
    }

    public NumberAggregate avg(Boolean avg) {
        this.avg = avg;
        return this;
    }

    public NumberAggregate min(Boolean min) {
        this.min = min;
        return this;
    }

    public NumberAggregate max(Boolean max) {
        this.max = max;
        return this;
    }

}
