
package com.begcode.report.core.parser;

import org.dom4j.Element;

/**
 * @author Jacky.gao
 * @since 2016年12月2日
 */
public interface Parser<T>{
	/**
	 * 解析元素
	 *
	 * @param element
	 * @return
	 */
	T parse(Element element);

	/**
	 * 元素名称
	 *
	 * @return
	 */
	String getName();
}
