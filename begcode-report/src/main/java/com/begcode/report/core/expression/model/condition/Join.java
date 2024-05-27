
package com.begcode.report.core.expression.model.condition;

/**
 * @author Jacky.gao
 * @since 2016年12月1日
 */
public enum Join {
	and,or;
	public static Join parse(String join){
		if(join.equals("and") || join.equals("&&")){
			return and;
		}
		if(join.equals("or") || join.equals("||")){
			return or;
		}
		throw new IllegalArgumentException("Unknow join : "+join);
	}
}
