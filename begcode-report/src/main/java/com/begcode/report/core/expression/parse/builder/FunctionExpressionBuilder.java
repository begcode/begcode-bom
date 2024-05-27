
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.FunctionExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月26日
 */
public class FunctionExpressionBuilder extends BaseExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		ReportParserParser.FunctionContext ctx=unitContext.function();
		FunctionExpression expr=new FunctionExpression();
		expr.setExpr(ctx.getText());
		expr.setName(ctx.Identifier().getText());
		ReportParserParser.FunctionParameterContext functionParameterContext=ctx.functionParameter();
		if(functionParameterContext!=null){
			List<BaseExpression> exprList=new ArrayList<BaseExpression>();
			List<ReportParserParser.ItemContext> itemContexts=functionParameterContext.item();
			if(itemContexts!=null){
				for(int i=0;i<itemContexts.size();i++){
					ReportParserParser.ItemContext itemContext=itemContexts.get(i);
					BaseExpression baseExpr= ExpressionUtils.getExprVisitor().parseItemContext(itemContext);
					exprList.add(baseExpr);
				}
			}
			expr.setExpressions(exprList);
		}
		return expr;
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.function()!=null;
	}
}
