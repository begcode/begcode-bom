
package com.begcode.report.core.cache;

import com.begcode.report.console.cache.CacheProperties;
import com.begcode.report.core.chart.ChartData;
import com.begcode.report.core.definition.ReportDefinition;
import com.begcode.report.core.utils.SpringContextUtils;

import java.util.Collection;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年3月8日
 */
public class CacheUtils {
    private static ReportCache reportCache;
    private static ReportDefinitionCache reportDefinitionCache;
    private static String CHART_DATA_key = "_chart_data_";

    @SuppressWarnings("unchecked")
    public static ChartData getChartData(String chartId) {
        String key = CHART_DATA_key;
        if (reportCache != null) {
            Map<String, ChartData> chartDataMap = (Map<String, ChartData>) reportCache.getObject(key);
            if (chartDataMap != null) {
                return chartDataMap.get(chartId);
            }
        }
        return null;
    }

    public static void storeChartDataMap(Map<String, ChartData> map) {
        String key = CHART_DATA_key;
        if (reportCache != null) {
            reportCache.storeObject(key, map);
        }
    }

    public static Object getObject(String file) {
        if (reportCache != null) {
            return reportCache.getObject(file);
        }
        return null;
    }

    public static void storeObject(String file, Object obj) {
        if (reportCache != null) {
            reportCache.storeObject(file, obj);
        }
    }

    public static ReportDefinition getReportDefinition(String file) {
        return reportDefinitionCache.getReportDefinition(file);
    }

    public static void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        reportDefinitionCache.cacheReportDefinition(file, reportDefinition);
    }

    public static void removeReportDefinition(String file) {
        reportDefinitionCache.removeReportDefinition(file);
    }

    static {
        Collection<ReportCache> coll = SpringContextUtils.getAllBeansOfType(ReportCache.class);
        for (ReportCache cache : coll) {
            if (cache.disabled()) {
                reportCache = cache;
                break;
            }
        }

        Collection<ReportDefinitionCache> reportCaches = SpringContextUtils.getAllBeansOfType(ReportDefinitionCache.class);
        if (CacheProperties.isEnableRedis()) {
            reportDefinitionCache = reportCaches.stream().filter(r -> r instanceof RedisReportDefinitionCache).findFirst().get();
        } else {
            reportDefinitionCache = reportCaches.stream().filter(r -> r instanceof DefaultMemoryReportDefinitionCache).findFirst().get();
        }
    }
}
