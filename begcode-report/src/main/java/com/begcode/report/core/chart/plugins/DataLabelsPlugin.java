
package com.begcode.report.core.chart.plugins;
/**
 * @author Jacky.gao
 * @since 2018年7月6日
 */
public class DataLabelsPlugin implements Plugin {
	private boolean display;
	@Override
	public String getName() {
		return "data-labels";
	}
	@Override
	public String toJson(String type) {
		StringBuilder sb=new StringBuilder();
		sb.append("\"datalabels\":{\"display\":"+display+",");
		sb.append("\"font\":{");
		sb.append("\"size\":14,");
		sb.append("\"weight\":\"bold\"");
		sb.append("}");
		sb.append("}");
		return sb.toString();
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
}
