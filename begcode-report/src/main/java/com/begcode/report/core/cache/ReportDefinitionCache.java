
package com.begcode.report.core.cache;

import com.begcode.report.console.exception.ReportDesignException;
import com.begcode.report.core.definition.ReportDefinition;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
public interface ReportDefinitionCache {
	ReportDefinition getReportDefinition(String file);

	void cacheReportDefinition(String file, ReportDefinition reportDefinition);

	void removeReportDefinition(String file);

	/**
	 * 是否开启
	 *
	 * @return
	 */
	boolean disabled();

	/**
	 * 获取sessionId
	 *
	 * @return
	 */
	default String sessionId() {
		try {
			String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
			return sessionId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ReportDesignException("sessionId获取失败");
		}
	}
}
