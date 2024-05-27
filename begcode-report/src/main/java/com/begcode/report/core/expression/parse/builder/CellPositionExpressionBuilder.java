
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.CellPositionExpression;

/**
 * @author Jacky.gao
 * @since 2017年1月21日
 */
public class CellPositionExpressionBuilder implements ExpressionBuilder {

	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		ReportParserParser.CellPositionContext ctx=unitContext.cellPosition();
		CellPositionExpression expr=new CellPositionExpression(ctx.Cell().getText());
		return expr;
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.cellPosition()!=null;
	}
}
