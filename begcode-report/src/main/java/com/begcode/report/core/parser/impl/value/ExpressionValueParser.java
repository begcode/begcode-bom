
package com.begcode.report.core.parser.impl.value;


import com.begcode.report.core.definition.value.ExpressionValue;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2016年12月30日
 */
@Component
public class ExpressionValueParser extends ValueParser{
	@Override
	public ExpressionValue parse(Element element) {
		ExpressionValue value=new ExpressionValue(element.getText());
		return value;
	}

	@Override
	public String getName() {
		return "expression-value";
	}
}
