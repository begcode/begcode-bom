
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.cell.CellObjectExpression;

/**
 * @author Jacky.gao
 * @since 2017年1月20日
 */
public class CellObjectExpressionBuilder implements ExpressionBuilder {

	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		ReportParserParser.CellContext ctx=unitContext.cell();
		String property=null;
		ReportParserParser.PropertyContext propCtx=ctx.property();
		if(propCtx!=null){
			property=propCtx.getText();
		}
		CellObjectExpression expr=new CellObjectExpression(property);
		expr.setExpr(ctx.getText());
		return expr;
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.cell()!=null;
	}
}
