
package com.begcode.report.core.build.cell.down;


import com.begcode.report.core.build.Context;
import com.begcode.report.core.build.cell.DuplicateType;
import com.begcode.report.core.definition.BlankCellInfo;
import com.begcode.report.core.definition.value.SimpleValue;
import com.begcode.report.core.definition.value.Value;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Row;

/**
 * @author Jacky.gao
 * @since 2016年11月7日
 */
public class CellDownDuplicator {
	private Cell cell;
	private int cellRowNumber;
	private DuplicateType duplicateType;
	private BlankCellInfo blankCellInfo;
	private boolean nonChild=false;
	public CellDownDuplicator(Cell cell,DuplicateType duplicateType,int cellRowNumber) {
		this.cell=cell;
		this.cellRowNumber=cellRowNumber;
		this.duplicateType=duplicateType;
	}
	public CellDownDuplicator(Cell cell,DuplicateType duplicateType,BlankCellInfo blankCellInfo,int cellRowNumber) {
		this.cell=cell;
		if(cellRowNumber==0){
			this.cellRowNumber=cell.getRow().getRowNumber();
		}else{
			this.cellRowNumber=cellRowNumber;
		}
		this.duplicateType=duplicateType;
		this.blankCellInfo=blankCellInfo;
	}

	public Cell duplicate(DownDuplicate downDuplicate,Cell newMainCell){
		switch(duplicateType){
		case Blank:
			processBlankCell(downDuplicate,newMainCell);
			break;
		case Self:
			processSelfBlankCell(downDuplicate);
			break;
		case IncreseSpan:
			processIncreaseSpanCell(downDuplicate);
			break;
		case Duplicate:
			throw new ReportComputeException("Invalid duplicator.");
		}
		return null;
	}

	public Cell duplicateChildrenCell(DownDuplicate downDuplicate,Cell leftParent,Cell originalCell,boolean parentNonChild){
		Cell newCell=cell.newCell();
		Row newRow=downDuplicate.newRow(newCell.getRow(),cellRowNumber);
		newRow.getCells().add(newCell);
		newCell.getColumn().getCells().add(newCell);
		newCell.setRow(newRow);
		if(newCell.getLeftParentCell()==originalCell){
			newCell.setLeftParentCell(leftParent);
			if(parentNonChild){
				nonChild=true;
			}
		}else{
			nonChild=true;
		}
		Cell leftParentCell=newCell.getLeftParentCell();
		if(leftParentCell!=null){
			leftParentCell.addRowChild(newCell);
		}
		Cell topParentCell=newCell.getTopParentCell();
		if(topParentCell!=null){
			topParentCell.addColumnChild(newCell);
		}
		Context context=downDuplicate.getContext();
		Value value=newCell.getValue();
		if(value instanceof SimpleValue){
			newCell.setData(value.getValue());
			newCell.setProcessed(true);
			context.addReportCell(newCell);
		}else{
			if(nonChild){
				newCell.setValue(new SimpleValue(""));
				context.addBlankCell(newCell);
			}else{
				context.addCell(newCell);
			}
		}
		return newCell;
	}

	private void processBlankCell(DownDuplicate downDuplicate,Cell newMainCell){
		Context context=downDuplicate.getContext();
		Cell newBlankCell=cell.newRowBlankCell(context,blankCellInfo,downDuplicate.getMainCell());
		if(blankCellInfo.isParent() && newMainCell.getLeftParentCell()==cell){
			newMainCell.setLeftParentCell(newBlankCell);
		}
		Row newRow=downDuplicate.newRow(newBlankCell.getRow(),cellRowNumber);
		newRow.getCells().add(newBlankCell);
		newBlankCell.getColumn().getCells().add(newBlankCell);
		newBlankCell.setRow(newRow);
		context.addReportCell(newBlankCell);
	}

	private void processSelfBlankCell(DownDuplicate downDuplicate){
		Cell newBlankCell=cell.newCell();
		newBlankCell.setValue(new SimpleValue(""));
		Row newRow=downDuplicate.newRow(newBlankCell.getRow(),cellRowNumber);
		newRow.getCells().add(newBlankCell);
		newBlankCell.getColumn().getCells().add(newBlankCell);
		newBlankCell.setRow(newRow);
		Cell leftParentCell=newBlankCell.getLeftParentCell();
		if(leftParentCell!=null){
			leftParentCell.addRowChild(newBlankCell);
		}
		Cell topParentCell=newBlankCell.getTopParentCell();
		if(topParentCell!=null){
			topParentCell.addColumnChild(newBlankCell);
		}
		Context context=downDuplicate.getContext();
		context.addBlankCell(newBlankCell);
	}


	private void processIncreaseSpanCell(DownDuplicate downDuplicate){
		int rowSpan=cell.getRowSpan();
		rowSpan+=downDuplicate.getRowSize();
		if(rowSpan==1){
			rowSpan++;
		}
		cell.setRowSpan(rowSpan);
	}

	public DuplicateType getDuplicateType() {
		return duplicateType;
	}
	public Cell getCell() {
		return cell;
	}
	public boolean isNonChild() {
		return nonChild;
	}
	public void setNonChild(boolean nonChild) {
		this.nonChild = nonChild;
	}
}
