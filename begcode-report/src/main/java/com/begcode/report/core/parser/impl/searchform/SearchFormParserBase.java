
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.Component;
import com.begcode.report.core.definition.searchform.FormPosition;
import com.begcode.report.core.definition.searchform.SearchForm;
import com.begcode.report.core.parser.Parser;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@Service
public class SearchFormParserBase extends FormParserBase implements Parser<SearchForm> {
	@Override
	public SearchForm parse(Element element) {
		SearchForm form=new SearchForm();
		form.setFormPosition(FormPosition.valueOf(element.attributeValue("form-position")));
		List<Component> components= formParse(element);
		form.setComponents(components);
		return form;
	}

	@Override
	public String getName() {
		return "search-form";
	}
}
