
package com.begcode.report.core.parser.impl.value;


import com.begcode.report.core.definition.value.ImageValue;
import com.begcode.report.core.definition.value.Source;
import com.begcode.report.core.definition.value.Value;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Expression;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年3月6日
 */
@Component
public class ImageValueParser extends ValueParser {

	@Override
	public Value parse(Element element) {
		ImageValue value=new ImageValue();
		String width=element.attributeValue("width");
		if(StringUtils.isNotBlank(width)){
			value.setWidth(Integer.valueOf(width));
		}
		String height=element.attributeValue("height");
		if(StringUtils.isNotBlank(height)){
			value.setHeight(Integer.valueOf(height));
		}
		Source source= Source.valueOf(element.attributeValue("source"));
		value.setSource(source);
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(ele.getName().equals("text")){
				if(source.equals(Source.text)){
					value.setPath(ele.getText());
				}else{
					value.setExpr(ele.getText());
				}
				break;
			}
		}
		if(source.equals(Source.expression)){
			String expr=value.getExpr();
			Expression expression= ExpressionUtils.parseExpression(expr);
			value.setExpression(expression);
		}
		return value;
	}

	@Override
	public String getName() {
		return "image-value";
	}
}
