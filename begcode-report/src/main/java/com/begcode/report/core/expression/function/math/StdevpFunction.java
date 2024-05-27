
package com.begcode.report.core.expression.function.math;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 求标准差
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class StdevpFunction extends MathFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		List<BigDecimal> list=buildDataList(dataList);
		BigDecimal total=new BigDecimal(0);
		for(BigDecimal bigData:list){
			total=total.add(bigData);
		}
		int size=list.size();
		BigDecimal avg=total.divide(new BigDecimal(size),8,BigDecimal.ROUND_HALF_UP);
		double sum=0;
		for(BigDecimal bigData:list){
			BigDecimal data=bigData.subtract(avg);
			sum+=Math.pow(data.doubleValue(), 2);
		}
		BigDecimal result=new BigDecimal(sum);
		result=result.divide(new BigDecimal(size),8,BigDecimal.ROUND_HALF_UP);
		return Math.sqrt(result.doubleValue());
	}


	@Override
	public String name() {
		return "stdevp";
	}
}
