
package com.begcode.report.core;


import com.begcode.report.core.model.ReportCell;

import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年1月19日
 */
public interface CellRenderer {
	Object doRender(ReportCell cell, Map<String, Object> parameters);
}
