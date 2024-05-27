
package com.begcode.report.core.export;

import java.io.OutputStream;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年3月20日
 */
public interface ExportConfigure {
	OutputStream getOutputStream();
	String getFile();
	Map<String,Object> getParameters();
}
