
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.ConditionCellStyle;
import com.begcode.report.core.definition.ConditionPaging;
import com.begcode.report.core.definition.ConditionPropertyItem;
import com.begcode.report.core.definition.LinkParameter;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.Condition;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.Op;
import com.begcode.report.core.expression.model.condition.BaseCondition;
import com.begcode.report.core.expression.model.condition.BothExpressionCondition;
import com.begcode.report.core.expression.model.condition.Join;
import com.begcode.report.core.expression.model.condition.PropertyExpressionCondition;
import com.begcode.report.core.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年4月11日
 */
@Component
public class ConditionParameterItemParser implements Parser<ConditionPropertyItem> {
	private Map<String,Parser<?>> parsers=new HashMap<String,Parser<?>>();
	public ConditionParameterItemParser() {
		parsers.put("cell-style",new CellStyleParser());
		parsers.put("link-parameter",new LinkParameterParser());
		parsers.put("paging",new ConditionPagingParser());
	}
	@Override
	public ConditionPropertyItem parse(Element element) {
		ConditionPropertyItem item=new ConditionPropertyItem();
		String rowHeight=element.attributeValue("row-height");
		if(StringUtils.isNotBlank(rowHeight)){
			item.setRowHeight(Integer.valueOf(rowHeight));
		}
		String colWidth=element.attributeValue("col-width");
		if(StringUtils.isNotBlank(colWidth)){
			item.setColWidth(Integer.valueOf(colWidth));
		}
		item.setName(element.attributeValue("name"));
		item.setNewValue(element.attributeValue("new-value"));
		item.setLinkUrl(element.attributeValue("link-url"));
		item.setLinkTargetWindow(element.attributeValue("link-target-window"));
		List<LinkParameter> parameters=null;
		List<Condition> conditions=new ArrayList<Condition>();
		item.setConditions(conditions);
		BaseCondition topCondition=null;
		BaseCondition prevCondition=null;
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			String name=ele.getName();
			if(name.equals("condition")){
				BaseCondition condition = parseCondition(ele);
				conditions.add(condition);
				if(topCondition==null){
					topCondition=condition;
					prevCondition=condition;
				}else{
					prevCondition.setNextCondition(condition);
					prevCondition.setJoin(condition.getJoin());
					prevCondition=condition;
				}
				continue;
			}
			Parser<?> parser=parsers.get(name);
			if(parser==null){
				continue;
			}
			Object data=parser.parse(ele);
			if(data instanceof ConditionCellStyle){
				item.setCellStyle((ConditionCellStyle)data);
			}else if(data instanceof LinkParameter){
				if(parameters==null){
					parameters=new ArrayList<LinkParameter>();
				}
				parameters.add((LinkParameter)data);
			}else if(data instanceof ConditionPaging){
				item.setPaging((ConditionPaging)data);
			}
		}
		item.setCondition(topCondition);
		item.setLinkParameters(parameters);
		return item;
	}

	@Override
	public String getName() {
		return "condition-property-item";
	}

	private BaseCondition parseCondition(Element ele) {
		String type=ele.attributeValue("type");
		if(type==null || type.equals("property")){
			PropertyExpressionCondition condition=new PropertyExpressionCondition();
			String property=ele.attributeValue("property");
			condition.setLeftProperty(property);
			condition.setLeft(property);
			String operation=ele.attributeValue("op");
			condition.setOperation(operation);
			condition.setOp(Op.parse(operation));
			for(Object o:ele.elements()){
				if(o==null || !(o instanceof Element)){
					continue;
				}
				Element e=(Element)o;
				if(!e.getName().equals("value")){
					continue;
				}
				String expr=e.getTextTrim();
				condition.setRightExpression(ExpressionUtils.parseExpression(expr));
				condition.setRight(expr);
				break;
			}
			String join=ele.attributeValue("join");
			if(StringUtils.isNotBlank(join)){
				condition.setJoin(Join.valueOf(join));
			}
			return condition;
		}else{
			BothExpressionCondition exprCondition=new BothExpressionCondition();
			exprCondition.setOperation(ele.attributeValue("op"));
			exprCondition.setOp(Op.parse(ele.attributeValue("op")));
			for(Object o:ele.elements()){
				if(o==null || !(o instanceof Element)){
					continue;
				}
				Element e=(Element)o;
				String name=e.getName();
				if(name.equals("left")){
					String left=e.getText();
					Expression leftExpr=ExpressionUtils.parseExpression(left);
					exprCondition.setLeft(left);
					exprCondition.setLeftExpression(leftExpr);
				}else if(name.equals("right")){
					String right=e.getText();
					Expression rightExpr=ExpressionUtils.parseExpression(right);
					exprCondition.setRight(right);
					exprCondition.setRightExpression(rightExpr);
				}
			}
			String join=ele.attributeValue("join");
			if(StringUtils.isNotBlank(join)){
				exprCondition.setJoin(Join.valueOf(join));
			}
			return exprCondition;
		}
	}
}
