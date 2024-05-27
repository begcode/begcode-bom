
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.VariableExpression;

/**
 * @author Jacky.gao
 * @since 2018年7月15日
 */
public class VariableExpressionBuilder implements ExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		String text=unitContext.variable().Identifier().getText();
		VariableExpression varExpr=new VariableExpression(text);
		return varExpr;
	}
	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.variable()!=null;
	}
}
