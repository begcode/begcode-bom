
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年12月5日
 */
public class ReportParseException extends ReportException {
	private static final long serialVersionUID = -8757106306597844487L;
	public ReportParseException(Exception ex) {
		super(ex);
	}
	public ReportParseException(String msg) {
		super(msg);
	}
}
