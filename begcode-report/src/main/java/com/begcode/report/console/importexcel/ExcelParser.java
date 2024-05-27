
package com.begcode.report.console.importexcel;

import com.begcode.report.core.definition.ReportDefinition;

import java.io.InputStream;

/**
 * @author Jacky.gao
 * @since 2017年5月27日
 */
public abstract class ExcelParser {
	public abstract ReportDefinition parse(InputStream inputStream) throws Exception;
	public abstract boolean support(String name);
}
