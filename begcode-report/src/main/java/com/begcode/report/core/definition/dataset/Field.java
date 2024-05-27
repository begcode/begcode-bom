
package com.begcode.report.core.definition.dataset;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2016年12月30日
 */
public class Field implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	public Field(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
