
package com.begcode.report.core.expression.parse.builder;


import com.begcode.report.core.definition.Order;
import com.begcode.report.core.definition.value.AggregateType;
import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.condition.BaseCondition;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author Jacky.gao
 * @since 2016年12月26日
 */
public class DatasetExpressionBuilder extends BaseExpressionBuilder {
	@Override
	public BaseExpression build(ReportParserParser.UnitContext unitContext) {
		ReportParserParser.DatasetContext context=(ReportParserParser.DatasetContext)unitContext.dataset();
		DatasetExpression expr=new DatasetExpression();
		expr.setExpr(context.getText());
		expr.setDatasetName(context.Identifier().getText());
		expr.setAggregate(AggregateType.valueOf(context.aggregate().getText()));
		if(context.property()!=null){
			expr.setProperty(context.property().getText());
		}
		ReportParserParser.ConditionsContext conditionsContext=context.conditions();
		if(conditionsContext!=null){
			BaseCondition condition=buildConditions(conditionsContext);
			expr.setCondition(condition);
		}
		TerminalNode orderNode=context.ORDER();
		if(orderNode!=null){
			expr.setOrder(Order.valueOf(orderNode.getText()));
		}
		return expr;
	}

	@Override
	public boolean support(ReportParserParser.UnitContext unitContext) {
		return unitContext.dataset()!=null;
	}
}
