
package com.begcode.report.core.expression.function;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.data.BindDataListExpressionData;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
@Component
public class MaxFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		if(dataList==null || dataList.size()==0){
			return null;
		}
		BigDecimal value=null;
		for(ExpressionData<?> exprData:dataList){
			if(exprData instanceof ObjectListExpressionData){
				ObjectListExpressionData listExpr=(ObjectListExpressionData)exprData;
				List<?> list=listExpr.getData();
				for(Object obj:list){
					if(obj==null || StringUtils.isBlank(obj.toString())){
						continue;
					}
					BigDecimal bigData= Utils.toBigDecimal(obj);
					if(value==null){
						value=bigData;
					}else{
						int result=value.compareTo(bigData);
						if(result==-1){
							value=bigData;
						}
					}
				}
			}else if(exprData instanceof ObjectExpressionData){
				Object obj=exprData.getData();
				if(obj!=null && StringUtils.isNotBlank(obj.toString())){
					value=Utils.toBigDecimal(obj);
				}
			}else if(exprData instanceof BindDataListExpressionData){
				BindDataListExpressionData bindDataList=(BindDataListExpressionData)exprData;
				List<BindData> list=bindDataList.getData();
				for(BindData bindData:list){
					Object obj=bindData.getValue();
					if(obj==null || StringUtils.isBlank(obj.toString())){
						continue;
					}
					BigDecimal bigData=Utils.toBigDecimal(obj);
					if(value==null){
						value=bigData;
					}else{
						int result=value.compareTo(bigData);
						if(result==-1){
							value=bigData;
						}
					}
				}
			}
		}
		return value;
	}

	@Override
	public String name() {
		return "max";
	}
}
