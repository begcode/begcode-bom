
package com.begcode.report.core.definition.value;

import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
public class DatasetValue extends DatasetExpression implements Value{
	private static final long serialVersionUID = 1892973888854385049L;

	@Override
	public ValueType getType() {
		return ValueType.dataset;
	}

	@Override
	public String getValue() {
		StringBuffer sb=new StringBuffer();
		sb.append(getDatasetName());
		sb.append(".");
		sb.append(getAggregate().name());
		sb.append("(");
		String prop=getProperty();
		if(prop!=null){
			if(prop.length()>13){
				prop=prop.substring(0,10)+"...";
			}
			sb.append(prop);
		}
		sb.append(")");
		return sb.toString();
	}
}
