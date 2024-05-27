package com.begcode.report.core.definition.searchform;


import com.begcode.report.core.build.Dataset;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年10月23日
 */
public class RenderContext {
	private int id=0;
	private Map<String, Dataset> datasetMap;
	private Map<String, Object> parameters;
	private Map<Component,String> componentMap=new HashMap<Component,String>();
	private Map<String,Object> metadata=new HashMap<String,Object>();
	public RenderContext(Map<String,Dataset> datasetMap,Map<String, Object> parameters) {
		this.datasetMap=datasetMap;
		this.parameters=parameters;
	}
	public Dataset getDataset(String datasetName) {
		return datasetMap.get(datasetName);
	}
	public Object getParameter(String name){
		return parameters.get(name);
	}
	public String buildComponentId(Component component){
		if(componentMap.containsKey(component)){
			return componentMap.get(component);
		}
		String cid="__c_"+(id++);
		componentMap.put(component, cid);
		return cid;
	}
	public Object getMetadata(String key) {
		return metadata.get(key);
	}
	public void putMetadata(String key,Object value){
		metadata.put(key, value);
	}
}
