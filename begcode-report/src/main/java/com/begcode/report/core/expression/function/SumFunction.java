
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
public class SumFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell) {
		if(dataList==null || dataList.size()==0){
			return null;
		}
		Object singleData=null;
		BigDecimal total=new BigDecimal(0);
		for(ExpressionData<?> exprData:dataList){
			if(exprData instanceof ObjectListExpressionData){
				ObjectListExpressionData listExpr=(ObjectListExpressionData)exprData;
				List<?> list=listExpr.getData();
				for(Object obj:list){
					if(obj==null || StringUtils.isBlank(obj.toString())){
						continue;
					}
					singleData=obj;
					BigDecimal bigData= Utils.toBigDecimal(obj);
					total=total.add(bigData);
				}
			}else if(exprData instanceof ObjectExpressionData){
				Object obj=exprData.getData();
				singleData=obj;
				if(obj!=null && StringUtils.isNotBlank(obj.toString())){
					BigDecimal bigData=Utils.toBigDecimal(obj);
					total=total.add(bigData);
				}
			}else if(exprData instanceof BindDataListExpressionData){
				BindDataListExpressionData data=(BindDataListExpressionData)exprData;
				List<BindData> bindDataList=data.getData();
				for(BindData bindData:bindDataList){
					Object obj=bindData.getValue();
					if(obj==null || StringUtils.isBlank(obj.toString())){
						continue;
					}
					singleData=obj;
					BigDecimal bigData=Utils.toBigDecimal(obj);
					total=total.add(bigData);
				}
			}
		}
		if(dataList.size()==1){
			if(singleData==null || singleData.equals("")){
				return "";
			}
		}
		return total;
	}

	@Override
	public String name() {
		return "sum";
	}
}
