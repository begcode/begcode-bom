
package com.begcode.report.core.build.aggregate;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.value.Value;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.expression.model.Condition;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月20日
 */
public class CountAggregate extends Aggregate {
	@Override
	public List<BindData> aggregate(DatasetExpression expr, Cell cell, Context context) {
		String datasetName=expr.getDatasetName();
		Cell leftCell= DataUtils.fetchLeftCell(cell, context, datasetName);
		Cell topCell=DataUtils.fetchTopCell(cell, context, datasetName);
		List<Object> leftList=null,topList=null;
		if(leftCell!=null){
			leftList=leftCell.getBindData();
		}
		if(topCell!=null){
			topList=topCell.getBindData();
		}
		int count=0;
		if(leftList==null && topList==null){
			List<?> data=context.getDatasetData(datasetName);
			count = doCondition(data, cell, expr,context);
		}else if(leftList==null){
			count = doCondition(topList, cell, expr,context);
		}else if(topList==null){
			count = doCondition(leftList, cell, expr,context);
		}else{
			List<Object> list=null;
			Object data=null;
			String prop=null;
			if(leftList.size()>topList.size()){
				list=topList;
				data=leftCell.getData();
				Value value=leftCell.getValue();
				DatasetExpression de=DataUtils.fetchDatasetExpression(value);
				if(de==null){
					throw new ReportComputeException("Unsupport value : "+value);
				}
				prop=de.getProperty();
			}else{
				list=leftList;
				data=topCell.getData();
				Value value=topCell.getValue();
				DatasetExpression de=DataUtils.fetchDatasetExpression(value);
				if(de==null){
					throw new ReportComputeException("Unsupport value : "+value);
				}
				prop=de.getProperty();
			}
			Condition condition=getCondition(cell);
			if(condition==null){
				condition=expr.getCondition();
			}
			for(Object obj:list){
				if(condition!=null && !condition.filter(cell, cell, obj, context)){
					continue;
				}
				Object o= Utils.getProperty(obj, prop);
				if(o!=null && data!=null && (o==data || o.equals(data))){
					count++;
				}else if(o==null && data==null){
					count++;
				}
			}
		}
		List<BindData> list=new ArrayList<BindData>();
		list.add(new BindData(count,null));
		return list;
	}
	private int doCondition(List<?> dataList,Cell cell,DatasetExpression expr,Context context){
		Condition condition=getCondition(cell);
		if(condition==null){
			condition=expr.getCondition();
		}
		if(condition==null){
			return dataList.size();
		}
		int size=0;
		for(Object obj:dataList){
			boolean result=condition.filter(cell, cell, obj, context);
			if(result)size++;
		}
		return size;
	}
}
