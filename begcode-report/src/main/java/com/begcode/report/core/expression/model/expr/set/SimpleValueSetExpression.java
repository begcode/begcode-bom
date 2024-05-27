
package com.begcode.report.core.expression.model.expr.set;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年1月1日
 */
public class SimpleValueSetExpression extends BaseExpression {
	private static final long serialVersionUID = -5433811018086391838L;
	private Object simpleValue;
	public SimpleValueSetExpression(Object simpleValue) {
		this.simpleValue=simpleValue;
	}
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new ObjectExpressionData(simpleValue);
	}
}
