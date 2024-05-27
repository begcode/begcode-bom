
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.CurrentCellValueExpression;

/**
 * @author Jacky.gao
 * @since 2017年7月11日
 */
public class CurrentCellValueExpressionBuilder implements ExpressionBuilder {

	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		return new CurrentCellValueExpression();
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.currentCellValue()!=null;
	}

}
