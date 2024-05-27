
package com.begcode.report.core.exception;

import com.begcode.report.core.model.ReportCell;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class IllegalCellExpandException extends ReportException{
	private static final long serialVersionUID = -2442986317129037490L;

	public IllegalCellExpandException(ReportCell cell) {
		super("Cell expand is "+cell.getExpand()+" is invalid.");
	}
}
