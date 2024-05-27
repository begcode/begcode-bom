
package com.begcode.report.core.exception;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class DatasetUndefinitionException extends ReportException {
	private static final long serialVersionUID = -1897331038232057797L;

	public DatasetUndefinitionException(String datasetName) {
		super("Dataset ["+datasetName+"] not definition.");
	}
}
