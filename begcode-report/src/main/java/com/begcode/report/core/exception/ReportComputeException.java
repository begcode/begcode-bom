
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年12月26日
 */
public class ReportComputeException extends ReportException {
	private static final long serialVersionUID = -5079596691655241415L;
	public ReportComputeException(Exception ex) {
		super(ex);
	}
	public ReportComputeException(String msg) {
		super(msg);
	}
}
