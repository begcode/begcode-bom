
package com.begcode.report.core.chart.dataset;

/**
 * @author Jacky.gao
 * @since 2017年6月8日
 */
public class BubbleData {
	private double x;
	private double y;
	private double r;

	public BubbleData(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
}
