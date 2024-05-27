
package com.begcode.report.core.parser.impl.value;

import com.begcode.report.core.definition.value.SimpleValue;
import com.begcode.report.core.definition.value.Value;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2016年12月21日
 */
@Component
public class SimpleValueParser extends ValueParser {
	@Override
	public Value parse(Element element) {
		SimpleValue simpleValue=new SimpleValue(element.getText());
		return simpleValue;
	}

	@Override
	public String getName() {
		return "simple-value";
	}
}
