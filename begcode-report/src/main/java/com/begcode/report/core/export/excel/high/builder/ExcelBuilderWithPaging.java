
package com.begcode.report.core.export.excel.high.builder;

import com.begcode.report.core.Utils;
import com.begcode.report.core.build.paging.Page;
import com.begcode.report.core.chart.ChartData;
import com.begcode.report.core.definition.Paper;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.export.excel.high.CellStyleContext;
import com.begcode.report.core.model.Column;
import com.begcode.report.core.model.Image;
import com.begcode.report.core.model.Report;
import com.begcode.report.core.model.Row;
import com.begcode.report.core.utils.ImageUtils;
import com.begcode.report.core.utils.UnitUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年8月10日
 */
public class ExcelBuilderWithPaging extends ExcelBuilder{
	public void build(Report report, OutputStream outputStream, boolean withSheet) {
		CellStyleContext cellStyleContext=new CellStyleContext();
		XSSFWorkbook wb = new XSSFWorkbook();
		CreationHelper creationHelper=wb.getCreationHelper();
		Paper paper=report.getPaper();
		try{
			List<Column> columns=report.getColumns();
			Map<Row,Map<Column, com.begcode.report.core.model.Cell>> cellMap=report.getRowColCellMap();
			int columnSize=columns.size();
			List<Page> pages=report.getPages();
			int rowNumber=0,pageIndex=1;
			Sheet sheet=null;
			for(Page page:pages){
				if(withSheet){
					sheet=createSheet(wb, paper, "第"+pageIndex+"页");
					rowNumber=0;
				}else if(sheet==null){
					sheet=createSheet(wb, paper, null);
				}
				pageIndex++;
				Drawing<?> drawing=sheet.createDrawingPatriarch();
				List<Row> rows=page.getRows();
				for(int rowIndex=0;rowIndex<rows.size();rowIndex++){
					Row r=rows.get(rowIndex);
					org.apache.poi.ss.usermodel.Row row = sheet.getRow(rowNumber);
		        	if(row==null){
		        		row=sheet.createRow(rowNumber);
		        	}
		        	Map<Column, com.begcode.report.core.model.Cell> colCell=cellMap.get(r);
		        	int skipCol=0;
		        	for(int i=0;i<columnSize;i++){
		        		Column col=columns.get(i);
		        		int w=col.getWidth();
		        		if(w<1){
		        			skipCol++;
		        			continue;
		        		}
		        		int colNum=i-skipCol;
		        		double colWidth= UnitUtils.pointToPixel(w)*37.5;
		        		sheet.setColumnWidth(colNum,(short)colWidth);
		        		org.apache.poi.ss.usermodel.Cell cell = row.getCell(colNum);
		        		if(cell!=null){
		        			continue;
		        		}
		        		cell=row.createCell(colNum);
		        		com.begcode.report.core.model.Cell cellInfo=null;
		        		if(colCell!=null){
		        			cellInfo=colCell.get(col);
		        		}
		        		if(cellInfo==null){
		        			continue;
		        		}
		        		XSSFCellStyle style = cellStyleContext.produceXSSFCellStyle(wb,cellInfo);
		        		int colSpan=cellInfo.getColSpan();
		        		int rowSpan=cellInfo.getPageRowSpan();
		        		int rowStart=rowNumber;
		        		int rowEnd=rowSpan;
		        		if(rowSpan==0){
		        			rowEnd++;
		        		}
		        		rowEnd+=rowNumber;
		        		int colStart=i;
		        		int colEnd=colSpan;
		        		if(colSpan==0){
		        			colEnd++;
		        		}
		        		colEnd+=i;
		        		for(int j=rowStart;j<rowEnd;j++){
		        			org.apache.poi.ss.usermodel.Row rr=sheet.getRow(j);
		        			if(rr==null){
		        				rr=sheet.createRow(j);
		        			}
		        			for(int c=colStart;c<colEnd;c++){
		        				Cell cc=rr.getCell(c-skipCol);
		        				if(cc==null){
		        					cc=rr.createCell(c-skipCol);
		        				}
		        				cc.setCellStyle(style);
		        			}
		        		}
		        		if(colSpan>0 || rowSpan>0){
		        			if(rowSpan>0){
		        				rowSpan--;
		        			}
		        			if(colSpan>0){
		        				colSpan--;
		        			}
		        			CellRangeAddress cellRegion=new CellRangeAddress(rowNumber,(rowNumber+rowSpan),i-skipCol,(i-skipCol+colSpan));
		        			sheet.addMergedRegion(cellRegion);
		        		}
		        		Object obj=cellInfo.getFormatData();
		        		if(obj!=null){
			        		if(obj instanceof String){
			        			cell.setCellValue((String)obj);
			        		}else if(obj instanceof Number){
			        			BigDecimal bigDecimal= Utils.toBigDecimal(obj);
			        			cell.setCellValue(bigDecimal.doubleValue());
			        		}else if(obj instanceof Boolean){
			        			cell.setCellValue((Boolean)obj);
			        		}else if(obj instanceof Image){
			        			Image img=(Image)obj;
			        			InputStream inputStream= ImageUtils.base64DataToInputStream(img.getBase64Data());
			    				BufferedImage bufferedImage=ImageIO.read(inputStream);
			    				int width=bufferedImage.getWidth();
			    				int height=bufferedImage.getHeight();
			    				IOUtils.closeQuietly(inputStream);
			    				inputStream=ImageUtils.base64DataToInputStream(img.getBase64Data());

			        			try{
			        				XSSFClientAnchor anchor=(XSSFClientAnchor)creationHelper.createClientAnchor();
			        				byte[] bytes=IOUtils.toByteArray(inputStream);
			        				int pictureFormat=buildImageFormat(img);
			        				int pictureIndex=wb.addPicture(bytes, pictureFormat);
			        				anchor.setCol1(i);
			        				anchor.setCol2(i+colSpan);
			        				anchor.setRow1(rowNumber);
			        				anchor.setRow2(rowNumber+rowSpan);
			        				anchor.setDx1(0);
			        				anchor.setDx2(width * Units.EMU_PER_PIXEL);
			        				anchor.setDy1(0);
			        				anchor.setDy2(height * Units.EMU_PER_PIXEL);
			        				drawing.createPicture(anchor, pictureIndex);
			        			}finally{
			        				IOUtils.closeQuietly(inputStream);
			        			}
			        		}else if(obj instanceof ChartData){
			        			ChartData chartData=(ChartData)obj;
			        			String base64Data=chartData.retriveBase64Data();
			        			if(base64Data!=null){
			        				Image img=new Image(base64Data,chartData.getWidth(),chartData.getHeight());
			        				InputStream inputStream=ImageUtils.base64DataToInputStream(img.getBase64Data());
			        				BufferedImage bufferedImage=ImageIO.read(inputStream);
			        				int width=bufferedImage.getWidth();
			        				int height=bufferedImage.getHeight();
			        				IOUtils.closeQuietly(inputStream);
			        				inputStream=ImageUtils.base64DataToInputStream(img.getBase64Data());

			        				try{
			        					XSSFClientAnchor anchor=(XSSFClientAnchor)creationHelper.createClientAnchor();
			        					byte[] bytes=IOUtils.toByteArray(inputStream);
			        					int pictureFormat=buildImageFormat(img);
			        					int pictureIndex=wb.addPicture(bytes, pictureFormat);
			        					anchor.setCol1(i);
			        					anchor.setCol2(i+colSpan);
			        					anchor.setRow1(rowNumber);
			        					anchor.setRow2(rowNumber+rowSpan);
			        					anchor.setDx1(0);
			        					anchor.setDx2(width * Units.EMU_PER_PIXEL);
			        					anchor.setDy1(0);
			        					anchor.setDy2(height * Units.EMU_PER_PIXEL);
			        					drawing.createPicture(anchor, pictureIndex);
			        				}finally{
			        					IOUtils.closeQuietly(inputStream);
			        				}
			        			}
			        		}else if(obj instanceof Date){
			        			cell.setCellValue((Date)obj);
			        		}
		        		}
		        	}
		        	row.setHeight((short)UnitUtils.pointToTwip(r.getHeight()));
		        	rowNumber++;
				}
				sheet.setRowBreak(rowNumber-1);
			}
			wb.write(outputStream);
		}catch(Exception ex){
			throw new ReportComputeException(ex);
		}finally{
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
