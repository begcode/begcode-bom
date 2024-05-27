
package com.begcode.report.core.definition.value;

/**
 * 普通字符串，或者是表达式
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class SimpleValue implements Value{

	private static final long serialVersionUID = 1L;

	private String value;
	public SimpleValue(String value) {
		this.value=value;
	}

	@Override
	public ValueType getType() {
		return ValueType.simple;
	}

	@Override
	public String getValue() {
		return value;
	}
}
