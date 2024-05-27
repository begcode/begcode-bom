
package com.begcode.report.core.build.cell.down;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.ConditionPropertyItem;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Column;
import com.begcode.report.core.model.Row;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年3月2日
 */
public class DownBlankCellApply {
	private int rowSize;
	private Cell cell;
	private Context context;
	private DownDuplocatorWrapper downDuplocatorWrapper;
	public DownBlankCellApply(int rowSize,Cell cell,Context context,DownDuplocatorWrapper downDuplocatorWrapper) {
		this.rowSize=rowSize;
		this.cell=cell;
		this.context=context;
		this.downDuplocatorWrapper=downDuplocatorWrapper;
	}
	public boolean useBlankCell(int index, BindData bindData){
		if(context.getBlankCellsMap().size()==0){
			return false;
		}
		int nextRowNumber=cell.getRow().getRowNumber()+rowSize*(index-1)+rowSize;
		Row nextRow=context.getRow(nextRowNumber);
		Cell blankCell=null;
		if(nextRow!=null){
			blankCell=context.getBlankCell(nextRow, cell.getColumn());
		}
		if(blankCell==null){
			return false;
		}
		context.removeBlankCell(blankCell);
		blankCell.setValue(cell.getValue());
		blankCell.setProcessed(true);
		blankCell.setData(bindData.getValue());
		blankCell.setFormatData(bindData.getLabel());
		blankCell.setBindData(bindData.getDataList());
		blankCell.setConditionPropertyItems(cell.getConditionPropertyItems());
		List<ConditionPropertyItem> conditionPropertyItems=blankCell.getConditionPropertyItems();
		if(conditionPropertyItems!=null && conditionPropertyItems.size()>0){
			context.getReport().getLazyComputeCells().add(blankCell);
		}else{
			blankCell.doFormat();
			blankCell.doDataWrapCompute(context);
		}

		processChildrenCell(cell,blankCell,index);
		return true;
	}
	private void processChildrenCell(Cell originalCell,Cell leftParentCell,int index){
		List<CellDownDuplicator> children=downDuplocatorWrapper.fetchChildrenDuplicator(originalCell);
		if(children==null){
			return;
		}
		for(CellDownDuplicator child:children){
			Cell childCell=child.getCell();
			Cell targetCell=getChildBlankCell(childCell,index);
			if(targetCell==null){
				continue;
			}
			context.removeBlankCell(targetCell);
			targetCell.setLeftParentCell(leftParentCell);
			targetCell.setValue(childCell.getValue());
			if(targetCell.getTopParentCell()==originalCell){
				targetCell.setTopParentCell(leftParentCell);
			}
			context.addUnprocessedCell(targetCell);
			processChildrenCell(childCell,targetCell,index);
		}
	}

	private Cell getChildBlankCell(Cell childCell,int index){
		int nextChildRowNumber=childCell.getRow().getRowNumber()+rowSize*(index-1)+rowSize;
		Row nextChildRow=context.getRow(nextChildRowNumber);
		Column col=childCell.getColumn();
		Cell targetCell=context.getBlankCell(nextChildRow, col);
		return targetCell;
	}
}
