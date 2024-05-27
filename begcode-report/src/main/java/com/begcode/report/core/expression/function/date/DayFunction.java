
package com.begcode.report.core.expression.function.date;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月21日
 */
@Component
public class DayFunction extends CalendarFunction {
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		Calendar c = buildCalendar(dataList);
		int day=c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	@Override
	public String name() {
		return "day";
	}
}
