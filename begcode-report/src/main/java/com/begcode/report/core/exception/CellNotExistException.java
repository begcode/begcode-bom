
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年11月19日
 */
public class CellNotExistException extends ReportException {
	private static final long serialVersionUID = -2436297948073253411L;
	public CellNotExistException(String cellName) {
		super("Cell ["+cellName+"] not exist.");
	}
}
