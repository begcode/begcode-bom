
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class CellDependencyException extends ReportException {

	private static final long serialVersionUID = 5765713360910995235L;

	public CellDependencyException() {
		super("Report cells has cyclic dependency.");
	}
}
