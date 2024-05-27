
package com.begcode.report.core.expression.model.expr;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;

import java.math.BigDecimal;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public class NumberExpression extends BaseExpression {
	private static final long serialVersionUID = 1694636614530741241L;
	private BigDecimal value;
	public NumberExpression(BigDecimal value) {
		this.value=value;
	}
	@Override
	public ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new ObjectExpressionData(value.floatValue());
	}
}
