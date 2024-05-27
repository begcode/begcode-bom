
package com.begcode.report.core.expression.function.page;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月17日
 */
@Component
public class PageTotalFunction extends PageFunction{
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		return context.getTotalPages();
	}
	@Override
	public String name() {
		return "pages";
	}
}
