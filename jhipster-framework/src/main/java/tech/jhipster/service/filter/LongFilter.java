/*
 * Copyright 2016-2023 the original author or authors from the JHipster project.
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

import org.apache.commons.lang3.StringUtils;

/**
 * Filter class for {@link java.lang.Long} type attributes.
 *
 * @see RangeFilter
 */
public class LongFilter extends RangeFilter<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public LongFilter() {
    }

    public LongFilter(String value) {
        if (StringUtils.isNotBlank(value)) {
            if (StringUtils.isNumeric(value)) {
                this.setEquals(Long.parseLong(value));
            }
        }
    }

    /**
     * <p>Constructor for LongFilter.</p>
     *
     * @param filter a {@link LongFilter} object.
     */
    public LongFilter(LongFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link LongFilter} object.
     */
    public LongFilter copy() {
        return new LongFilter(this);
    }

}
