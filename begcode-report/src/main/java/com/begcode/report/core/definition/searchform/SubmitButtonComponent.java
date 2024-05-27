
package com.begcode.report.core.definition.searchform;

/**
 * @author Jacky.gao
 * @since 2017年10月24日
 */
public class SubmitButtonComponent extends ButtonComponent{
	@Override
	public String initJs(RenderContext context) {
		StringBuilder sb=new StringBuilder();
		sb.append("$('#"+context.buildComponentId(this)+"').click(function(){");
		sb.append("doSearch();");
		sb.append("});");
		return sb.toString();
	}
}
