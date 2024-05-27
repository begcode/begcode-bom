
package com.begcode.report.core.export;

import com.begcode.report.core.export.html.HtmlReport;

import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
public interface ExportManager {
	String BEAN_ID="ureport.exportManager";
	/**
	 * 导出Html报表
	 * @param file 报表模版文件名
	 * @param contextPath 当前项目的context path
	 * @param parameters 参数
	 * @return 返回一个HtmlReport对象，里面有报表产生的HTML及相关CSS
	 */
	HtmlReport exportHtml(String file, String contextPath, Map<String, Object> parameters);
	/**
	 * 导出指定页码的Html报表
	 * @param file 报表模版文件名
	 * @param contextPath 当前项目的context path
	 * @param parameters 参数
	 * @param pageIndex 页码
	 * @return 返回一个HtmlReport对象，里面有报表产生的HTML及相关CSS
	 */
	HtmlReport exportHtml(String file, String contextPath, Map<String, Object> parameters, int pageIndex);
	/**
	 * 导出PDF报表
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportPdf(ExportConfigure config);
	/**
	 * 不分页导出Excel
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcel(ExportConfigure config);
	/**
	 * 不分页导出Excel97格式文件
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcel97(ExportConfigure config);
	/**
	 * 分页导出Excel
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcelWithPaging(ExportConfigure config);
	/**
	 * 分页导出Excel
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcel97WithPaging(ExportConfigure config);
	/**
	 * 分页分Sheet导出Excel
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcelWithPagingSheet(ExportConfigure config);
	/**
	 * 分页分Sheet导出Excel
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportExcel97WithPagingSheet(ExportConfigure config);
	/**
	 * 导出Word
	 * @param config 包含报表模版文件名、参数等信息的配置对象
	 */
	void exportWord(ExportConfigure config);
}
