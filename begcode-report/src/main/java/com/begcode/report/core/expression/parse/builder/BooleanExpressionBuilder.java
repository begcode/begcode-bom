
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.BooleanExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月25日
 */
public class BooleanExpressionBuilder implements ExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		String text=unitContext.BOOLEAN().getText();
		return new BooleanExpression(Boolean.valueOf(text));
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.BOOLEAN()!=null;
	}
}
