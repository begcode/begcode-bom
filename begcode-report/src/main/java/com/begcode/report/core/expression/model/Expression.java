
package com.begcode.report.core.expression.model;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public interface Expression extends Serializable{
	ExpressionData<?> execute(Cell cell, Cell currentCell, Context context);
}
