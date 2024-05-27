
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.Align;
import com.begcode.report.core.definition.searchform.SubmitButtonComponent;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class SubmitButtonParser implements FormParser<SubmitButtonComponent> {
	@Override
	public SubmitButtonComponent parse(Element element) {
		SubmitButtonComponent btn=new SubmitButtonComponent();
		btn.setLabel(element.attributeValue("label"));
		btn.setStyle(element.attributeValue("style"));
		btn.setType(element.attributeValue("type"));
		String align=element.attributeValue("align");
		if(align!=null){
			btn.setAlign(Align.valueOf(align));
		}
		return btn;
	}

	@Override
	public String getName() {
		return "button-submit";
	}
}
