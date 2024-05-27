
package com.begcode.report.core.expression.model.expr.cell;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年1月20日
 */
public class CellValueExpression extends BaseExpression {
	private static final long serialVersionUID = 5964924636009364350L;

	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		while(!context.isCellPocessed(cell.getName())){
			context.getReportBuilder().buildCell(context, null);
		}
		return new ObjectExpressionData(cell.getData());
	}
}
