
package com.begcode.report.core.expression.function;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.BindDataListExpressionData;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
@Component
public class CountFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		if(dataList==null || dataList.size()==0){
			return null;
		}
		int size=0;
		for(ExpressionData<?> data:dataList){
			if(data instanceof ObjectListExpressionData){
				ObjectListExpressionData listExpressionData=(ObjectListExpressionData)data;
				size+=listExpressionData.getData().size();
			}else if(data instanceof ObjectExpressionData){
				size++;
			}else if(data instanceof BindDataListExpressionData){
				BindDataListExpressionData bindDataList=(BindDataListExpressionData)data;
				size+=bindDataList.getData().size();
			}
		}
		return size;
	}

	@Override
	public String name() {
		return "count";
	}
}
