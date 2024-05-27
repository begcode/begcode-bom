
package com.begcode.report.core.definition.value;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.begcode.report.core.expression.model.Condition;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年3月28日
 */
public class GroupItem {
	private String name;
	@JsonIgnore
	private Condition condition;
	/**
	 * 此属性给设计器使用，引擎不使用该属性
	 */
	private List<Condition> conditions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
}
