
package com.begcode.report.core.build.assertor;


import com.begcode.report.core.Utils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author Jacky.gao
 * @since 2017年1月12日
 */
public class GreatThenAssertor extends AbstractAssertor {

	@Override
	public boolean eval(Object left, Object right) {
		if(left==null || right==null){
			return false;
		}
		if(StringUtils.isBlank(left.toString()) || StringUtils.isBlank(right.toString())){
			return false;
		}
		BigDecimal leftObj= Utils.toBigDecimal(left);
		right=buildObject(right);
		BigDecimal rightObj=Utils.toBigDecimal(right);
		return leftObj.compareTo(rightObj)==1;
	}
}
