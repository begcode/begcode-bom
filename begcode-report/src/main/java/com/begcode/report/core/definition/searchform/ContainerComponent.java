
package com.begcode.report.core.definition.searchform;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public abstract class ContainerComponent implements Component {
	protected List<Component> children;
	public void setChildren(List<Component> children) {
		this.children = children;
	}
	public List<Component> getChildren() {
		return children;
	}
}
