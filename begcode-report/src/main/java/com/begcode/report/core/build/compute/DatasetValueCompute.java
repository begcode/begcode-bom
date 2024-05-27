
package com.begcode.report.core.build.compute;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.build.DatasetUtils;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
public class DatasetValueCompute implements ValueCompute {
	@Override
	public List<BindData> compute(Cell cell, Context context) {
		DatasetExpression expr=(DatasetExpression)cell.getValue();
		return DatasetUtils.computeDatasetExpression(expr, cell, context);
	}
	@Override
	public ValueType type() {
		return ValueType.dataset;
	}
}
