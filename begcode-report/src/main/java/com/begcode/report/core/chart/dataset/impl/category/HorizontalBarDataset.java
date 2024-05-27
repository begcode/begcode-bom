
package com.begcode.report.core.chart.dataset.impl.category;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class HorizontalBarDataset extends BarDataset {
	@Override
	public String buildDataJson(Context context, Cell cell) {
		String datasetJson=buildDatasetJson(context, cell,null);
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
		return "horizontalBar";
	}
}
