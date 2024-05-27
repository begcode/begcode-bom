
package com.begcode.report.core.build.paging;

import com.begcode.report.core.build.BindData;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.definition.PagingMode;
import com.begcode.report.core.definition.Paper;
import com.begcode.report.core.model.Cell;
import com.begcode.report.core.model.Report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年1月17日
 */
public class PagingBuilder {
	private static Map<PagingMode,Pagination> paginationMap=new HashMap<PagingMode,Pagination>();
	static{
		paginationMap.put(PagingMode.fitpage, new FitPagePagination());
		paginationMap.put(PagingMode.fixrows, new FixRowsPagination());
	}
	public static List<Page> buildPages(Report report){
		Paper paper = report.getPaper();
		Pagination pagination=paginationMap.get(paper.getPagingMode());
		List<Page> pages=pagination.doPaging(report);
		computeExistPageFunctionCells(report);
		return pages;
	}
	public static void computeExistPageFunctionCells(Report report) {
		Context context=report.getContext();
		List<Cell> existPageFunctionCells=context.getExistPageFunctionCells();
		for(Cell cell:existPageFunctionCells){
			List<BindData> dataList=context.buildCellData(cell);
			if(dataList==null || dataList.size()==0){
				continue;
			}
			BindData bindData=dataList.get(0);
			cell.setData(bindData.getValue());
			cell.setBindData(bindData.getDataList());
			cell.doFormat();
			cell.doDataWrapCompute(context);
		}
	}
}
