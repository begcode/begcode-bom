
package com.begcode.report.core.build.paging;

import com.begcode.report.core.model.Report;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2017年1月17日
 */
public interface Pagination {
	List<Page> doPaging(Report report);
}
