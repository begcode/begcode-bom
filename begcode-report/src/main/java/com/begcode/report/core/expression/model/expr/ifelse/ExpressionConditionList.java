
package com.begcode.report.core.expression.model.expr.ifelse;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.condition.Join;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月16日
 */
public class ExpressionConditionList {
	private List<ExpressionCondition> conditions;
	private List<Join> joins;
	public ExpressionConditionList(List<ExpressionCondition> conditions,List<Join> joins) {
		this.conditions = conditions;
		this.joins = joins;
	}
	public boolean eval(Context context, Cell cell, Cell currentCell){
		if(conditions.size()==1){
			return conditions.get(0).eval(context, cell,currentCell);
		}
		for(int i=0;i<conditions.size();i++){
			ExpressionCondition condition=conditions.get(i);
			boolean result=condition.eval(context, cell,currentCell);
			Join join=null;
			if(i<joins.size()){
				join=joins.get(i);
			}
			if(join==null){
				return result;
			}else{
				if(join.equals(Join.and) && !result){
					return false;
				}
				if(join.equals(Join.or) && result){
					return true;
				}
			}
		}
		return true;
	}
	public List<ExpressionCondition> getConditions() {
		return conditions;
	}
}
