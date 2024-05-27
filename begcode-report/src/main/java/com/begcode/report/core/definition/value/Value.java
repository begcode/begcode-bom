
package com.begcode.report.core.definition.value;

import java.io.Serializable;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public interface Value extends Serializable {
	String getValue();
	ValueType getType();
}
