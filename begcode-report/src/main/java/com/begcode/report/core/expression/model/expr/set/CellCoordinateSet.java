
package com.begcode.report.core.expression.model.expr.set;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月2日
 */
public class CellCoordinateSet {
	private List<CellCoordinate> cellCoordinates;

	public CellCoordinateSet(List<CellCoordinate> cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}
	public List<CellCoordinate> getCellCoordinates() {
		return cellCoordinates;
	}
}
