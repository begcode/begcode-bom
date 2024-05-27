
package com.begcode.report.core.expression.model.expr;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.Operator;
import com.begcode.report.core.expression.model.data.BindDataListExpressionData;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月15日
 */
public class JoinExpression extends BaseExpression {
	private static final long serialVersionUID = -9045259827109781135L;
	private List<Operator> operators;
	private List<BaseExpression> expressions;

	public JoinExpression(List<Operator> operators, List<BaseExpression> expressions) {
		this.operators = operators;
		this.expressions = expressions;
	}
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		if(expressions.size()==1){
			return expressions.get(0).compute(cell, currentCell,context);
		}
		List<Object> list=new ArrayList<Object>();
		for(int i=0;i<expressions.size();i++){
			BaseExpression expression=expressions.get(i);
			ExpressionData<?> data=expression.execute(cell, currentCell,context);
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
			if(obj==null){
				obj="";
			}
			list.add(obj);
		}
		String str=null;
		for(int i=0;i<list.size();i++){
			Object data=list.get(i);
			if(str==null){
				if(data instanceof String){
					str="\""+data+"\"";
					//str=""+data+"";
				}else{
					str=""+data+"";
				}
			}else{
				Operator op=operators.get(i-1);
				if(data instanceof String){
					str+=""+op+"\""+data+"\"";
					//str+=""+op+""+data+"";
				}else{
					str+=""+op+""+data+"";
				}
			}
		}
		Object obj=context.evalExpr(str);
		return new ObjectExpressionData(obj);
	}
	public List<BaseExpression> getExpressions() {
		return expressions;
	}
}
