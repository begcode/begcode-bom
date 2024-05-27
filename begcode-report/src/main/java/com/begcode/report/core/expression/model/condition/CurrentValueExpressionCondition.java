
package com.begcode.report.core.expression.model.condition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年12月8日
 */
public class CurrentValueExpressionCondition extends BaseCondition {
	private ConditionType type=ConditionType.current;
	@JsonIgnore
	private Expression rightExpression;
	@Override
	Object computeLeft(Cell cell, Cell currentCell, Object obj, Context context) {
		return obj;
	}

	@Override
	Object computeRight(Cell cell,Cell currentCell,Object obj,Context context) {
		ExpressionData<?> exprData=rightExpression.execute(cell, currentCell,context);
		return extractExpressionData(exprData);
	}

	@Override
	public ConditionType getType() {
		return type;
	}
	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}
	public Expression getRightExpression() {
		return rightExpression;
	}
}
