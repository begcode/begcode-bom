
package com.begcode.report.core.export.pdf.font;
/**
 * @author Jacky.gao
 * @since 2014年4月22日
 */
public interface FontRegister {

	/**
	 * 字体文件相对路径
	 *
	 * @return
	 */
	default String path() {
		return "static/report-font/";
	}

	/**
	 * @return 返回自定义的字体名称
	 */
	String getFontName();
	/**
	 * 返回字体所在位置，需要注意的是字体文件需要放置到classpath下，这里返回的值就是该字体文件所在classpath下位置即可
	 * @return 返回字体所在位置
	 */
	String getFontPath();
}
