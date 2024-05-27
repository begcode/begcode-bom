
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.*;
import com.begcode.report.core.definition.value.Value;
import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.exception.ReportParseException;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.parser.Parser;
import com.begcode.report.core.parser.ReportParseFactory;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年12月5日
 */
@Component
public class CellParser extends ReportParseFactory implements Parser<CellDefinition> {

    @Override
    public CellDefinition parse(Element element) {
        CellDefinition cell = new CellDefinition();
        cell.setName(element.attributeValue("name"));
        cell.setColumnNumber(Integer.valueOf(element.attributeValue("col")));
        cell.setRowNumber(Integer.valueOf(element.attributeValue("row")));
        cell.setLeftParentCellName(element.attributeValue("left-cell"));
        cell.setTopParentCellName(element.attributeValue("top-cell"));
        String rowSpan = element.attributeValue("row-span");
        if (StringUtils.isNotBlank(rowSpan)) {
            cell.setRowSpan(Integer.valueOf(rowSpan));
        }
        String colSpan = element.attributeValue("col-span");
        if (StringUtils.isNotBlank(colSpan)) {
            cell.setColSpan(Integer.valueOf(colSpan));
        }
        String expand = element.attributeValue("expand");
        if (StringUtils.isNotBlank(expand)) {
            cell.setExpand(Expand.valueOf(expand));
        }
        String fillBlankRows = element.attributeValue("fill-blank-rows");
        if (StringUtils.isNotBlank(fillBlankRows)) {
            cell.setFillBlankRows(Boolean.valueOf(fillBlankRows));
            String multiple = element.attributeValue("multiple");
            if (StringUtils.isNotBlank(multiple)) {
                cell.setMultiple(Integer.valueOf(multiple));
            }
        }
        cell.setLinkTargetWindow(element.attributeValue("link-target-window"));
        String linkUrl = element.attributeValue("link-url");
        cell.setLinkUrl(linkUrl);
        if (StringUtils.isNotBlank(linkUrl)) {
            if (linkUrl.startsWith(ExpressionUtils.EXPR_PREFIX) && linkUrl.endsWith(ExpressionUtils.EXPR_SUFFIX)) {
                String expr = linkUrl.substring(2, linkUrl.length() - 1);
                Expression urlExpression = ExpressionUtils.parseExpression(expr);
                cell.setLinkUrlExpression(urlExpression);
            }
        }
        List<LinkParameter> linkParameters = null;
        List<ConditionPropertyItem> conditionPropertyItems = null;
        for (Object obj : element.elements()) {
            if (!(obj instanceof Element)) {
                continue;
            }
            Element ele = (Element) obj;
            Object parseData = parseValue(ele);
            if (parseData instanceof Value) {
                Value value = (Value) parseData;
                cell.setValue(value);
            } else if (parseData instanceof CellStyle) {
                CellStyle cellStyle = (CellStyle) parseData;
                cell.setCellStyle(cellStyle);
            } else if (parseData instanceof LinkParameter) {
                if (linkParameters == null) {
                    linkParameters = new ArrayList<LinkParameter>();
                }
                linkParameters.add((LinkParameter) parseData);
            } else if (parseData instanceof ConditionPropertyItem) {
                if (conditionPropertyItems == null) {
                    conditionPropertyItems = new ArrayList<ConditionPropertyItem>();
                }
                conditionPropertyItems.add((ConditionPropertyItem) parseData);
            }
        }
        if (linkParameters != null) {
            cell.setLinkParameters(linkParameters);
        }
        cell.setConditionPropertyItems(conditionPropertyItems);
        if (cell.getValue() == null) {
            throw new ReportException("Cell [" + cell.getName() + "] value not define.");
        }
        return cell;
    }

    @Override
    public String getName() {
        return "cell";
    }

    private Object parseValue(Element element) {
        Parser<?> parser = getParse(element.getName());
        if (parser != null) {
            return parser.parse(element);
        }
        throw new ReportParseException("Unknow element :" + element.getName());
    }
}
