
package com.begcode.report.core.expression.model.expr.set;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月1日
 */
public class FromToExpression extends BaseExpression {
	private static final long serialVersionUID = -3250140935488901894L;
	private BaseExpression fromExpression;
	private BaseExpression toExpression;

	public FromToExpression(BaseExpression fromExpression,BaseExpression toExpression) {
		this.fromExpression = fromExpression;
		this.toExpression = toExpression;
	}

	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		Object fromData=fromExpression.execute(cell,currentCell, context);
		Object toData=toExpression.execute(cell,currentCell, context);
		int from=convertFloatData(fromData),to=convertFloatData(toData);
		List<Integer> list=new ArrayList<Integer>();
		for(int i=from;i<=to;i++){
			list.add(i);
		}
		return new ObjectListExpressionData(list);
	}

	private int convertFloatData(Object data){
		if(data instanceof ObjectExpressionData){
			Object obj=((ObjectExpressionData)data).getData();
			return Utils.toBigDecimal(obj).intValue();
		}else{
			throw new ReportComputeException("Can not convert ["+data+"] to integer.");
		}
	}
}
