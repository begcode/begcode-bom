
package com.begcode.report.core.definition.searchform;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
public class ResetButtonComponent extends ButtonComponent{
	@Override
	public String toHtml(RenderContext context) {
		return "<button type=\"reset\" id=\""+context.buildComponentId(this)+"\" class=\"btn "+getStyle()+" btn-sm\">"+getLabel()+"</button>";
	}
	@Override
	public String initJs(RenderContext context) {
		return "";
	}
}
