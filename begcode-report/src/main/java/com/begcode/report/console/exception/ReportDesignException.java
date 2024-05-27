
package com.begcode.report.console.exception;


import com.begcode.report.core.exception.ReportException;

public class ReportDesignException extends ReportException {
	private static final long serialVersionUID = 4046240733455821337L;
	public ReportDesignException(Exception ex) {
		super(ex);
	}
	public ReportDesignException(String msg) {
		super(msg);
	}
}
