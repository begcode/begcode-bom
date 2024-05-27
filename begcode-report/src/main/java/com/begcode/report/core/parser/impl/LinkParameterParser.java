
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.LinkParameter;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.parser.Parser;
import org.dom4j.Element;
import org.springframework.stereotype.Component;


/**
 * @author Jacky.gao
 * @since 2017年3月31日
 */
@Component
public class LinkParameterParser implements Parser<LinkParameter> {
	@Override
	public LinkParameter parse(Element element) {
		LinkParameter param=new LinkParameter();
		param.setName(element.attributeValue("name"));
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(ele.getName().equals("value")){
				param.setValue(ele.getText());
				Expression expr= ExpressionUtils.parseExpression(ele.getText());
				param.setValueExpression(expr);;
				break;
			}
		}
		return param;
	}

	@Override
	public String getName() {
		return "link-parameter";
	}
}
