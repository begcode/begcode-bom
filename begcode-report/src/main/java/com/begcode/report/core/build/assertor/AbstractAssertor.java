
package com.begcode.report.core.build.assertor;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年9月15日
 */
public abstract class AbstractAssertor implements Assertor{
	protected Object buildObject(Object obj){
		if(obj==null){
			return obj;
		}
		if(obj instanceof List){
			List<?> list=(List<?>)obj;
			if(list.size()==1){
				return list.get(0);
			}
		}
		return obj;
	}
}
