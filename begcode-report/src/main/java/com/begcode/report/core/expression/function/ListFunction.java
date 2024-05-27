
package com.begcode.report.core.expression.function;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月3日
 */
@Component
public class ListFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		List<Object> list= new ArrayList<Object>();
		for(ExpressionData<?> d:dataList){
			if(d instanceof ObjectExpressionData){
				ObjectExpressionData exprData=(ObjectExpressionData)d;
				list.add(exprData.getData());
			}else if(d instanceof ObjectListExpressionData){
				ObjectListExpressionData listData=(ObjectListExpressionData)d;
				list.addAll(listData.getData());
			}
		}
		return list;
	}

	@Override
	public String name() {
		return "list";
	}
}
