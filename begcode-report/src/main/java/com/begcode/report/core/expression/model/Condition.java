
package com.begcode.report.core.expression.model;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public interface Condition {
	/**
	 * @param cell 当前Condition所在的单元格
	 * @param currentCell 当前Condition正在处理的单元格
	 * @param obj 要判断的对象
	 * @param context 上下文对象
	 * @return 返回是否符合条件
	 */
	boolean filter(Cell cell, Cell currentCell, Object obj, Context context);
}
