
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.DateInputComponent;
import com.begcode.report.core.definition.searchform.LabelPosition;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class DatetimeInputParser implements FormParser<DateInputComponent> {
	@Override
	public DateInputComponent parse(Element element) {
		DateInputComponent component=new DateInputComponent();
		component.setBindParameter(element.attributeValue("bind-parameter"));
		component.setLabel(element.attributeValue("label"));
		component.setType(element.attributeValue("type"));
		component.setLabelPosition(LabelPosition.valueOf(element.attributeValue("label-position")));
		component.setFormat(element.attributeValue("format"));
		return component;
	}

	@Override
	public String getName() {
		return "input-datetime";
	}
}
