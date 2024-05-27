
package com.begcode.report.core.parser;

import com.begcode.report.core.definition.CellDefinition;

/**
 * @author Jacky.gao
 * @since 2017年2月27日
 */
public class BuildUtils {
	public static int buildRowNumberEnd(CellDefinition cell, int rowNumber){
		int rowSpan=cell.getRowSpan();
		rowSpan=rowSpan>0 ? rowSpan-1 : rowSpan;
		return rowNumber+rowSpan;
	}
	public static int buildColNumberEnd(CellDefinition cell,int colNumber){
		int colSpan=cell.getColSpan();
		colSpan=colSpan>0 ? colSpan-1 : colSpan;
		return colNumber+colSpan;
	}
}
