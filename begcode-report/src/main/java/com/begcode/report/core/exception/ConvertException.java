
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年12月22日
 */
public class ConvertException extends ReportException {
	private static final long serialVersionUID = 8681316352205087220L;
	public ConvertException(Exception ex) {
		super(ex);
	}
	public ConvertException(String msg) {
		super(msg);
	}
}
