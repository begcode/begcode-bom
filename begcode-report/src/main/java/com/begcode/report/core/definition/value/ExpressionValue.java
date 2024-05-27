
package com.begcode.report.core.definition.value;

import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Expression;

/**
 * @author Jacky.gao
 * @since 2016年12月24日
 */
public class ExpressionValue implements Value{

	private static final long serialVersionUID = 1L;

	private String text;
	private Expression expression;
	public ExpressionValue(String text) {
		this.text=text;
		expression= ExpressionUtils.parseExpression(text);
	}

	@Override
	public ValueType getType() {
		return ValueType.expression;
	}
	@Override
	public String getValue() {
		return text;
	}
	public Expression getExpression() {
		return expression;
	}
}
