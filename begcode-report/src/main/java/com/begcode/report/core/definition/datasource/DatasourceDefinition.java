
package com.begcode.report.core.definition.datasource;


import com.begcode.report.core.definition.dataset.DatasetDefinition;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
public interface DatasourceDefinition extends Serializable {
	String getName();
	List<DatasetDefinition> getDatasets();
	DatasourceType getType();
}
