
package com.begcode.report.core.expression.function;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Column;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月25日
 */
@Component
public class ColumnFunction implements Function{
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		Column col=currentCell.getColumn();
		return col.getColumnNumber();
	}
	@Override
	public String name() {
		return "column";
	}
}
