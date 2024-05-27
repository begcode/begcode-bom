
package com.begcode.report.core.export;

import com.begcode.report.core.build.paging.Page;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年3月23日
 */
public class FullPageData {
	private int totalPages;
	private int columnMargin;
	private List<List<Page>> pageList;

	public FullPageData(int totalPages, int columnMargin,List<List<Page>> pageList) {
		this.totalPages = totalPages;
		this.columnMargin = columnMargin;
		this.pageList = pageList;
	}
	public int getColumnMargin() {
		return columnMargin;
	}
	public List<List<Page>> getPageList() {
		return pageList;
	}
	public int getTotalPages() {
		return totalPages;
	}
}
