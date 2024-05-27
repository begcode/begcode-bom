
package com.begcode.report.core.parser.impl;

import com.begcode.report.core.Utils;
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
public class CellStyleParser implements Parser<CellStyle> {
	@Override
	public CellStyle parse(Element element) {
		boolean forCondition=false;
		String forConditionText=element.attributeValue("for-condition");
		if(StringUtils.isNotBlank(forConditionText)){
			forCondition=Boolean.valueOf(forConditionText);
		}
		CellStyle style=null;
		if(forCondition){
			ConditionCellStyle s=new ConditionCellStyle();
			String bgcolorScope=element.attributeValue("bgcolor-scope");
			if(StringUtils.isNotBlank(bgcolorScope)){
				s.setBgcolorScope(Scope.valueOf(bgcolorScope));
			}
			String forecolorScope=element.attributeValue("forecolor-scope");
			if(StringUtils.isNotBlank(forecolorScope)){
				s.setForecolorScope(Scope.valueOf(forecolorScope));
			}
			String fontSizeScope=element.attributeValue("font-size-scope");
			if(StringUtils.isNotBlank(fontSizeScope)){
				s.setFontSizeScope(Scope.valueOf(fontSizeScope));
			}
			String fontFamilyScope=element.attributeValue("font-family-scope");
			if(StringUtils.isNotBlank(fontFamilyScope)){
				s.setFontFamilyScope(Scope.valueOf(fontFamilyScope));
			}
			String boldScope=element.attributeValue("bold-scope");
			if(StringUtils.isNotBlank(boldScope)){
				s.setBoldScope(Scope.valueOf(boldScope));
			}
			String italicScope=element.attributeValue("italic-scope");
			if(StringUtils.isNotBlank(italicScope)){
				s.setItalicScope(Scope.valueOf(italicScope));
			}
			String underlineScope=element.attributeValue("underline-scope");
			if(StringUtils.isNotBlank(underlineScope)){
				s.setUnderlineScope(Scope.valueOf(underlineScope));
			}
			String alignScope=element.attributeValue("align-scope");
			if(StringUtils.isNotBlank(alignScope)){
				s.setAlignScope(Scope.valueOf(alignScope));
			}
			String valignScope=element.attributeValue("valign-scope");
			if(StringUtils.isNotBlank(valignScope)){
				s.setValignScope(Scope.valueOf(valignScope));
			}
			style=s;
		}else{
			style=new CellStyle();
		}
		style.setBgcolor(element.attributeValue("bgcolor"));
		String forecolor=element.attributeValue("forecolor");
		if(StringUtils.isNotBlank(forecolor)){
			style.setForecolor(forecolor);
		}
		String fontFamily=element.attributeValue("font-family");
		if(StringUtils.isNotBlank(fontFamily)){
			style.setFontFamily(fontFamily);
		}
		String bold=element.attributeValue("bold");
		if(StringUtils.isNotBlank(bold)){
			style.setBold(Boolean.valueOf(bold));
		}
		String fontSize=element.attributeValue("font-size");
		if(StringUtils.isNotBlank(fontSize)){
			style.setFontSize(Integer.valueOf(fontSize));
		}
		style.setFormat(element.attributeValue("format"));
		String italic=element.attributeValue("italic");
		if(StringUtils.isNotBlank(italic)){
			style.setItalic(Boolean.valueOf(italic));
		}
		String underline=element.attributeValue("underline");
		if(StringUtils.isNotBlank(underline)){
			style.setUnderline(Boolean.valueOf(underline));
		}
		String valign=element.attributeValue("valign");
		if(StringUtils.isNotBlank(valign)){
			style.setValign(Alignment.valueOf(valign));
		}
		String align=element.attributeValue("align");
		if(StringUtils.isNotBlank(align)){
			style.setAlign(Alignment.valueOf(align));
		}
		String wrapCompute=element.attributeValue("wrap-compute");
		if(StringUtils.isNotBlank(wrapCompute)){
			style.setWrapCompute(Boolean.valueOf(wrapCompute));
		}
		String lineHeight=element.attributeValue("line-height");
		if(StringUtils.isNotBlank(lineHeight)){
			style.setLineHeight(Utils.toBigDecimal(lineHeight).floatValue());
		}
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			String name=ele.getName();
			if(name.equals("left-border")){
				style.setLeftBorder(parseBorder(ele));
			}else if(name.equals("right-border")){
				style.setRightBorder(parseBorder(ele));
			}else if(name.equals("top-border")){
				style.setTopBorder(parseBorder(ele));
			}else if(name.equals("bottom-border")){
				style.setBottomBorder(parseBorder(ele));
			}
		}
		return style;
	}

	@Override
	public String getName() {
		return "cell-style";
	}

	private Border parseBorder(Element element){
		Border border=new Border();
		border.setWidth(Integer.valueOf(element.attributeValue("width")));
		border.setStyle(BorderStyle.valueOf(element.attributeValue("style")));
		border.setColor(element.attributeValue("color"));
		return border;
	}
}
