
package com.begcode.report.core.build.compute;

import java.util.ArrayList;
import java.util.List;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.value.ImageValue;
import com.begcode.report.core.definition.value.Source;
import com.begcode.report.core.definition.value.ValueType;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.image.ImageType;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Image;
import com.begcode.report.core.utils.ImageUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * @author Jacky.gao
 * @since 2017年1月24日
 */
public class ImageValueCompute implements ValueCompute{
	@Override
	public List<BindData> compute(Cell cell, Context context) {
		ImageValue value=(ImageValue)cell.getValue();
		int width=value.getWidth(),height=value.getHeight();
		Source source=value.getSource();
		List<BindData> list=new ArrayList<BindData>();
		if(source.equals(Source.text)){
			String base64Data= ImageUtils.getImageBase64Data(ImageType.image, value.getValue(),width,height);
			list.add(new BindData(new Image(base64Data,value.getValue(),-1,-1)));
		}else{
			Expression expression=value.getExpression();
			ExpressionData<?> data=expression.execute(cell,cell, context);
			Object obj=data.getData();
			if(obj instanceof List){
				List<?> listData=(List<?>)obj;
				for(Object o:listData){
					if(o==null){
						continue;
					}
					String path=null;
					if(o instanceof BindData){
						BindData bindData=(BindData)o;
						Object valueData=bindData.getValue();
						if(valueData!=null){
							path=valueData.toString();
						}
					}else{
						path=o.toString();
					}
					if(StringUtils.isBlank(path)){
						continue;
					}
					String base64Data=ImageUtils.getImageBase64Data(ImageType.image, path,width,height);
					list.add(new BindData(new Image(base64Data,path,-1,-1)));
				}
			}else if(obj instanceof BindData){
				BindData bindData=(BindData)obj;
				String path=null;
				Object valueData=bindData.getValue();
				if(valueData!=null){
					path=valueData.toString();
				}
				if(StringUtils.isNotBlank(path)){
					String base64Data=ImageUtils.getImageBase64Data(ImageType.image, path,width,height);
					list.add(new BindData(new Image(base64Data,path,-1,-1)));
				}
			}else if(obj instanceof String){
				String text=obj.toString();
				if(text.startsWith("\"") && text.endsWith("\"")){
					text=text.substring(1,text.length()-1);
				}
				String base64Data=ImageUtils.getImageBase64Data(ImageType.image, text,width,height);
				list.add(new BindData(new Image(base64Data,text,-1,-1)));
			}else{
				if(obj!=null && StringUtils.isNotBlank(obj.toString())){
					String base64Data=ImageUtils.getImageBase64Data(ImageType.image, obj.toString(),width,height);
					list.add(new BindData(new Image(base64Data,obj.toString(),-1,-1)));
				}
			}
		}
		return list;
	}
	@Override
	public ValueType type() {
		return ValueType.image;
	}
}
