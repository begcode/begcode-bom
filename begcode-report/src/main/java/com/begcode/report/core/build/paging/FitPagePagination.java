
package com.begcode.report.core.build.paging;


import com.begcode.report.core.definition.Band;
import com.begcode.report.core.definition.Orientation;
import com.begcode.report.core.definition.Paper;
import com.begcode.report.core.model.Report;
import com.begcode.report.core.model.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月17日
 */
public class FitPagePagination extends BasePagination implements Pagination {
	@Override
	public List<Page> doPaging(Report report) {
		Paper paper=report.getPaper();
		int height=paper.getHeight()-paper.getBottomMargin()-paper.getTopMargin()-5;
		if(paper.getOrientation().equals(Orientation.landscape)){
			height=paper.getWidth()-paper.getBottomMargin()-paper.getTopMargin()-5;
		}
		List<Row> rows=report.getRows();
		List<Row> headerRows=report.getHeaderRepeatRows();
		List<Row> footerRows=report.getFooterRepeatRows();
		List<Row> titleRows=report.getTitleRows();
		List<Row> summaryRows=report.getSummaryRows();
		int repeatHeaderRowHeight=report.getRepeatHeaderRowHeight(),repeatFooterRowHeight=report.getRepeatFooterRowHeight();
		int titleRowHeight=report.getTitleRowsHeight();
		int rowHeight=titleRowHeight+repeatHeaderRowHeight+repeatFooterRowHeight;
		List<Page> pages=new ArrayList<Page>();
		List<Row> pageRows=new ArrayList<Row>();
		List<Row> pageRepeatHeaders=new ArrayList<Row>();
		List<Row> pageRepeatFooters=new ArrayList<Row>();
		pageRepeatHeaders.addAll(headerRows);
		pageRepeatFooters.addAll(footerRows);
		int i=0;
		int rowSize=rows.size();
		Row row=rows.get(i);
		int pageIndex=1;
		while(row!=null){
			int rowRealHeight=row.getRealHeight();
			if(rowRealHeight==0){
				continue;
			}
			Band band=row.getBand();
			if(band!=null){
				String rowKey=row.getRowKey();
				int index=-1;
				if(band.equals(Band.headerrepeat)){
					for(int j=0;j<pageRepeatHeaders.size();j++){
						Row headerRow=pageRepeatHeaders.get(j);
						if(headerRow.getRowKey().equals(rowKey)){
							index=j;
							break;
						}
					}
					pageRepeatHeaders.remove(index);
					pageRepeatHeaders.add(index,row);
				}else if(band.equals(Band.footerrepeat)){
					for(int j=0;j<pageRepeatFooters.size();j++){
						Row footerRow=pageRepeatFooters.get(j);
						if(footerRow.getRowKey().equals(rowKey)){
							index=j;
							break;
						}
					}
					pageRepeatFooters.remove(index);
					pageRepeatFooters.add(index,row);
				}
				continue;
			}
			rowHeight+=rowRealHeight+1;
			pageRows.add(row);
			row.setPageIndex(pageIndex);
			boolean overflow=false;
			if((i+1)<rows.size()){
				Row nextRow=rows.get(i+1);
				if((rowHeight+nextRow.getRealHeight()) > height){
					overflow=true;
				}
			}
			if(!overflow && row.isPageBreak()){
				overflow=true;
			}
			if(overflow){
				Page newPage=buildPage(pageRows,pageRepeatHeaders,pageRepeatFooters,titleRows,pageIndex,report);
				pageIndex++;
				pages.add(newPage);
				rowHeight=repeatHeaderRowHeight+repeatFooterRowHeight;
				pageRows=new ArrayList<Row>();
			}
			i++;
			if(i<rowSize){
				row=rows.get(i);
			}else{
				row=null;
			}
		}
		if(pageRows.size()>0){
			Page newPage=buildPage(pageRows,pageRepeatHeaders,pageRepeatFooters,titleRows,pageIndex,report);
			pages.add(newPage);
		}
		report.getContext().setTotalPages(pages.size());
		buildPageHeaderFooter(pages, report);
		buildSummaryRows(summaryRows, pages);
		return pages;
	}
}
