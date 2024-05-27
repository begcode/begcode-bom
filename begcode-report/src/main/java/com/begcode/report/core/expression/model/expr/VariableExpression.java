
package com.begcode.report.core.expression.model.expr;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2018年7月15日
 */
public class VariableExpression extends BaseExpression{
	private static final long serialVersionUID = 4810887743258516630L;
	private String text;
	public VariableExpression(String text) {
		this.text=text;
	}

	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		Object obj=context.getVariable(text);
		return new ObjectExpressionData(obj);
	}
}
