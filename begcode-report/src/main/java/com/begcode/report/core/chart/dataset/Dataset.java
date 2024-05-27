
package com.begcode.report.core.chart.dataset;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public interface Dataset {
	String buildDataJson(Context context, Cell cell);
	String getType();
}
