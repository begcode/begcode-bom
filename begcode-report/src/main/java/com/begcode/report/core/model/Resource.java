
package com.begcode.report.core.model;


import com.begcode.report.core.cache.ResourceCache;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Jacky.gao
 * @since 2017年3月17日
 */
public class Resource {
	private String key;

	public Resource(String path) {
		this.key = path;
	}

	public InputStream getResourceData(){
		byte[] imageBytes = (byte[]) ResourceCache.getObject(key);
		InputStream inputStream=new ByteArrayInputStream(imageBytes);
		return inputStream;
	}

	public String getKey() {
		return key;
	}
}
