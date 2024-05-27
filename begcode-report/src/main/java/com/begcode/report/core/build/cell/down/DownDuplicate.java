
package com.begcode.report.core.build.cell.down;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Report;
import com.begcode.report.core.model.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年11月10日
 */
public class DownDuplicate {
	private int index;
	private Cell mainCell;
	private int rowSize;
	private Context context;
	private int minRowNumber=-1;
	private Map<Row,Row> rowMap=new HashMap<Row,Row>();
	private List<Row> newRowList=new ArrayList<Row>();
	public DownDuplicate(Cell mainCell,int rowSize,Context context) {
		this.mainCell=mainCell;
		this.rowSize=rowSize;
		this.context=context;
	}

	public Row newRow(Row row,int currentRowNumber){
		if(rowMap.containsKey(row)){
			return rowMap.get(row);
		}else{
			int rowNumber=currentRowNumber;
			Row newRow=row.newRow();
			rowNumber=rowNumber+rowSize*(index-1)+rowSize;
			if(minRowNumber==-1 || minRowNumber>rowNumber){
				minRowNumber=rowNumber;
			}
			newRow.setTempRowNumber(rowNumber);
			newRowList.add(newRow);
			rowMap.put(row, newRow);
			return newRow;
		}
	}

	public Cell getMainCell() {
		return mainCell;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Context getContext() {
		return context;
	}
	public void complete(){
		if(minRowNumber<1){
			return;
		}
		Report report=context.getReport();
		/*
		Collections.sort(newRowList, new Comparator<Row>() {
			@Override
			public int compare(Row o1, Row o2) {
				return o1.getTempRowNumber()-o2.getTempRowNumber();
			}
		});
		*/
		report.insertRows(minRowNumber, newRowList);
	}
	public void reset(){
		rowMap.clear();
	}
}
