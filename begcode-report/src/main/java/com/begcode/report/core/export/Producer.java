
package com.begcode.report.core.export;

import com.begcode.report.core.model.Report;

import java.io.OutputStream;

/**
 * @author Jacky.gao
 * @since 2016年12月30日
 */
public interface Producer {
	void produce(Report report, OutputStream outputStream);
}
