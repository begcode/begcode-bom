/*
 * Copyright 2016-2025 the original author or authors from the JHipster project.
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
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.util.Objects;

/**
 * Class for filtering attributes with {@link java.lang.String} type.
 * It can be added to a criteria class as a member, to support the following query parameters:
 * <code>
 * fieldName.equals='something'
 * fieldName.notEquals='something'
 * fieldName.specified=true
 * fieldName.specified=false
 * fieldName.in='something','other'
 * fieldName.notIn='something','other'
 * fieldName.contains='thing'
 * fieldName.doesNotContain='thing'
 * </code>
 */
public class StringFilter extends Filter<String> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Description("字符串中包含的值。示例：contains=abc，表示查找包含abc的字符串")
    private String contains;
    @Description("字符串中不包含的值。示例：doesNotContain=abc，表示查找不包含abc的字符串")
    private String doesNotContain;
    @Description("字符串左侧包含的值。示例：containsLeft=abc，表示查找以abc开头的字符串")
    private String containsLeft;
    @Description("字符串右侧包含的值。示例：containsRight=abc，表示查找以abc结尾的字符串")
    private String containsRight;

    /**
     * <p>Constructor for StringFilter.</p>
     */
    public StringFilter() {}

    public StringFilter(String value) {
        if (StringUtils.isNotBlank(value)) {
            this.setContains(value);
        }
    }

    /**
     * <p>Constructor for StringFilter.</p>
     *
     * @param filter a {@link StringFilter} object.
     */
    public StringFilter(StringFilter filter) {
        super(filter);
        contains = filter.contains;
        doesNotContain = filter.doesNotContain;
    }

    /** {@inheritDoc} */
    @Override
    public StringFilter copy() {
        return new StringFilter(this);
    }

    /**
     * <p>Getter for the field <code>contains</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContains() {
        return contains;
    }

    /**
     * <p>Setter for the field <code>contains</code>.</p>
     *
     * @param contains a {@link java.lang.String} object.
     * @return a {@link StringFilter} object.
     */
    public StringFilter setContains(String contains) {
        this.contains = contains;
        return this;
    }

    /**
     * <p>Getter for the field <code>doesNotContain</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDoesNotContain() {
        return doesNotContain;
    }

    /**
     * <p>Setter for the field <code>doesNotContain</code>.</p>
     *
     * @param doesNotContain a {@link java.lang.String} object.
     * @return a {@link StringFilter} object.
     */
    public StringFilter setDoesNotContain(String doesNotContain) {
        this.doesNotContain = doesNotContain;
        return this;
    }

    public String getContainsLeft() {
        return containsLeft;
    }

    public StringFilter setContainsLeft(String containsLeft) {
        this.containsLeft = containsLeft;
        return this;
    }

    public String getContainsRight() {
        return containsRight;
    }

    public StringFilter setContainsRight(String containsRight) {
        this.containsRight = containsRight;
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
        if (!super.equals(o)) {
            return false;
        }
        StringFilter that = (StringFilter) o;
        return Objects.equals(contains, that.contains) &&
            Objects.equals(containsLeft, that.containsLeft) &&
            Objects.equals(containsRight, that.containsRight) &&
            Objects.equals(doesNotContain, that.doesNotContain);
    }

    public boolean hasDefinedFilter() {
        return super.hasDefinedFilter() && (StringUtils.isNotBlank(contains) || StringUtils.isNotBlank(doesNotContain) || StringUtils.isNotBlank(containsLeft) || StringUtils.isNotBlank(containsRight));
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contains, doesNotContain);
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
            (getContains() != null ? "contains=" + getContains() + ", " : "") +
            (getContainsLeft() != null ? "containsLeft=" + getContainsLeft() + ", " : "") +
            (getContainsRight() != null ? "containsRight=" + getContainsRight() + ", " : "") +
            (getDoesNotContain() != null ? "doesNotContain=" + getDoesNotContain() : "") +
            "]"
        );
    }
}
