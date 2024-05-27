
package com.begcode.report.core.expression.model.data;

/**
 * @author Jacky.gao
 * @since 2017年1月1日
 */
public class ObjectExpressionData implements ExpressionData<Object> {
	private Object data;
	public ObjectExpressionData(Object data) {
		this.data=data;
	}
	@Override
	public Object getData() {
		return data;
	}
}
