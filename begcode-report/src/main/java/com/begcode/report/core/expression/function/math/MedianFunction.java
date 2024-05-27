
package com.begcode.report.core.expression.function.math;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 求中位数
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class MedianFunction extends MathFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		List<BigDecimal> list=buildDataList(dataList);
		int size=list.size();
		if(size==1){
			return list.get(0);
		}else if(size==2){
			BigDecimal data=list.get(0).add(list.get(1));
			return data.divide(new BigDecimal(2),8,BigDecimal.ROUND_HALF_UP);
		}
		Collections.sort(list);
		int mode=size % 2;
		if(mode==0){
			int half = size / 2;
			int start=half-1;
			BigDecimal data=list.get(start).add(list.get(half));
			return data.divide(new BigDecimal(2),8,BigDecimal.ROUND_HALF_UP);
		}else{
			int half=size / 2;
			return list.get(half);
		}
	}

	@Override
	public String name() {
		return "median";
	}
}
