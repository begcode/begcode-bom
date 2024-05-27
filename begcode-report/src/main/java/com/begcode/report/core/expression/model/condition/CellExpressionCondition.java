
package com.begcode.report.core.expression.model.condition;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年4月7日
 */
public class CellExpressionCondition extends BaseCondition {
	private ConditionType type=ConditionType.cell;
	private String cellName;
	private Expression rightExpression;
	@Override
	Object computeLeft(Cell cell, Cell currentCell, Object obj, Context context) {
		if(cellName.equals(currentCell.getName())){
			return currentCell.getData();
		}else{
			List<Cell> cells= Utils.fetchTargetCells(cell, context, cellName);
			int size=cells.size();
			if(cells==null || size==0){
				return new ReportComputeException("Unknow cell : "+cellName);
			}else{
				for(Cell c:cells){
					if(c.getRow()==cell.getRow() || c.getColumn()==cell.getColumn()){
						return c.getData();
					}
				}
				if(size>1){
					StringBuilder sb=new StringBuilder();
					for(Cell c:cells){
						if(sb.length()>0){
							sb.append(",");
						}
						sb.append(c.getData());
					}
					return sb.toString();
				}else{
					return cells.get(0).getData();
				}
			}
		}
	}

	@Override
	Object computeRight(Cell cell, Cell currentCell, Object obj, Context context) {
		ExpressionData<?> exprData = rightExpression.execute(cell,currentCell, context);
		return extractExpressionData(exprData);
	}

	@Override
	public ConditionType getType() {
		return type;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}
}
