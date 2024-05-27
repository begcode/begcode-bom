
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.LabelPosition;
import com.begcode.report.core.definition.searchform.Option;
import com.begcode.report.core.definition.searchform.RadioInputComponent;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class RadioInputParser implements FormParser<RadioInputComponent> {
	@Override
	public RadioInputComponent parse(Element element) {
		RadioInputComponent radio=new RadioInputComponent();
		radio.setBindParameter(element.attributeValue("bind-parameter"));
		radio.setOptionsInline(Boolean.valueOf(element.attributeValue("options-inline")));
		radio.setLabel(element.attributeValue("label"));
		radio.setType(element.attributeValue("type"));
		radio.setLabelPosition(LabelPosition.valueOf(element.attributeValue("label-position")));
		List<Option> options=new ArrayList<Option>();
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(!ele.getName().equals("option")){
				continue;
			}
			Option option=new Option();
			options.add(option);
			option.setLabel(ele.attributeValue("label"));
			option.setValue(ele.attributeValue("value"));
		}
		radio.setOptions(options);
		return radio;
	}

	@Override
	public String getName() {
		return "input-radio";
	}
}
