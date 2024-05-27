
package com.begcode.report.core.definition;

import com.begcode.report.core.model.Column;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class ColumnDefinition implements Comparable<ColumnDefinition>, Serializable {

	private static final long serialVersionUID = 1L;

	private int columnNumber;
	private int width;
	private boolean hide;

	protected Column newColumn(List<Column> columns){
		Column col=new Column(columns);
		col.setWidth(width);
		return col;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	@Override
	public int compareTo(ColumnDefinition o) {
		return columnNumber-o.getColumnNumber();
	}
}
