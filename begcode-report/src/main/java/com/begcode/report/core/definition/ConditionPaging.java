
package com.begcode.report.core.definition;

/**
 * @author Jacky.gao
 * @since 2017年6月19日
 */
public class ConditionPaging {
	private PagingPosition position;
	/**
	 * 当position为after时，line用来指定当前行后多少行进行分页
	 */
	private int line;

	public PagingPosition getPosition() {
		return position;
	}
	public void setPosition(PagingPosition position) {
		this.position = position;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}
