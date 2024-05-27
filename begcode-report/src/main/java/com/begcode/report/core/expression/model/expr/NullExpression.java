
package com.begcode.report.core.expression.model.expr;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.NoneExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public class NullExpression extends BaseExpression {
	private static final long serialVersionUID = -5448531052217619991L;

	@Override
	public ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new NoneExpressionData();
	}
}
