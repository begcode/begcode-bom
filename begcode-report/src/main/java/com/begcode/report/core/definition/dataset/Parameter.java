
package com.begcode.report.core.definition.dataset;

import com.begcode.report.core.definition.datasource.DataType;

/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
public class Parameter {
	private String name;
	private DataType type;
	private String defaultValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
