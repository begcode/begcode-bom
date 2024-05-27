
package com.begcode.report.core.expression.model.expr.cell;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.model.Cell;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jacky.gao
 * @since 2017年1月20日
 */
public class CellObjectExpression extends BaseExpression {
	private static final long serialVersionUID = 1558531964770533126L;
	private String property;
	public CellObjectExpression(String property) {
		this.property=property;
	}
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		while(!context.isCellPocessed(cell.getName())){
			context.getReportBuilder().buildCell(context, null);
		}
		if(StringUtils.isNotBlank(property)){
			Object obj= Utils.getProperty(cell, property);
			return new ObjectExpressionData(obj);
		}
		return new ObjectExpressionData(cell);
	}
}
