/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.begcode.ureport.core.expression.function.math;

import com.begcode.ureport.core.build.Context;
import com.begcode.ureport.core.expression.model.data.ExpressionData;
import com.begcode.ureport.core.model.Cell;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 求标准差
 *
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class StdevpFunction extends MathFunction {

    @Override
    public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
        List<BigDecimal> list = buildDataList(dataList);
        BigDecimal total = new BigDecimal(0);
        for (BigDecimal bigData : list) {
            total = total.add(bigData);
        }
        int size = list.size();
        BigDecimal avg = total.divide(new BigDecimal(size), 8, BigDecimal.ROUND_HALF_UP);
        double sum = 0;
        for (BigDecimal bigData : list) {
            BigDecimal data = bigData.subtract(avg);
            sum += Math.pow(data.doubleValue(), 2);
        }
        BigDecimal result = new BigDecimal(sum);
        result = result.divide(new BigDecimal(size), 8, BigDecimal.ROUND_HALF_UP);
        return Math.sqrt(result.doubleValue());
    }


    @Override
    public String name() {
        return "stdevp";
    }
}
