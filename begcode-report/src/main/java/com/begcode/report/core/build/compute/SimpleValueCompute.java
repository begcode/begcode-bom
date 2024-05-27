
package com.begcode.report.core.build.compute;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
public class SimpleValueCompute implements ValueCompute {

	@Override
	public List<BindData> compute(Cell cell, Context context) {
		List<BindData> list=new ArrayList<BindData>();
		list.add(new BindData(cell.getValue().getValue(),null,null));
		return list;
	}

	@Override
	public ValueType type() {
		return ValueType.simple;
	}
}
