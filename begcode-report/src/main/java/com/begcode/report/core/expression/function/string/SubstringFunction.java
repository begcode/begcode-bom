
package com.begcode.report.core.expression.function.string;

import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月24日
 */
@Component
public class SubstringFunction extends StringFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		String text=buildString(dataList);
		int start=0,end=text.length();
		if(dataList.size()>1){
			ExpressionData<?> exprData=dataList.get(1);
			start=buildPos(exprData);
		}
		if(dataList.size()==3){
			ExpressionData<?> exprData=dataList.get(2);
			end=buildPos(exprData);
		}
		return text.substring(start, end);
	}

	private int buildPos(ExpressionData<?> exprData) {
		if(exprData instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)exprData;
			Object obj=objData.getData();
			if(obj==null){
				throw new ReportComputeException("Function ["+name()+"] second parameter can not be null.");
			}
			return Utils.toBigDecimal(obj).intValue();
		}
		throw new ReportComputeException("Function ["+name()+"] position data is invalid : "+exprData);
	}

	@Override
	public String name() {
		return "substring";
	}
}
