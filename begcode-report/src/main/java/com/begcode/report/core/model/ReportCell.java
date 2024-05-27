
package com.begcode.report.core.model;

import com.begcode.report.core.definition.CellStyle;
import com.begcode.report.core.definition.Expand;
import com.begcode.report.core.definition.value.Value;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年1月19日
 */
public interface ReportCell {
	CellStyle getCellStyle();
	String getName();
	int getRowSpan();
	int getColSpan();
	Row getRow();
	Column getColumn();
	Object getData();
	Value getValue();
	Expand getExpand();
	List<Object> getBindData();
}
