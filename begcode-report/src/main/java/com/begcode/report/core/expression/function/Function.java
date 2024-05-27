
package com.begcode.report.core.expression.function;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
public interface Function {
	Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell);
	String name();
}
