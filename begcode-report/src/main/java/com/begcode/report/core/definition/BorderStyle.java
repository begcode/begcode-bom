
package com.begcode.report.core.definition;
/**
 * @author Jacky.gao
 * @since 2014年3月31日
 */
public enum BorderStyle {
	solid,dashed,doublesolid;
	public static BorderStyle toBorderStyle(String name){
		if(name.equals("double")){
			return BorderStyle.doublesolid;
		}else{
			return BorderStyle.valueOf(name);
		}
	}
	@Override
	public String toString() {
		if(this.equals(BorderStyle.doublesolid)){
			return "double";
		}
		return super.toString();
	}
}
