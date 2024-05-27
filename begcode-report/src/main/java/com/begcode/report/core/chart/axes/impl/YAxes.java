
package com.begcode.report.core.chart.axes.impl;


import com.begcode.report.core.chart.axes.BaseAxes;
import com.begcode.report.core.chart.axes.ScaleLabel;
import com.begcode.report.core.chart.axes.YPosition;

/**
 * @author Jacky.gao
 * @since 2017年6月14日
 */
public class YAxes extends BaseAxes {
	private YPosition yposition;
	@Override
	public String toJson() {
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"ticks\":{");
		sb.append("\"minRotation\":"+getRotation()+"");
		sb.append("}");
		ScaleLabel scaleLabel=getScaleLabel();
		if(scaleLabel!=null){

			sb.append(",\"scaleLabel\":"+scaleLabel.toJson());
		}
		sb.append("}");
		return sb.toString();
	}
	public YPosition getYposition() {
		return yposition;
	}
	public void setYposition(YPosition yposition) {
		this.yposition = yposition;
	}
}
