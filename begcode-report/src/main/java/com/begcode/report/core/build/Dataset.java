
package com.begcode.report.core.build;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class Dataset {
	private String name;
	private List<?> data;

	public Dataset(String name, List<?> data) {
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public List<?> getData() {
		return data;
	}
}
