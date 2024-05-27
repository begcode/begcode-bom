
package com.begcode.report.core.export;

import java.io.OutputStream;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年3月20日
 */
public class ExportConfigureImpl implements ExportConfigure{
	private String file;
	private OutputStream outputStream;
	private Map<String,Object> parameters;
	public ExportConfigureImpl(String file,Map<String,Object> parameters,OutputStream outputStream) {
		this.file=file;
		this.parameters=parameters;
		this.outputStream=outputStream;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public String getFile() {
		return file;
	}
}
