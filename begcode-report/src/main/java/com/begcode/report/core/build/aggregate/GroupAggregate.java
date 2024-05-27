
package com.begcode.report.core.build.aggregate;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.Order;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.utils.DataUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年12月22日
 */
public class GroupAggregate extends Aggregate {
	@Override
	public List<BindData> aggregate(DatasetExpression expr, Cell cell, Context context) {
		List<?> objList= DataUtils.fetchData(cell, context, expr.getDatasetName());
		List<BindData> list = doAggregate(expr, cell, context, objList);
		return list;
	}

	protected List<BindData> doAggregate(DatasetExpression expr, Cell cell,Context context, List<?> objList) {
		String property=expr.getProperty();
		Map<String,String> mappingMap=context.getMapping(expr);
		List<BindData> list=new ArrayList<BindData>();
		if(objList.size()==0){
			list.add(new BindData(""));
			return list;
		}else if(objList.size()==1){
			Object o=objList.get(0);
			boolean conditionResult=doCondition(expr.getCondition(),cell,o,context);
			if(!conditionResult){
				list.add(new BindData(""));
				return list;
			}
			Object data= Utils.getProperty(o, property);
			Object mappingData=mappingData(mappingMap,data);
			List<Object> rowList=new ArrayList<Object>();
			rowList.add(o);
			if(mappingData==null){
				list.add(new BindData(data,rowList));
			}else{
				list.add(new BindData(data,mappingData,rowList));
			}
			return list;
		}
		Map<Object,List<Object>> map=new HashMap<Object,List<Object>>();
		for(Object o:objList){
			boolean conditionResult=doCondition(expr.getCondition(),cell,o,context);
			if(!conditionResult){
				continue;
			}
			Object data=Utils.getProperty(o, property);
			Object mappingData=mappingData(mappingMap,data);
			List<Object> rowList=null;
			if(map.containsKey(data)){
				rowList=map.get(data);
			}else{
				rowList=new ArrayList<Object>();
				map.put(data, rowList);
				if(mappingData==null){
					list.add(new BindData(data,rowList));
				}else{
					list.add(new BindData(data,mappingData,rowList));
				}
			}
			rowList.add(o);
		}
		if(list.size()==0){
			List<Object> rowList=new ArrayList<Object>();
			rowList.add(new HashMap<String,Object>());
			list.add(new BindData("",rowList));
		}
		if(list.size()>1){
			Order order=expr.getOrder();
			orderBindDataList(list, order);
		}
		return list;
	}
}
