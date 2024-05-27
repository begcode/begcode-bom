
package com.begcode.report.core.build.cell.right;

import com.begcode.report.core.build.cell.DuplicateType;
import com.begcode.report.core.model.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年2月25日
 */
public class RightDuplocatorWrapper {
	private String mainCellName;
	private List<CellRightDuplicator> mainCellChildren=new ArrayList<CellRightDuplicator>();
	private List<CellRightDuplicator> cellDuplicators=new ArrayList<CellRightDuplicator>();
	private Map<Cell,List<CellRightDuplicator>> createNewDuplicatorsMap=new HashMap<Cell,List<CellRightDuplicator>>();
	private List<Cell> duplicatorCells=new ArrayList<Cell>();
	public RightDuplocatorWrapper(String mainCellName) {
		this.mainCellName=mainCellName;
	}

	public void addCellRightDuplicator(CellRightDuplicator duplicator){
		if(duplicator.getDuplicateType().equals(DuplicateType.Duplicate)){
			addCellRightDuplicatorToMap(duplicator);
		}else{
			cellDuplicators.add(duplicator);
			duplicatorCells.add(duplicator.getCell());
		}
	}

	private void addCellRightDuplicatorToMap(CellRightDuplicator duplicator){
		Cell topParentCell=duplicator.getCell().getTopParentCell();
		if(topParentCell.getName().equals(mainCellName)){
			mainCellChildren.add(duplicator);
		}
		List<CellRightDuplicator> list=null;
		if(createNewDuplicatorsMap.containsKey(topParentCell)){
			list=createNewDuplicatorsMap.get(topParentCell);
		}else{
			list=new ArrayList<CellRightDuplicator>();
			createNewDuplicatorsMap.put(topParentCell, list);
		}
		list.add(duplicator);
	}

	public boolean contains(Cell cell){
		return duplicatorCells.contains(cell);
	}

	public List<CellRightDuplicator> getMainCellChildren() {
		return mainCellChildren;
	}

	public List<CellRightDuplicator> fetchChildrenDuplicator(Cell topParentCell){
		return createNewDuplicatorsMap.get(topParentCell);
	}

	public List<CellRightDuplicator> getCellDuplicators() {
		return cellDuplicators;
	}
}
