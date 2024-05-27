
package com.begcode.report.core.expression;
/**
 * @author Jacky.gao
 * @since 2016年7月26日
 */
public class ErrorInfo {
	private int line;
	private int charPositionInLine;
	private String message;

	public ErrorInfo(int line,int charPositionInLine,String message) {
		this.line = line;
		this.charPositionInLine=charPositionInLine;
		this.message = message;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCharPositionInLine() {
		return charPositionInLine;
	}
	public void setCharPositionInLine(int charPositionInLine) {
		this.charPositionInLine = charPositionInLine;
	}
}
