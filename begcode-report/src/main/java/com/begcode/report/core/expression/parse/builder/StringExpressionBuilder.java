
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.StringExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public class StringExpressionBuilder implements ExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		String text=unitContext.STRING().getText();
		text=text.substring(1,text.length()-1);
		StringExpression stringExpr=new StringExpression(text);
		return stringExpr;
	}
	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.STRING()!=null;
	}
}
