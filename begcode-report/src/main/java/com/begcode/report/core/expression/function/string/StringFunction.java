
package com.begcode.report.core.expression.function.string;


import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.expression.function.Function;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月24日
 */
public abstract class StringFunction implements Function {
	protected String buildString(List<ExpressionData<?>> dataList) {
		if(dataList==null || dataList.size()==0){
			throw new ReportComputeException("Function ["+name()+"] need a data of string parameter.");
		}
		ExpressionData<?> data=dataList.get(0);
		return buildString(data);
	}

	protected String buildString(ExpressionData<?> expressionData) {

		String result=null;
		if(expressionData instanceof ObjectListExpressionData){
			ObjectListExpressionData listData=(ObjectListExpressionData)expressionData;
			List<?> list=listData.getData();
			if(list==null || list.size()!=1){
				throw new ReportComputeException("Function ["+name()+"] need a data of number parameter.");
			}
			Object obj=list.get(0);
			if(obj==null){
				throw new ReportComputeException("Function ["+name()+"] parameter can not be null.");
			}
			result=obj.toString();
		}else if(expressionData instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)expressionData;
			Object obj=objData.getData();
			if(obj==null){
				throw new ReportComputeException("Function ["+name()+"] parameter can not be null.");
			}
			result=obj.toString();
		}else{
			throw new ReportComputeException("Function ["+name()+"] need a data of number parameter.");
		}

		return result;
	}
}
