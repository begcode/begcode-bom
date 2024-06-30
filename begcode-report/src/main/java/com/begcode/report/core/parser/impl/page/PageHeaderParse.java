package com.begcode.report.core.parser.impl.page;

import com.begcode.report.core.definition.HeaderFooterDefinition;
import com.begcode.report.core.parser.Parser;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

@Component
public class PageHeaderParse extends PageHeaderFooterParser implements Parser<HeaderFooterDefinition> {

    @Override
    public HeaderFooterDefinition parse(Element element) {
        return super.pageParse(element);
    }

    @Override
    public String getName() {
        return "header";
    }
}
