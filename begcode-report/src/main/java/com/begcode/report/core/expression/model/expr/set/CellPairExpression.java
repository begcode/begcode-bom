
package com.begcode.report.core.expression.model.expr.set;


import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.ExpressionUtils;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;
import com.begcode.report.core.expression.model.expr.BaseExpression;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月1日
 */
public class CellPairExpression extends BaseExpression {
	private static final long serialVersionUID = 775139518725235246L;
	private String startCellName;
	private String endCellName;
	public CellPairExpression(String startCellName,String endCellName) {
		this.startCellName=startCellName;
		this.endCellName=endCellName;
	}
	@Override
	protected ExpressionData<?> compute(Cell cell, Cell currentCell, Context context) {
		List<Cell> cellList = buildCells(cell, context);
		if(cellList.size()>1){
			List<Object> list=new ArrayList<Object>();
			for(Cell targetCell:cellList){
				list.add(targetCell.getData());
			}
			return new ObjectListExpressionData(list);
		}else if(cellList.size()==1){
			return new ObjectExpressionData(cellList.get(0).getData());
		}else{
			return new ObjectExpressionData(null);
		}
	}
	private List<Cell> buildCells(Cell cell, Context context) {
		List<String> cellNameList= ExpressionUtils.getCellNameList();
		CellName startName=parseCellName(startCellName);
		int startPos=cellNameList.indexOf(startName.getName());
		int rowStart=startName.getNumber();

		CellName endName=parseCellName(endCellName);
		int endPos=cellNameList.indexOf(endName.getName());
		int rowEnd=endName.getNumber();

		if(startPos>endPos){
			int tmp=startPos;
			startPos=endPos;
			endPos=tmp;
		}
		if(rowStart>rowEnd){
			int tmp=rowStart;
			rowStart=rowEnd;
			rowEnd=tmp;
		}

		List<String> names=new ArrayList<String>();
		for(int i=startPos;i<=endPos;i++){
			names.add(cellNameList.get(i));
		}

		List<Cell> cellList=new ArrayList<Cell>();
		for(String name:names){
			for(int i=rowStart;i<=rowEnd;i++){
				String cellName=name+i;
				List<Cell> cells= Utils.fetchTargetCells(cell, context, cellName);
				cellList.addAll(cells);
			}
		}
		return cellList;
	}
	private CellName parseCellName(String name){
		StringBuilder sb=new StringBuilder();
		StringBuilder sb1=new StringBuilder();
		for(int i=0;i<name.length();i++){
			char c=name.charAt(i);
			if(Character.isDigit(c)){
				sb1.append(c);
			}else{
				sb.append(c);
			}
		}
		String cellName=sb.toString();
		int number=Integer.valueOf(sb1.toString());
		return new CellName(cellName,number);
	}
}

class CellName{
	private String name;
	private int number;
	public CellName(String name, int number) {
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public int getNumber() {
		return number;
	}
}
