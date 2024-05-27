
package com.begcode.report.core.build.paging;


import com.begcode.report.core.model.Row;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月17日
 */
public class RepeatRows {
	private List<Row> headerRepeatRows;
	private List<Row> footerRepeatRows;

	public RepeatRows(List<Row> headerRepeatRows, List<Row> footerRepeatRows) {
		this.headerRepeatRows = headerRepeatRows;
		this.footerRepeatRows = footerRepeatRows;
	}
	public List<Row> getFooterRepeatRows() {
		return footerRepeatRows;
	}
	public List<Row> getHeaderRepeatRows() {
		return headerRepeatRows;
	}
}
