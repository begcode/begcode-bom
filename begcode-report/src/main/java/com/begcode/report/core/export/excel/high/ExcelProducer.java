
package com.begcode.report.core.export.excel.high;

import com.begcode.report.core.export.excel.high.builder.ExcelBuilderDirect;
import com.begcode.report.core.export.excel.high.builder.ExcelBuilderWithPaging;
import com.begcode.report.core.model.Report;

import java.io.OutputStream;


/**
 * @author Jacky.gao
 * @since 2016年12月30日
 */
public class ExcelProducer {
	private ExcelBuilderWithPaging excelBuilderWithPaging=new ExcelBuilderWithPaging();
	private ExcelBuilderDirect excelBuilderDirect=new ExcelBuilderDirect();
	public void produceWithPaging(Report report, OutputStream outputStream) {
		doProduce(report, outputStream, true,false);
	}
	public void produce(Report report, OutputStream outputStream) {
		doProduce(report, outputStream, false,false);
	}
	public void produceWithSheet(Report report, OutputStream outputStream) {
		doProduce(report, outputStream, true,true);
	}

	private void doProduce(Report report, OutputStream outputStream,boolean withPaging,boolean withSheet) {
		if(withPaging){
			excelBuilderWithPaging.build(report, outputStream, withSheet);
		}else{
			excelBuilderDirect.build(report, outputStream);
		}
	}
}
