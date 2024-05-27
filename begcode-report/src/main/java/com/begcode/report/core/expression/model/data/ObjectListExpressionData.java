
package com.begcode.report.core.expression.model.data;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月3日
 */
public class ObjectListExpressionData implements ExpressionData<List<?>> {
	private List<?> list;
	public ObjectListExpressionData(List<?> list) {
		this.list=list;
	}
	@Override
	public List<?> getData() {
		return list;
	}
}
