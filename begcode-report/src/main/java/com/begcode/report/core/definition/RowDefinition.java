
package com.begcode.report.core.definition;

import com.begcode.report.core.model.Row;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class RowDefinition implements Comparable<RowDefinition>,Serializable{
	private static final long serialVersionUID = 8326047944994933822L;
	private int rowNumber;
	private int height;
	private Band band;
	protected Row newRow(List<Row> rows){
		Row row=new Row(rows);
		row.setHeight(height);
		row.setBand(band);
		row.setRowKey("r"+rowNumber);
		return row;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	@Override
	public int compareTo(RowDefinition o) {
		return rowNumber-o.getRowNumber();
	}
}
