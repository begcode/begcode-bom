
package com.begcode.report.core.definition.searchform;

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public class TextInputComponent extends InputComponent {
	@Override
	String inputHtml(RenderContext context) {
		String name=getBindParameter();
		Object pvalue=context.getParameter(name)==null ? "" : context.getParameter(name);
		return "<input type='text' value=\""+pvalue+"\" style=\"padding:3px;height:28px\" id='"+context.buildComponentId(this)+"' name='"+getBindParameter()+"' class='form-control'>";
	}
	@Override
	public String initJs(RenderContext context) {
		String name=getBindParameter();
		StringBuilder sb=new StringBuilder();
		sb.append("formElements.push(");
		sb.append("function(){");
		sb.append("if(''==='"+name+"'){");
		sb.append("alert('文本框未绑定查询参数名，不能进行查询操作!');");
		sb.append("throw '文本框未绑定查询参数名，不能进行查询操作!'");
		sb.append("}");
		sb.append("return {");
		sb.append("\""+name+"\":");
		sb.append("$('#"+context.buildComponentId(this)+"').val()");
		sb.append("}");
		sb.append("}");
		sb.append(");");
		return sb.toString();
	}
}
