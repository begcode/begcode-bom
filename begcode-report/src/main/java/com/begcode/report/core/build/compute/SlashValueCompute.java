
package com.begcode.report.core.build.compute;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.value.SlashValue;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Image;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年3月14日
 */
public class SlashValueCompute implements ValueCompute {
	@Override
	public List<BindData> compute(Cell cell, Context context) {
		List<BindData> list=new ArrayList<BindData>();
		SlashValue v=(SlashValue)cell.getValue();
		Image img=new Image(v.getBase64Data(),"slash.png",0,0);
		BindData bindData=new BindData(img);
		list.add(bindData);
		/*String key=SlashBuilder.buildKey(context.getReport().getReportFullName(), cell.getName());
		Resource res=new Resource(key);
		BindData bindData=new BindData(res);
		list.add(bindData);*/
		return list;
	}


	@Override
	public ValueType type() {
		return ValueType.slash;
	}
}
