
package com.begcode.report.core.chart.dataset.impl;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.chart.dataset.Dataset;
import com.begcode.report.core.chart.dataset.impl.category.BarDataset;
import com.begcode.report.core.chart.dataset.impl.category.LineDataset;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class MixDataset implements Dataset {
	private List<BarDataset> barDatasets=new ArrayList<BarDataset>();
	private List<LineDataset> lineDatasets=new ArrayList<LineDataset>();

	@Override
	public String buildDataJson(Context context, Cell cell) {
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\"datasets\":[");
		int index=0;
		for(BarDataset ds:barDatasets){
			if(index>0){
				sb.append(",");
			}
			sb.append(ds.toMixJson(context, cell, index));
		}
		for(LineDataset ds:lineDatasets){
			if(index>0){
				sb.append(",");
			}
			sb.append(ds.toMixJson(context, cell, index));
		}
		sb.append("],");
		String labels=null;
		if(barDatasets.size()>0){
			labels=barDatasets.get(0).getLabels();
		}else if(lineDatasets.size()>0){
			labels=lineDatasets.get(0).getLabels();
		}else{
			throw new ReportComputeException("Mix chart need one dataset at least.");
		}
		sb.append("labels:"+labels);
		sb.append("}");
		return sb.toString();
	}


	@Override
	public String getType() {
		return "bar";
	}

	public List<BarDataset> getBarDatasets() {
		return barDatasets;
	}

	public void setBarDatasets(List<BarDataset> barDatasets) {
		this.barDatasets = barDatasets;
	}

	public List<LineDataset> getLineDatasets() {
		return lineDatasets;
	}

	public void setLineDatasets(List<LineDataset> lineDatasets) {
		this.lineDatasets = lineDatasets;
	}
}
