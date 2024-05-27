
package com.begcode.report.core;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2017年2月24日
 */
public class Range implements Serializable{
	private static final long serialVersionUID = -4547468301777433024L;
	private int start=-1;
	private int end;
	public Range() {
	}
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
}
