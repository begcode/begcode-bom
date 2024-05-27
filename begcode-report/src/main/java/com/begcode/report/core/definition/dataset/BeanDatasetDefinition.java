
package com.begcode.report.core.definition.dataset;

import com.begcode.report.core.build.Dataset;
import com.begcode.report.core.exception.ReportComputeException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年12月27日
 */
public class BeanDatasetDefinition implements DatasetDefinition {
	private static final long serialVersionUID = -1332306988025304185L;
	private String name;
	private String method;
	private String clazz;
	private List<Field> fields;

	@SuppressWarnings("unchecked")
	public Dataset buildDataset(String datasourceName, Object obj, Map<String,Object> parameters){
		try {
			Method m=obj.getClass().getMethod(method, new Class[]{String.class,String.class,Map.class});
			Object result=m.invoke(obj, new Object[]{datasourceName,name,parameters});
			List<Object> list=(List<Object>)result;
			return new Dataset(name,list);
		} catch (Exception e) {
			throw new ReportComputeException(e);
		}
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
