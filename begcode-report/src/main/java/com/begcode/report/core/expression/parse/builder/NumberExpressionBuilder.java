
package com.begcode.report.core.expression.parse.builder;

import com.begcode.report.core.Utils;
import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.NumberExpression;

import java.math.BigDecimal;

/**
 * @author Jacky.gao
 * @since 2016年12月25日
 */
public class NumberExpressionBuilder implements ExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		BigDecimal value= Utils.toBigDecimal(unitContext.NUMBER().getText());
		return new NumberExpression(value);
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.NUMBER()!=null;
	}
}
