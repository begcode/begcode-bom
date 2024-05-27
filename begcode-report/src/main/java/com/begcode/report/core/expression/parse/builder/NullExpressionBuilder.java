
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.NullExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月25日
 */
public class NullExpressionBuilder implements ExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		return new NullExpression();
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.NULL()!=null;
	}
}
