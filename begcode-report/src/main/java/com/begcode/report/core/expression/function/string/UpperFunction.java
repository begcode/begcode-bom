
package com.begcode.report.core.expression.function.string;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月24日
 */
@Component
public class UpperFunction extends StringFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		String text=buildString(dataList);
		return text.toUpperCase();
	}

	@Override
	public String name() {
		return "upper";
	}
}
