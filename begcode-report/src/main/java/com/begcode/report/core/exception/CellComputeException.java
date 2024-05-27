
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年12月5日
 */
public class CellComputeException extends ReportException {
	private static final long serialVersionUID = -1363254031247074841L;
	public CellComputeException(Exception ex) {
		super(ex);
	}
	public CellComputeException(String msg) {
		super(msg);
	}
}
