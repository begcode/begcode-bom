
package com.begcode.report.core.expression.model.expr;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年7月11日
 */
public class CurrentCellValueExpression extends BaseExpression {
	private static final long serialVersionUID = -653158121297142855L;

	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new ObjectExpressionData(cell.getData());
	}
}
