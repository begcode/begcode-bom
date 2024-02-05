/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.begcode.ureport.core.parser.impl.searchform;

import com.begcode.ureport.core.definition.searchform.ColComponent;
import com.begcode.ureport.core.definition.searchform.Component;
import com.begcode.ureport.core.definition.searchform.GridComponent;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
@org.springframework.stereotype.Component
public class GridParserBase extends FormParserBase implements FormParser<GridComponent> {
    @Override
    public GridComponent parse(Element element) {
        GridComponent grid = new GridComponent();
        grid.setType(element.attributeValue("type"));
        grid.setShowBorder(Boolean.valueOf(element.attributeValue("show-border")));
        if (grid.isShowBorder()) {
            grid.setBorderColor(element.attributeValue("border-color"));
            grid.setBorderWidth(Integer.valueOf(element.attributeValue("border-width")));
        }
        List<ColComponent> cols = new ArrayList<ColComponent>();
        for (Object obj : element.elements()) {
            if (obj == null || !(obj instanceof Element)) {
                continue;
            }
            Element ele = (Element) obj;
            if (!ele.getName().equals("col")) {
                continue;
            }
            ColComponent col = new ColComponent();
            cols.add(col);
            col.setSize(Integer.valueOf(ele.attributeValue("size")));
            List<Component> components = formParse(ele);
            col.setChildren(components);
        }
        grid.setCols(cols);
        return grid;
    }

    @Override
    public String getName() {
        return "grid";
    }

}
