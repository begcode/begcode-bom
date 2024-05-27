
package com.begcode.report.core.model;

import com.begcode.report.core.definition.CellStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public abstract class Line{
	private CellStyle customCellStyle;
	private List<Cell> cells=new ArrayList<Cell>();

	public CellStyle getCustomCellStyle() {
		return customCellStyle;
	}

	public void setCustomCellStyle(CellStyle customCellStyle) {
		this.customCellStyle = customCellStyle;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
}
