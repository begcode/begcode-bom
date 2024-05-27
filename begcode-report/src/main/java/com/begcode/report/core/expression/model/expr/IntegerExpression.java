
package com.begcode.report.core.expression.model.expr;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public class IntegerExpression extends BaseExpression {
	private static final long serialVersionUID = -8903608366537307519L;
	private Integer value;
	public IntegerExpression(Integer value) {
		this.value=value;
	}
	@Override
	public ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		return new ObjectExpressionData(value);
	}
}
