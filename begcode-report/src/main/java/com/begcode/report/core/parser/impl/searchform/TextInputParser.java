
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.LabelPosition;
import com.begcode.report.core.definition.searchform.TextInputComponent;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class TextInputParser implements FormParser<TextInputComponent> {
	@Override
	public TextInputComponent parse(Element element) {
		TextInputComponent component=new TextInputComponent();
		component.setBindParameter(element.attributeValue("bind-parameter"));
		component.setLabel(element.attributeValue("label"));
		component.setType(element.attributeValue("type"));
		component.setLabelPosition(LabelPosition.valueOf(element.attributeValue("label-position")));
		return component;
	}

	@Override
	public String getName() {
		return "input-text";
	}
}
