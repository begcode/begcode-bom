
package com.begcode.report.core.build.cell;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public interface CellBuilder {
	Cell buildCell(List<BindData> dataList, Cell cell, Context context);
}
