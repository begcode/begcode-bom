
package com.begcode.report.core.expression.model.expr;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.BindDataListExpressionData;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2018年7月13日
 */
public class VariableAssignExpression extends BaseExpression {
	private static final long serialVersionUID = 435511939569866187L;
	private String variable;
	private Expression expression;
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		ExpressionData<?> data=expression.execute(cell, currentCell, context);
		Object obj=null;
		if(data instanceof ObjectExpressionData){
			ObjectExpressionData d=(ObjectExpressionData)data;
			obj=d.getData();
		}else if(data instanceof ObjectListExpressionData){
			ObjectListExpressionData d=(ObjectListExpressionData)data;
			obj=d.getData();
		}else if(data instanceof BindDataListExpressionData){
			BindDataListExpressionData dataList=(BindDataListExpressionData)data;
			List<BindData> bindList=dataList.getData();
			if(bindList.size()==1){
				BindData bindData=bindList.get(0);
				obj=bindData.getValue();
			}else{
				StringBuilder sb=new StringBuilder();
				for(BindData bd:bindList){
					if(sb.length()>0){
						sb.append(",");
					}
					sb.append(bd.getValue());
				}
				obj=sb.toString();
			}
		}
		if(obj!=null){
			context.putVariable(variable, obj);
		}
		return null;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
