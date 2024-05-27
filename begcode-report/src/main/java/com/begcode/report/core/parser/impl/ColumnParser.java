
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.ColumnDefinition;
import com.begcode.report.core.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2016年12月5日
 */
@Component
public class ColumnParser implements Parser<ColumnDefinition> {
	@Override
	public ColumnDefinition parse(Element element) {
		ColumnDefinition col=new ColumnDefinition();
		col.setColumnNumber(Integer.valueOf(element.attributeValue("col-number")));
		String hide=element.attributeValue("hide");
		if(StringUtils.isNotBlank(hide)){
			col.setHide(Boolean.valueOf(hide));
		}
		String width=element.attributeValue("width");
		if(StringUtils.isNotBlank(width)){
			col.setWidth(Integer.valueOf(width));
		}
		return col;
	}

	@Override
	public String getName() {
		return "column";
	}
}
