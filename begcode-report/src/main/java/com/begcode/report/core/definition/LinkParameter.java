
package com.begcode.report.core.definition;

import com.begcode.report.core.expression.model.Expression;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2017年3月31日
 */
public class LinkParameter implements Serializable{
	private static final long serialVersionUID = -5156733452111427492L;
	private String name;
	private String value;
	private Expression valueExpression;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Expression getValueExpression() {
		return valueExpression;
	}
	public void setValueExpression(Expression valueExpression) {
		this.valueExpression = valueExpression;
	}
}
