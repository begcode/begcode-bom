
package com.begcode.report.core.definition.dataset;

import java.io.Serializable;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
public interface DatasetDefinition extends Serializable{
	String getName();
	List<Field> getFields();
}
