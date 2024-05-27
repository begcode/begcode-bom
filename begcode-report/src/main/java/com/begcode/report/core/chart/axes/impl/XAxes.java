
package com.begcode.report.core.chart.axes.impl;


import com.begcode.report.core.chart.axes.BaseAxes;
import com.begcode.report.core.chart.axes.ScaleLabel;
import com.begcode.report.core.chart.axes.XPosition;

/**
 * @author Jacky.gao
 * @since 2017年6月14日
 */
public class XAxes extends BaseAxes {
	private XPosition xposition=XPosition.bottom;
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
	public XPosition getXposition() {
		return xposition;
	}
	public void setXposition(XPosition xposition) {
		this.xposition = xposition;
	}
}
