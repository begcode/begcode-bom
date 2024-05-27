
package com.begcode.report.core.definition.value;

import com.begcode.report.core.chart.Chart;

/**
 * @author Jacky.gao
 * @since 2017年6月9日
 */
public class ChartValue implements Value {

	private static final long serialVersionUID = 1L;

	private Chart chart;
	@Override
	public String getValue() {
		return null;
	}
	@Override
	public ValueType getType() {
		return ValueType.chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public Chart getChart() {
		return chart;
	}
}
