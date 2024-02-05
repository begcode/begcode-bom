package com.begcode.ureport.core.parser.impl.page;

import com.begcode.ureport.core.definition.HeaderFooterDefinition;
import com.begcode.ureport.core.parser.Parser;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

/**
 * @Author: summer
 * @Date: 2022/8/9 20:46
 * @Description:
 **/
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
