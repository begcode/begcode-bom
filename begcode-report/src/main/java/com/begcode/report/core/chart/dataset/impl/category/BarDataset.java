
package com.begcode.report.core.chart.dataset.impl.category;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class BarDataset extends CategoryDataset{
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

	public String toMixJson(Context context,Cell cell,int index){
		String props="\"type\":\"bar\"";
		String datasetJson=buildDatasetJson(context, cell,props);
		return datasetJson;
	}
	@Override
	public String getType() {
		return "bar";
	}
}
