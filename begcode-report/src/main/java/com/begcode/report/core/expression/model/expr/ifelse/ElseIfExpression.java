
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
public class ElseIfExpression extends BaseExpression {
	private static final long serialVersionUID = -198920923804292977L;
	private ExpressionConditionList conditionList;

	private ExpressionBlock expression;
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return expression.execute(cell, currentCell,context);
	}
	public boolean conditionsEval(Cell cell,Cell currentCell, Context context) {
		return conditionList.eval(context, cell,currentCell);
	}
	public void setConditionList(ExpressionConditionList conditionList) {
		this.conditionList = conditionList;
	}
	public ExpressionBlock getExpression() {
		return expression;
	}
	public void setExpression(ExpressionBlock expression) {
		this.expression = expression;
	}
}
