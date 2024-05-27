
package com.begcode.report.core.build.assertor;

import com.begcode.report.core.Utils;

import java.math.BigDecimal;


/**
 * @author Jacky.gao
 * @since 2017年1月12日
 */
public class EqualsAssertor extends AbstractAssertor {
	@Override
	public boolean eval(Object left, Object right) {
		if(left == null && right==null){
			return true;
		}
		if(left==null || right==null){
			return false;
		}
		if(left instanceof Number && right instanceof Number){
			BigDecimal b1= Utils.toBigDecimal(left);
			BigDecimal b2=Utils.toBigDecimal(right);
			return b1.compareTo(b2)==0;
		}else if(left instanceof Number){
			BigDecimal b1=Utils.toBigDecimal(left);
			BigDecimal b2=null;
			try{
				b2=Utils.toBigDecimal(right);
			}catch(Exception ex){}
			if(b2!=null){
				return b1.compareTo(b2)==0;
			}
		}else if(right instanceof Number){
			BigDecimal b1=Utils.toBigDecimal(right);
			BigDecimal b2=null;
			try{
				b2=Utils.toBigDecimal(left);
			}catch(Exception ex){}
			if(b2!=null){
				return b1.compareTo(b2)==0;
			}
		}
		right=buildObject(right);
		return left.equals(right);
	}
}
