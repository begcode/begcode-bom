
package com.begcode.report.core.expression.function.string;

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
public class ReplaceFunction extends StringFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		if(dataList.size()!=3){
			throw new ReportComputeException("Function ["+name()+"] need three parameters.");
		}
		String text=buildString(dataList);
		String targetText=null,replaceText=null;
		ExpressionData<?> exprData=dataList.get(1);
		if(exprData instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)exprData;
			Object obj=objData.getData();
			if(obj==null){
				throw new ReportComputeException("Function ["+name()+"] parameter can not be null.");
			}
			targetText=obj.toString();
		}
		exprData=dataList.get(2);
		replaceText=buildStart(exprData);
		return text.replaceAll(targetText, replaceText);
	}

	private String buildStart(ExpressionData<?> exprData) {
		if(exprData instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)exprData;
			Object obj=objData.getData();
			if(obj==null){
				throw new ReportComputeException("Function ["+name()+"] parameter can not be null.");
			}
			return obj.toString();
		}
		throw new ReportComputeException("Function ["+name()+"] start position data is invalid : "+exprData);
	}

	@Override
	public String name() {
		return "replace";
	}

}
