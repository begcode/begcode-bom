
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.LabelPosition;
import com.begcode.report.core.definition.searchform.Option;
import com.begcode.report.core.definition.searchform.SelectInputComponent;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月30日
 */
@Component
public class SelectInputParser implements FormParser<SelectInputComponent> {
	@Override
	public SelectInputComponent parse(Element element) {
		SelectInputComponent select=new SelectInputComponent();
		select.setBindParameter(element.attributeValue("bind-parameter"));
		select.setLabel(element.attributeValue("label"));
		select.setType(element.attributeValue("type"));
		select.setLabelPosition(LabelPosition.valueOf(element.attributeValue("label-position")));
		String useDataset=element.attributeValue("use-dataset");
		if(StringUtils.isNotBlank(useDataset)){
			select.setUseDataset(Boolean.valueOf(useDataset));
			select.setDataset(element.attributeValue("dataset"));
			select.setLabelField(element.attributeValue("label-field"));
			select.setValueField(element.attributeValue("value-field"));
		}
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
		select.setOptions(options);
		return select;
	}

	@Override
	public String getName() {
		return "input-select";
	}
}
