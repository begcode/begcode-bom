
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.ResetButtonComponent;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class ResetButtonParser implements FormParser<ResetButtonComponent>{
	@Override
	public ResetButtonComponent parse(Element element) {
		ResetButtonComponent btn=new ResetButtonComponent();
		btn.setLabel(element.attributeValue("label"));
		btn.setStyle(element.attributeValue("style"));
		btn.setType(element.attributeValue("type"));
		return btn;
	}

	@Override
	public String getName() {
		return "button-reset";
	}
}
