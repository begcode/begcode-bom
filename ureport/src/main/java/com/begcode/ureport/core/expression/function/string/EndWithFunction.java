package com.begcode.ureport.core.expression.function.string;

import com.begcode.ureport.core.build.Context;
import com.begcode.ureport.core.exception.ReportComputeException;
import com.begcode.ureport.core.expression.model.data.ExpressionData;
import com.begcode.ureport.core.model.Cell;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: summer
 * @Date: 2022/10/23 10:44
 * @Description:
 **/
@Component
public class EndWithFunction extends StringFunction {
    @Override
    public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
        if (dataList.size() < 1) {
            throw new ReportComputeException("[" + name() + "]函数需要两个参数值");
        }
        String str = buildString(dataList.get(0));
        String suffix = buildString(dataList.get(1));
        return StringUtils.endsWith(str, suffix);
    }

    @Override
    public String name() {
        return "endWith";
    }
}
