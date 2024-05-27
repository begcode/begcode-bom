
package com.begcode.report.core.export.word;

import java.math.BigInteger;

/**
 * @author Jacky.gao
 * @since 2015年8月8日
 */
public class DxaUtils {
	public static float dxa2mm(float dxa) {
		return (float) (dxa2inch(dxa) * 25.4);
	}

	public static float dxa2mm(BigInteger dxa) {
		return (float) (dxa2inch(dxa) * 25.4);
	}

	public static float emu2points(long emu) {
		return dxa2points(emu) / 635;
	}

	public static float dxa2points(float dxa) {
		return dxa / 20;
	}

	public static int points2dxa(int points) {
		return points * 21;
	}
	public static int dxa2points(int dxa) {
		return dxa / 20;
	}

	public static float dxa2points(BigInteger dxa) {
		return dxa.intValue() / 20;
	}

	public static float dxa2inch(float dxa) {
		return dxa2points(dxa) / 72;
	}

	public static float dxa2inch(BigInteger dxa) {
		return dxa2points(dxa) / 72;
	}
}
