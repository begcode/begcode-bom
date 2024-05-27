
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.definition.*;
import com.begcode.report.core.parser.Parser;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.springframework.stereotype.Component;


/**
 * @author Jacky.gao
 * @since 2017年1月19日
 */
@Component
public class PaperParser implements Parser<Paper> {
	@Override
	public Paper parse(Element element) {
		Paper paper=new Paper();
		String orientation=element.attributeValue("orientation");
		paper.setOrientation(Orientation.valueOf(orientation));
		paper.setPaperType(PaperType.valueOf(element.attributeValue("type")));
		if(paper.getPaperType().equals(PaperType.CUSTOM)){
			paper.setWidth(Integer.valueOf(element.attributeValue("width")));
			paper.setHeight(Integer.valueOf(element.attributeValue("height")));
		}else{
			PaperSize size=paper.getPaperType().getPaperSize();
			paper.setWidth(size.getWidth());
			paper.setHeight(size.getHeight());
		}
		String leftMargin=element.attributeValue("left-margin");
		if(StringUtils.isNotBlank(leftMargin)){
			paper.setLeftMargin(Integer.valueOf(leftMargin));
		}
		String rightMargin=element.attributeValue("right-margin");
		if(StringUtils.isNotBlank(rightMargin)){
			paper.setRightMargin(Integer.valueOf(rightMargin));
		}
		String topMargin=element.attributeValue("top-margin");
		if(StringUtils.isNotBlank(topMargin)){
			paper.setTopMargin(Integer.valueOf(topMargin));
		}
		String bottomMargin=element.attributeValue("bottom-margin");
		if(StringUtils.isNotBlank(bottomMargin)){
			paper.setBottomMargin(Integer.valueOf(bottomMargin));
		}
		paper.setPagingMode(PagingMode.valueOf(element.attributeValue("paging-mode")));
		if(paper.getPagingMode().equals(PagingMode.fixrows)){
			paper.setFixRows(Integer.valueOf(element.attributeValue("fixrows")));
		}
		String columnEnabled=element.attributeValue("column-enabled");
		if(StringUtils.isNotBlank(columnEnabled)){
			paper.setColumnEnabled(Boolean.valueOf(columnEnabled));
		}
		if(paper.isColumnEnabled()){
			paper.setColumnCount(Integer.valueOf(element.attributeValue("column-count")));
			paper.setColumnMargin(Integer.valueOf(element.attributeValue("column-margin")));
		}
		String htmlReportAlign=element.attributeValue("html-report-align");
		if(StringUtils.isNotBlank(htmlReportAlign)){
			paper.setHtmlReportAlign(HtmlReportAlign.valueOf(htmlReportAlign));
		}
		String htmlIntervalRefreshValue=element.attributeValue("html-interval-refresh-value");
		if(StringUtils.isNotBlank(htmlIntervalRefreshValue)){
			paper.setHtmlIntervalRefreshValue(Integer.valueOf(htmlIntervalRefreshValue));
		}
		paper.setBgImage(element.attributeValue("bg-image"));
		return paper;
	}

	@Override
	public String getName() {
		return "paper";
	}
}
