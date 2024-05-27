
package com.begcode.report.core.export;

import com.begcode.report.core.build.paging.Page;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年3月23日
 */
public class SinglePageData {
	private int totalPages;
	private int pageIndex;
	private List<Page> pages;
	private int columnMargin;

	public SinglePageData(int totalPages, int columnMargin,List<Page> pages) {
		this.totalPages = totalPages;
		this.columnMargin=columnMargin;
		this.pages = pages;
	}
	public SinglePageData(int totalPages, int pageIndex, int columnMargin,List<Page> pages) {
		this.totalPages = totalPages;
		this.pageIndex = pageIndex;
		this.columnMargin=columnMargin;
		this.pages = pages;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public List<Page> getPages() {
		return pages;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getColumnMargin() {
		return columnMargin;
	}
}
