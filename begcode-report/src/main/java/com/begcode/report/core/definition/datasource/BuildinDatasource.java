
package com.begcode.report.core.definition.datasource;

import java.sql.Connection;

/**
 * @author Jacky.gao
 * @since 2017年2月9日
 */
public interface BuildinDatasource {
	/**
	 * @return 返回数据源名称
	 */
	String name();
	/**
	 * @return 返回当前采用数据源的一个连接
	 */
	Connection getConnection();
}
