
package com.begcode.report.core.expression.function.math;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class LogFunction extends MathFunction {
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		BigDecimal data=buildBigDecimal(dataList);
		return Math.log(data.doubleValue());
	}

	@Override
	public String name() {
		return "log";
	}
}
