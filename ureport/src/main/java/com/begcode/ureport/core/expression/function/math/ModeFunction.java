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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求众数
 *
 * @author Jacky.gao
 * @since 2017年1月23日
 */
@Component
public class ModeFunction extends MathFunction {

    @Override
    public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
        int max = 0;
        BigDecimal result = null;
        List<BigDecimal> list = buildDataList(dataList);
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        for (BigDecimal bigData : list) {
            if (bigData == null) continue;
            double d = bigData.doubleValue();
            if (map.containsKey(d)) {
                int count = map.get(d);
                count++;
                map.put(d, count);
                if (count > max) {
                    max = count;
                    result = bigData;
                }
            } else {
                map.put(d, 1);
                if (result == null) {
                    max = 1;
                    result = bigData;
                }
            }
        }
        return result;
    }

    @Override
    public String name() {
        return "mode";
    }
}
