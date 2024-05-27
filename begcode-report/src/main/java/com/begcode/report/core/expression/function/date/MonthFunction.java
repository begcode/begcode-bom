
package com.begcode.report.core.expression.function.date;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月22日
 */
@Component
public class MonthFunction extends CalendarFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		Calendar c = buildCalendar(dataList);
		int month=c.get(Calendar.MONTH)+1;
		return month+1;
	}

	@Override
	public String name() {
		return "month";
	}
}
