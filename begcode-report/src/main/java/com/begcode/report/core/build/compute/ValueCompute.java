
package com.begcode.report.core.build.compute;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
public interface ValueCompute {
	List<BindData> compute(Cell cell, Context context);
	ValueType type();
}
