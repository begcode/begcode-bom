
package com.begcode.report.core.chart.dataset.impl.category;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class RadarDataset extends CategoryDataset {
	private boolean fill=true;
	private double lineTension=0.1;

	@Override
	public String buildDataJson(Context context, Cell cell) {
		String props="\"fill\":"+fill+",\"lineTension\":"+lineTension;
		String datasetJson=buildDatasetJson(context, cell,props);
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		String labels=getLabels();
		sb.append("\"labels\":"+labels+",");
		sb.append("\"datasets\":["+datasetJson+"]");
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String getType() {
		return "radar";
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public double getLineTension() {
		return lineTension;
	}

	public void setLineTension(double lineTension) {
		this.lineTension = lineTension;
	}
}
