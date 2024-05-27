
package com.begcode.report.core.build.compute;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.chart.Chart;
import com.begcode.report.core.chart.ChartData;
import com.begcode.report.core.definition.value.ChartValue;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年6月9日
 */
public class ChartValueCompute implements ValueCompute {

	@Override
	public List<BindData> compute(Cell cell, Context context) {
		ChartValue chartValue=(ChartValue)cell.getValue();
		Chart chart=chartValue.getChart();
		ChartData data=chart.doCompute(cell, context);
		List<BindData> list=new ArrayList<BindData>();
		list.add(new BindData(data));
		return list;
	}

	@Override
	public ValueType type() {
		return ValueType.chart;
	}
}
