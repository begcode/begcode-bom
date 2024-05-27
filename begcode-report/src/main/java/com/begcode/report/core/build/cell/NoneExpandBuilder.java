
package com.begcode.report.core.build.cell;


import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.ConditionPropertyItem;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class NoneExpandBuilder implements CellBuilder {

	@Override
	public Cell buildCell(List<BindData> dataList, Cell cell, Context context) {
		if(dataList.size()==1){
			BindData bindData=dataList.get(0);
			cell.setData(bindData.getValue());
			cell.setFormatData(bindData.getLabel());
			cell.setBindData(bindData.getDataList());
		}else{
			Object obj=null;
			List<Object> bindData=null;
			for(BindData data:dataList){
				if(obj==null){
					if(data.getLabel()==null){
						obj=data.getValue();
					}else{
						obj=data.getLabel();
					}
				}else{
					if(data.getLabel()==null){
						obj=obj+","+data.getValue();
					}else{
						obj=obj+","+data.getLabel();
					}
				}
				bindData=data.getDataList();
			}
			cell.setData(obj);
			cell.setBindData(bindData);
		}
		List<ConditionPropertyItem> conditionPropertyItems=cell.getConditionPropertyItems();
		if(conditionPropertyItems!=null && conditionPropertyItems.size()>0){
			context.getReport().getLazyComputeCells().add(cell);
		}else{
			cell.doFormat();
			cell.doDataWrapCompute(context);
		}
		return cell;
	}
}
