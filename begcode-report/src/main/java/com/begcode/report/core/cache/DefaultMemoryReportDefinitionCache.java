
package com.begcode.report.core.cache;


import com.begcode.report.console.cache.CacheProperties;
import com.begcode.report.core.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
@Component
public class DefaultMemoryReportDefinitionCache implements ReportDefinitionCache {
    private Map<String, ReportDefinition> reportMap = new ConcurrentHashMap<String, ReportDefinition>();

    @Override
    public ReportDefinition getReportDefinition(String file) {
        return reportMap.get(sessionId() + file);
    }

    @Override
    public void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        if (reportMap.containsKey(sessionId() + file)) {
            reportMap.remove(sessionId() + file);
        }
        reportMap.put(sessionId() + file, reportDefinition);
    }

    @Override
    public void removeReportDefinition(String file) {
        reportMap.remove(sessionId() + file);
    }

    @Override
	public boolean disabled() {
		return !CacheProperties.isEnableRedis();
	}
}
