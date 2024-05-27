
package com.begcode.report.core.expression.function.page;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年5月5日
 */
@Component
public class PageNumberFunction extends PageFunction {
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		if(currentCell!=null && currentCell.getRow()!=null){
			return currentCell.getRow().getPageIndex();
		}else{
			return context.getPageIndex();
		}
	}
	@Override
	public String name() {
		return "page";
	}
}
