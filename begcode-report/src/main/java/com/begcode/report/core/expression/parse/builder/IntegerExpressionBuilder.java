
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.IntegerExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月24日
 */
public class IntegerExpressionBuilder implements ExpressionBuilder{
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		Integer value=null;
		if(unitContext.INTEGER()!=null){
			value=Integer.valueOf(unitContext.INTEGER().getText());
		}
		return new IntegerExpression(value);
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.INTEGER()!=null;
	}

}
