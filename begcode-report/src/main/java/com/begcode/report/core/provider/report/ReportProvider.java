
package com.begcode.report.core.provider.report;

import java.io.InputStream;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
public interface ReportProvider {
	/**
	 * 根据报表名加载报表文件
	 * @param file 报表名称
	 * @return 返回的InputStream
	 */
	InputStream loadReport(String file);
	/**
	 * 根据报表名，删除指定的报表文件
	 * @param file 报表名称
	 */
	void deleteReport(String file);
	/**
	 * 获取所有的报表文件
	 * @return 返回报表文件列表
	 */
	List<ReportFile> getReportFiles();
	/**
	 * 保存报表文件
	 * @param file 报表名称
	 * @param content 报表的XML内容
	 */
	void saveReport(String file, String content);
	/**
	 * @return 返回存储器名称
	 */
	String getName();
	/**
	 * @return 返回是否启用
	 */
	boolean enabled();
	/**
	 * @return 返回报表文件名前缀
	 */
	String getPrefix();
}
