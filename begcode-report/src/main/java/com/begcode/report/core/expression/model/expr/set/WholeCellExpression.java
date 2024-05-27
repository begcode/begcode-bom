
package com.begcode.report.core.expression.model.expr.set;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.Condition;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月6日
 */
public class WholeCellExpression extends CellExpression{
	private static final long serialVersionUID = 4926788994485522808L;
	private Condition condition;
	public WholeCellExpression(String cellName) {
		super(cellName);
	}
	@Override
	public boolean supportPaging(){
		return false;
	}
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		while(!context.isCellPocessed(cellName)){
			context.getReportBuilder().buildCell(context, null);
		}
		List<Cell> cells=context.getReport().getCellsMap().get(cellName);
		List<Object> list=new ArrayList<Object>();
		for(Cell c:cells){
			Object obj=c.getData();
			if(condition!=null){
				boolean result=condition.filter(cell, currentCell, obj, context);
				if(!result){
					continue;
				}
			}
			list.add(obj);
		}
		if(list.size()==1){
			return new ObjectExpressionData(list.get(0));
		}else{
			return new ObjectListExpressionData(list);
		}
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
