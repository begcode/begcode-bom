
package com.begcode.report.core.definition.searchform;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public class SearchForm {
	private List<Component> components;
	private FormPosition formPosition;
	public String toHtml(RenderContext context){
		StringBuilder sb=new StringBuilder();
		sb.append("<form  style='margin-top:10px;margin-bottom:10px'>");
		for(Component component:components){
			sb.append(component.toHtml(context));
		}
		sb.append("</form>");
		return sb.toString();
	}
	public String toJs(RenderContext context){
		StringBuilder sb=new StringBuilder();
		for(Component component:components){
			sb.append(component.initJs(context));
		}
		return sb.toString();
	}
	public List<Component> getComponents() {
		return components;
	}
	public void setComponents(List<Component> components) {
		this.components = components;
	}
	public FormPosition getFormPosition() {
		return formPosition;
	}
	public void setFormPosition(FormPosition formPosition) {
		this.formPosition = formPosition;
	}
}
