
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.CurrentCellDataExpression;

/**
 * @author Jacky.gao
 * @since 2017年7月11日
 */
public class CurrentCellDataExpressionBuilder implements ExpressionBuilder {

	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		ReportParserParser.CurrentCellDataContext context=unitContext.currentCellData();
		CurrentCellDataExpression expr=new CurrentCellDataExpression();
		expr.setProperty(context.property().getText());
		return expr;
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.currentCellData()!=null;
	}

}
