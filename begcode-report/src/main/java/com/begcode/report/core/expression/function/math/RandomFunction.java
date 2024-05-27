
package com.begcode.report.core.expression.function.math;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class RandomFunction extends MathFunction {
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		int feed=0;
		if(dataList.size()>0){
			BigDecimal data=buildBigDecimal(dataList);
			feed=data.intValue();
		}
		if(feed==0){
			return Math.random();
		}
		return RandomUtils.nextInt(feed);
	}

	@Override
	public String name() {
		return "random";
	}
}
