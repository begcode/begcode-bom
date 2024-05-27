
package com.begcode.report.core.utils;

import com.begcode.report.core.Utils;

/**
 * @author Jacky.gao
 * @since 2017年3月16日
 */
public class UnitUtils {
	public static int pointToPixel(double point){
		double value=point * 1.33;
		return Utils.toBigDecimal(value).intValue();
	}
	public static int pixelToPoint(double pixel){
		double value=pixel * 0.75;
		return Utils.toBigDecimal(value).intValue();
	}
	public static final float pointToInche(final float value) {
	    return value / 72f;
	}
	public static int pointToTwip(int point) {
		return point * 20;
	}
}
