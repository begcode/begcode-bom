
package com.begcode.report.core.build.cell.down;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2016年11月7日
 */
public class CellDownDuplicateUnit {
	private Cell mainCell;
	private int mainCellRowNumber;
	private Context context;
	private DownDuplicate downDuplicate;
	private DownDuplocatorWrapper downDuplocatorWrapper;
	public CellDownDuplicateUnit(Context context,DownDuplocatorWrapper downDuplocatorWrapper,Cell mainCell,int mainCellRowNumber,int rowSize) {
		this.context=context;
		this.downDuplocatorWrapper=downDuplocatorWrapper;
		this.mainCell=mainCell;
		this.mainCellRowNumber=mainCellRowNumber;
		this.downDuplicate=new DownDuplicate(mainCell,rowSize,context);
	}
	public void duplicate(Cell cell,int index){
		Map<Cell,Cell> newCellMap=new HashMap<Cell,Cell>();
		newCellMap.put(mainCell, cell);
		downDuplicate.setIndex(index);
		for(CellDownDuplicator childDuplicator:downDuplocatorWrapper.getMainCellChildren()){
			Cell newCell=childDuplicator.duplicateChildrenCell(downDuplicate, cell, mainCell,false);
			newCellMap.put(childDuplicator.getCell(), newCell);
			processChildrenCells(newCell,childDuplicator.getCell(),newCellMap,downDuplicate,childDuplicator.isNonChild());
			childDuplicator.setNonChild(false);
		}
		for(CellDownDuplicator cellDownDuplicator:downDuplocatorWrapper.getCellDuplicators()){
			cellDownDuplicator.duplicate(downDuplicate,cell);
		}
		Row newRow=downDuplicate.newRow(cell.getRow(),mainCellRowNumber);
		cell.setRow(newRow);
		newRow.getCells().add(cell);
		cell.getColumn().getCells().add(cell);
		context.addReportCell(cell);
		downDuplicate.reset();
		for(Cell newCell:newCellMap.values()){
			Cell originTopCell=newCell.getTopParentCell();
			if(originTopCell!=null && newCellMap.containsKey(originTopCell)){
				newCell.setTopParentCell(newCellMap.get(originTopCell));
			}
		}
	}

	public void complete(){
		downDuplicate.complete();
	}

	private void processChildrenCells(Cell cell,Cell originalCell,Map<Cell,Cell> newCellMap,DownDuplicate downDuplicate,boolean parentNonChild){
		List<CellDownDuplicator> childCellDownDuplicators=downDuplocatorWrapper.fetchChildrenDuplicator(originalCell);
		if(childCellDownDuplicators==null){
			return;
		}
		for(CellDownDuplicator duplicator:childCellDownDuplicators){
			Cell newCell=duplicator.duplicateChildrenCell(downDuplicate, cell, originalCell,parentNonChild);
			newCellMap.put(duplicator.getCell(), newCell);
			processChildrenCells(newCell,duplicator.getCell(),newCellMap,downDuplicate,duplicator.isNonChild());
			duplicator.setNonChild(false);
		}
	}
}
