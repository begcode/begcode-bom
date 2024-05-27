
package com.begcode.report.core.definition;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2017年2月26日
 */
public class BlankCellInfo implements Serializable{
	private static final long serialVersionUID = -7492794314898687250L;
	private int offset;
	private int span;
	private boolean parent;
	public BlankCellInfo() {
	}
	public BlankCellInfo(int offset, int span,boolean parent) {
		this.offset=offset;
		this.span = span;
		this.parent=parent;
	}
	public int getOffset() {
		return offset;
	}

	public int getSpan() {
		return span;
	}
	public boolean isParent() {
		return parent;
	}
}
