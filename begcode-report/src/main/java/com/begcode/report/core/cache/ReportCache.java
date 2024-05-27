
package com.begcode.report.core.cache;


/**
 * @author Jacky.gao
 * @since 2017年3月8日
 */
public interface ReportCache {
	Object getObject(String file);
	void storeObject(String file, Object obj);
	boolean disabled();
}
