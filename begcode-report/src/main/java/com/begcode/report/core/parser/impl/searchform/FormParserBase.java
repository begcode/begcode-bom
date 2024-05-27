
package com.begcode.report.core.parser.impl.searchform;

import com.begcode.report.core.definition.searchform.Component;
import com.begcode.report.core.parser.Parser;
import com.begcode.report.core.parser.ReportParseFactory;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
public abstract class FormParserBase extends ReportParseFactory {

    public List<Component> formParse(Element element) {
        List<Component> list = new ArrayList<Component>();
        for (Object obj : element.elements()) {
            if (obj == null || !(obj instanceof Element)) {
                continue;
            }
            Element ele = (Element) obj;
            String name = ele.getName();
            Parser<?> targetParser = getParse(name);
            if (targetParser == null) {
                continue;
            }
            list.add((Component) targetParser.parse(ele));
        }
        return list;
    }
}
