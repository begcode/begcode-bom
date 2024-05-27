
package com.begcode.report.core.definition.searchform;

/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public interface Component {
	String toHtml(RenderContext context);
	String initJs(RenderContext context);
	String getType();
}
