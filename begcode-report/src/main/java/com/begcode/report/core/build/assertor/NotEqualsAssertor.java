
package com.begcode.report.core.build.assertor;


/**
 * @author Jacky.gao
 * @since 2017年1月12日
 */
public class NotEqualsAssertor extends AbstractAssertor {

	@Override
	public boolean eval(Object left, Object right) {
		if(left == null && right==null){
			return false;
		}
		if(left==null || right==null){
			return true;
		}
		right=buildObject(right);
		return !left.equals(right);
	}
}
