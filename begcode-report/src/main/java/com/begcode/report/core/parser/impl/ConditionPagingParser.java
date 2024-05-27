
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.ConditionPaging;
import com.begcode.report.core.definition.PagingPosition;
import com.begcode.report.core.parser.Parser;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年6月21日
 */
@Component
public class ConditionPagingParser implements Parser<ConditionPaging> {
	@Override
	public ConditionPaging parse(Element element) {
		ConditionPaging paging=new ConditionPaging();
		String position=element.attributeValue("position");
		paging.setPosition(PagingPosition.valueOf(position));
		String line=element.attributeValue("line");
		paging.setLine(Integer.valueOf(line));
		return paging;
	}

	@Override
	public String getName() {
		return "paging";
	}
}
