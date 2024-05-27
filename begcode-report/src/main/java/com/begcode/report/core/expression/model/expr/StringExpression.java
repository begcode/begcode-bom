
package com.begcode.report.core.expression.model.expr;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public class StringExpression extends BaseExpression{
	private static final long serialVersionUID = 4810887743258516630L;
	private String text;
	public StringExpression(String text) {
		this.text=text;
	}

	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new ObjectExpressionData(text);
	}
}
