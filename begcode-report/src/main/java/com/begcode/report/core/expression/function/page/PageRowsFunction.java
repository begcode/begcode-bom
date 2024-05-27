
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
public class PageRowsFunction extends PageFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		int pageIndex=currentCell.getRow().getPageIndex();
		if(pageIndex==0)pageIndex=1;
		return context.getCurrentPageRows(pageIndex).size();
	}

	@Override
	public String name() {
		return "prows";
	}
}
