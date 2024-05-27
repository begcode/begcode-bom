
package com.begcode.report.core.expression.model.expr.ifelse;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.expression.model.expr.ExpressionBlock;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月16日
 */
public class IfExpression extends BaseExpression {
	private static final long serialVersionUID = -514395376408127087L;
	private ExpressionConditionList conditionList;
	private ExpressionBlock expression;
	private List<ElseIfExpression> elseIfExpressions;
	private ElseExpression elseExpression;
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		if(conditionList!=null){
			boolean result=conditionList.eval(context, cell,currentCell);
			if(result){
				return expression.execute(cell, currentCell,context);
			}
		}
		if(elseIfExpressions!=null){
			for(ElseIfExpression elseIfExpr:elseIfExpressions){
				if(elseIfExpr.conditionsEval(cell, currentCell,context)){
					return elseIfExpr.execute(cell,currentCell, context);
				}
			}
		}
		if(elseExpression!=null){
			return elseExpression.execute(cell,currentCell, context);
		}
		return new ObjectExpressionData(null);
	}
	public void setConditionList(ExpressionConditionList conditionList) {
		this.conditionList = conditionList;
	}
	public ExpressionConditionList getConditionList() {
		return conditionList;
	}
	public void setElseExpression(ElseExpression elseExpression) {
		this.elseExpression = elseExpression;
	}
	public void setElseIfExpressions(List<ElseIfExpression> elseIfExpressions) {
		this.elseIfExpressions = elseIfExpressions;
	}
	public ExpressionBlock getExpression() {
		return expression;
	}
	public void setExpression(ExpressionBlock expression) {
		this.expression = expression;
	}
	public ElseExpression getElseExpression() {
		return elseExpression;
	}
	public List<ElseIfExpression> getElseIfExpressions() {
		return elseIfExpressions;
	}
}
