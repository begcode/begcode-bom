
package com.begcode.report.core.build.cell.right;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Column;
import com.begcode.report.core.model.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2016年11月10日
 */
public class RightDuplicate {
	private int index;
	private int columnSize;
	private Context context;
	private Cell mainCell;
	private int minColNumber=-1;
	private Map<Column,Column> colMap=new HashMap<Column,Column>();
	private List<Column> newColList=new ArrayList<Column>();
	public RightDuplicate(Cell mainCell,int columnSize,Context context) {
		this.mainCell=mainCell;
		this.columnSize=columnSize;
		this.context=context;
	}

	public Column newColumn(Column col,int colNumber){
		if(colMap.containsKey(col)){
			return colMap.get(col);
		}else{
			Column newCol=col.newColumn();
			colNumber=colNumber+columnSize*(index-1)+columnSize;
			if(minColNumber==-1 || minColNumber>colNumber){
				minColNumber=colNumber;
			}
			newCol.setTempColumnNumber(colNumber);
			newColList.add(newCol);
			colMap.put(col, newCol);
			return newCol;
		}
	}

	public int getColSize() {
		return columnSize;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Context getContext() {
		return context;
	}

	public Cell getMainCell() {
		return mainCell;
	}

	public void complete(){
		if(minColNumber<1){
			return;
		}
		Report report=context.getReport();
		/*
		Collections.sort(newColList,new Comparator<Column>(){
			@Override
			public int compare(Column o1, Column o2) {
				return o1.getTempColumnNumber()-o2.getTempColumnNumber();
			}
		});
		*/
		report.insertColumns(minColNumber, newColList);
	}
	public void reset(){
		colMap.clear();
	}
}
