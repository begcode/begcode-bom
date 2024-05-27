
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.CheckboxInputComponent;
import com.begcode.report.core.definition.searchform.LabelPosition;
import com.begcode.report.core.definition.searchform.Option;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Component
public class CheckboxParser implements FormParser<CheckboxInputComponent> {
	@Override
	public CheckboxInputComponent parse(Element element) {
		CheckboxInputComponent checkbox=new CheckboxInputComponent();
		checkbox.setBindParameter(element.attributeValue("bind-parameter"));
		checkbox.setOptionsInline(Boolean.valueOf(element.attributeValue("options-inline")));
		checkbox.setLabel(element.attributeValue("label"));
		checkbox.setType(element.attributeValue("type"));
		checkbox.setLabelPosition(LabelPosition.valueOf(element.attributeValue("label-position")));
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
		checkbox.setOptions(options);
		return checkbox;
	}

	@Override
	public String getName() {
		return "input-checkbox";
	}
}
