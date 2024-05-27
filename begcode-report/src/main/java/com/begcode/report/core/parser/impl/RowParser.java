
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.Band;
import com.begcode.report.core.definition.RowDefinition;
import com.begcode.report.core.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2016年12月5日
 */
@Component
public class RowParser implements Parser<RowDefinition> {
	@Override
	public RowDefinition parse(Element element) {
		RowDefinition row=new RowDefinition();
		row.setRowNumber(Integer.valueOf(element.attributeValue("row-number")));
		String height=element.attributeValue("height");
		if(StringUtils.isNotBlank(height)){
			row.setHeight(Integer.valueOf(height));
		}
		String band=element.attributeValue("band");
		if(StringUtils.isNotBlank(band)){
			row.setBand(Band.valueOf(band));
		}
		return row;
	}

	@Override
	public String getName() {
		return "row";
	}
}
