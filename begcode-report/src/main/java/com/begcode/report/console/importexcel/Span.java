
package com.begcode.report.console.importexcel;

/**
 * @author Jacky.gao
 * @since 2017年5月26日
 */
public class Span {
	private int rowSpan;
	private int colSpan;
	public Span(int rowSpan,int colSpan) {
		this.rowSpan=rowSpan;
		this.colSpan=colSpan;
	}
	public int getRowSpan() {
		return rowSpan;
	}
	public int getColSpan() {
		return colSpan;
	}
}
