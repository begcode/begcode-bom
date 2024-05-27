
package com.begcode.report.core.build.aggregate;


import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月20日
 */
public class RegroupAggregate extends GroupAggregate {
	@Override
	public List<BindData> aggregate(DatasetExpression expr, Cell cell, Context context) {
		List<?> objList=context.getDatasetData(expr.getDatasetName());
		return doAggregate(expr, cell, context,objList);
	}
}
