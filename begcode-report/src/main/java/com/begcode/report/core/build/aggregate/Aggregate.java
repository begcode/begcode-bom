
package com.begcode.report.core.build.aggregate;

import com.begcode.report.core.Utils;
import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.Order;
import com.begcode.report.core.definition.value.Value;
import com.begcode.report.core.expression.model.Condition;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
public abstract class Aggregate {
	public abstract List<BindData> aggregate(DatasetExpression expr, Cell cell, Context context);

	protected Condition getCondition(Cell cell){
		Value value=cell.getValue();
		Condition condition=null;
		if(value instanceof DatasetExpression){
			DatasetExpression dsValue=(DatasetExpression)value;
			condition=dsValue.getCondition();
		}
		return condition;
	}

	protected Object mappingData(Map<String,String> mappingMap,Object data){
		if(mappingMap==null || data==null){
			return data;
		}
		String label=mappingMap.get(data.toString());
		if(label==null){
			return data;
		}
		return label;
	}

	protected boolean doCondition(Condition condition,Cell cell,Object obj,Context context){
		if(condition==null){
			return true;
		}
		return condition.filter(cell,cell, obj, context);
	}

	protected void orderBindDataList(List<BindData> list, final Order order) {
		if(order==null || order.equals(Order.none)){
			return;
		}
		Collections.sort(list, new Comparator<BindData>() {
			@Override
			public int compare(BindData o1, BindData o2) {
				Object data1=o1.getValue();
				Object data2=o2.getValue();
				if(data1==null || data2==null){
					return 1;
				}
				if(data1 instanceof Date){
					Date d1=(Date)data1;
					Date d2=(Date)data2;
					if(order.equals(Order.asc)){
						return d1.compareTo(d2);
					}else{
						return d2.compareTo(d1);
					}
				}else if(data1 instanceof Number){
					BigDecimal n1= Utils.toBigDecimal(data1);
					BigDecimal n2=Utils.toBigDecimal(data2);
					if(order.equals(Order.asc)){
						return n1.compareTo(n2);
					}else{
						return n2.compareTo(n1);
					}
				}else{
					String str1=data1.toString();
					String str2=data2.toString();
					if(order.equals(Order.asc)){
						return str1.compareTo(str2);
					}else{
						return str2.compareTo(str1);
					}
				}
			}
		});
	}
}
