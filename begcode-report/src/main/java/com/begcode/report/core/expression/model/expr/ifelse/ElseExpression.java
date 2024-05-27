
package com.begcode.report.core.expression.model.expr.ifelse;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.ExpressionBlock;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年1月16日
 */
public class ElseExpression extends BaseExpression {
	private static final long serialVersionUID = 7686136494993309779L;
	private ExpressionBlock expression;
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return expression.execute(cell, currentCell,context);
	}
	public ExpressionBlock getExpression() {
		return expression;
	}
	public void setExpression(ExpressionBlock expression) {
		this.expression = expression;
	}
}
