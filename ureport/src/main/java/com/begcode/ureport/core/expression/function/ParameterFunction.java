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
package com.begcode.ureport.core.expression.function;

import com.begcode.ureport.core.build.Context;
import com.begcode.ureport.core.exception.ReportComputeException;
import com.begcode.ureport.core.expression.model.data.ExpressionData;
import com.begcode.ureport.core.expression.model.data.ObjectExpressionData;
import com.begcode.ureport.core.expression.model.data.ObjectListExpressionData;
import com.begcode.ureport.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年5月21日
 */
@Component
public class ParameterFunction implements Function {
    @Override
    public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
        if (dataList == null || dataList.size() < 1) {
            throw new ReportComputeException("Function [param] need one parameter.");
        }
        Object obj = null;
        ExpressionData<?> data = dataList.get(0);
        if (data instanceof ObjectExpressionData) {
            ObjectExpressionData objData = (ObjectExpressionData) data;
            obj = objData.getData();
        } else if (data instanceof ObjectListExpressionData) {
            ObjectListExpressionData listData = (ObjectListExpressionData) data;
            List<?> list = listData.getData();
            if (list.size() > 0) {
                obj = list.get(0);
            }
        }
        if (obj == null) {
            throw new ReportComputeException("Function [param] need one parameter.");
        }
        Map<String, Object> map = context.getParameters();
        if (map == null) {
            return null;
        }
        return map.get(obj.toString());
    }

    @Override
    public String name() {
        return "param";
    }
}
