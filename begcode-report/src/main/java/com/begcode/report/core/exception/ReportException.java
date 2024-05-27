
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class ReportException extends RuntimeException{
	private static final long serialVersionUID = 2970559370876740683L;
	public ReportException(String msg) {
		super(msg);
	}
	public ReportException(Exception ex) {
		super(ex);
		ex.printStackTrace();
	}
}
