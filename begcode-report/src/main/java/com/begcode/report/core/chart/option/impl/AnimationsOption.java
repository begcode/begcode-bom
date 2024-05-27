
package com.begcode.report.core.chart.option.impl;


import com.begcode.report.core.chart.option.Easing;
import com.begcode.report.core.chart.option.Option;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class AnimationsOption implements Option {
	private int duration=1000;
	private Easing easing=Easing.easeOutQuart;
	@Override
	public String buildOptionJson() {
		StringBuilder sb=new StringBuilder();
		sb.append("\"animation\":{");
		sb.append("\"duration\":"+duration+",");
		sb.append("\"easing\":\""+easing+"\"");
		sb.append("}");
		return sb.toString();
	}
	@Override
	public String getType() {
		return "animation";
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Easing getEasing() {
		return easing;
	}
	public void setEasing(Easing easing) {
		this.easing = easing;
	}
}
