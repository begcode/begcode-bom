
package com.begcode.report.core.build;

import com.begcode.report.core.build.aggregate.*;
import com.begcode.report.core.definition.value.AggregateType;
import com.begcode.report.core.exception.CellComputeException;
import com.begcode.report.core.expression.model.expr.dataset.DatasetExpression;
import com.begcode.report.core.model.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年12月26日
 */
public class DatasetUtils {
	private static Map<AggregateType, Aggregate> aggregates=new HashMap<AggregateType,Aggregate>();
	static{
		aggregates.put(AggregateType.group,new GroupAggregate());
		aggregates.put(AggregateType.select,new SelectAggregate());
		aggregates.put(AggregateType.reselect,new ReselectAggregate());
		aggregates.put(AggregateType.regroup,new RegroupAggregate());
		aggregates.put(AggregateType.avg,new AvgAggregate());
		aggregates.put(AggregateType.count,new CountAggregate());
		aggregates.put(AggregateType.sum,new SumAggregate());
		aggregates.put(AggregateType.min,new MinAggregate());
		aggregates.put(AggregateType.max,new MaxAggregate());
		aggregates.put(AggregateType.customgroup,new CustomGroupAggregate());
	}

	public static List<BindData> computeDatasetExpression(DatasetExpression expr, Cell cell, Context context){
		AggregateType aggregateType=expr.getAggregate();
		Aggregate aggregate=aggregates.get(aggregateType);
		if(aggregate!=null){
			return aggregate.aggregate(expr,cell,context);
		}else{
			throw new CellComputeException("Unknow aggregate : "+aggregateType);
		}
	}
}
