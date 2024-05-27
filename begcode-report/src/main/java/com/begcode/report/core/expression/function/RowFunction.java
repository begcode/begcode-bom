
package com.begcode.report.core.expression.function;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Row;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月25日
 */
@Component
public class RowFunction implements Function{
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		Row row=currentCell.getRow();
		return row.getRowNumber();
	}
	@Override
	public String name() {
		return "row";
	}
}
