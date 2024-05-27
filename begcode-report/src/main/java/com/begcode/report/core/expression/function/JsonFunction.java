
package com.begcode.report.core.expression.function;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.begcode.report.core.Utils;
import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.expression.model.data.BindDataListExpressionData;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.model.Cell;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年1月3日
 */
@Component
public class JsonFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context, Cell currentCell){
		if(dataList.size()!=2){
			return null;
		}
		String obj = buildData(dataList.get(0));
		String property=buildData(dataList.get(1));

		if(obj==null || property==null || obj.equals("") || property.equals("")){
			return null;
		}
		ObjectMapper mapper=new ObjectMapper();
		try{
			Map<?,?> map=mapper.readValue(obj, HashMap.class);
			return Utils.getProperty(map, property);
		}catch(Exception ex){
			throw new ReportException(ex);
		}
	}

	private String buildData(ExpressionData<?> exprData) {
		String obj=null;
		if(exprData instanceof ObjectExpressionData){
			ObjectExpressionData objData=(ObjectExpressionData)exprData;
			Object data=objData.getData();
			if(data!=null){
				obj=data.toString();
			}
		}else if(exprData instanceof ObjectListExpressionData){
			ObjectListExpressionData listData=(ObjectListExpressionData)exprData;
			List<?> list=listData.getData();
			if(list.size()==1){
				Object data=list.get(0);
				if(data!=null){
					obj=data.toString();
				}
			}
		}else if(exprData instanceof BindDataListExpressionData){
			BindDataListExpressionData listData=(BindDataListExpressionData)exprData;
			List<BindData> list=listData.getData();
			if(list.size()==1){
				Object data=list.get(0).getValue();
				if(data!=null){
					obj=data.toString();
				}
			}
		}
		return obj;
	}

	@Override
	public String name() {
		return "json";
	}
}
