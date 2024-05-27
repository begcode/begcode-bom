
package com.begcode.report.core.provider.report;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.begcode.report.console.conf.serialize.CustomDateSerialize;

import java.util.Date;

/**
 * @author Jacky.gao
 * @since 2017年2月11日
 */
public class ReportFile {
	private String name;

	@JsonSerialize(using= CustomDateSerialize.class)
	private Date updateDate;

	public ReportFile(String name, Date updateDate) {
		this.name = name;
		this.updateDate = updateDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
