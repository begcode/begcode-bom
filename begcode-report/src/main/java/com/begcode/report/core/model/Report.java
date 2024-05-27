package com.begcode.report.core.model;

import com.begcode.report.core.build.Context;
import com.begcode.report.core.build.paging.Page;
import com.begcode.report.core.build.paging.PagingBuilder;
import com.begcode.report.core.definition.Band;
import com.begcode.report.core.definition.ConditionPropertyItem;
import com.begcode.report.core.definition.HeaderFooterDefinition;
import com.begcode.report.core.definition.Paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2016年11月1日
 */
public class Report {
	private Paper paper;
	private HeaderFooterDefinition header;
	private HeaderFooterDefinition footer;
	private Cell rootCell;
	private Context context;
	private List<Row> rows;
	private List<Row> headerRepeatRows= new ArrayList<>();
	private List<Row> footerRepeatRows= new ArrayList<>();
	private List<Row> titleRows= new ArrayList<>();
	private List<Row> summaryRows= new ArrayList<>();
	private int repeatHeaderRowHeight=0,repeatFooterRowHeight=0,titleRowsHeight=0,summaryRowsHeight=0;
	private List<Column> columns;
	private List<Page> pages;
	private String reportFullName;
	private List<Cell> lazyComputeCells= new ArrayList<>();
	private Map<Row,Map<Column,Cell>> rowColCellMap= new HashMap<>();
	private Map<String,List<Cell>> cellsMap= new HashMap<>();
	public void insertRow(Row row,int rowNumber){
		int pos=rowNumber-1;
		rows.add(pos,row);
		Band band=row.getBand();
		if(band==null){
			return;
		}
	}
	public void insertRows(int firstRowIndex,List<Row> insertRows){
		int pos=firstRowIndex-1;
		rows.addAll(pos,insertRows);
	}
	public void insertColumn(Column column,int columnNumber){
		int pos=columnNumber-1;
		columns.add(pos,column);
	}

	public void insertColumns(int firstColumnIndex,List<Column> insertColumns){
		int pos=firstColumnIndex-1;
		columns.addAll(pos, insertColumns);
	}

	public Row getRow(int rowNumber){
		if(rowNumber>rows.size()){
			return null;
		}
		return rows.get(rowNumber-1);
	}

	public Column getColumn(int columnNumber){
		if(columnNumber>columns.size()){
			return null;
		}
		return columns.get(columnNumber-1);
	}

	public Cell getRootCell() {
		return rootCell;
	}
	public void setRootCell(Cell rootCell) {
		this.rootCell = rootCell;
	}
	public boolean addCell(Cell cell){
		String cellName=cell.getName();
		List<Cell> cells=null;
		if(cellsMap.containsKey(cellName)){
			cells=cellsMap.get(cellName);
		}else{
			cells=new ArrayList<Cell>();
			cellsMap.put(cellName, cells);
		}
		cells.add(cell);
		Row row=cell.getRow();
		Column col=cell.getColumn();
		Map<Column,Cell> colMap=null;
		if(rowColCellMap.containsKey(row)){
			colMap=rowColCellMap.get(row);
		}else{
			colMap=new HashMap<Column,Cell>();
			rowColCellMap.put(row, colMap);
		}
		colMap.put(col, cell);
		return addLazyCell(cell);
	}

	public boolean addLazyCell(Cell cell){
		List<ConditionPropertyItem> conditionPropertyItems=cell.getConditionPropertyItems();
		if(conditionPropertyItems!=null && conditionPropertyItems.size()>0){
			lazyComputeCells.add(cell);
			return true;
		}
		return false;
	}

	public Map<Row, Map<Column, Cell>> getRowColCellMap() {
		return rowColCellMap;
	}

	public List<Page> getPages() {
		if(pages==null){
			pages= PagingBuilder.buildPages(this);
		}
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public void rePaging(Paper paper){
		paper.setColumnCount(this.paper.getColumnCount());
		paper.setColumnEnabled(this.paper.isColumnEnabled());
		paper.setFixRows(this.paper.getFixRows());
		paper.setPagingMode(this.paper.getPagingMode());
		setPaper(paper);
		pages=PagingBuilder.buildPages(this);
	}

	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}

	public String getReportFullName() {
		return reportFullName;
	}
	public void setReportFullName(String reportFullName) {
		this.reportFullName = reportFullName;
	}
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public Map<String, List<Cell>> getCellsMap() {
		return cellsMap;
	}
	public List<Cell> getLazyComputeCells() {
		return lazyComputeCells;
	}
	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public List<Row> getHeaderRepeatRows() {
		return headerRepeatRows;
	}
	public void setHeaderRepeatRows(List<Row> headerRepeatRows) {
		this.headerRepeatRows = headerRepeatRows;
	}
	public List<Row> getFooterRepeatRows() {
		return footerRepeatRows;
	}
	public void setFooterRepeatRows(List<Row> footerRepeatRows) {
		this.footerRepeatRows = footerRepeatRows;
	}
	public List<Row> getTitleRows() {
		return titleRows;
	}
	public List<Row> getSummaryRows() {
		return summaryRows;
	}

	public int getRepeatHeaderRowHeight() {
		return repeatHeaderRowHeight;
	}
	public void setRepeatHeaderRowHeight(int repeatHeaderRowHeight) {
		this.repeatHeaderRowHeight = repeatHeaderRowHeight;
	}
	public int getRepeatFooterRowHeight() {
		return repeatFooterRowHeight;
	}
	public void setRepeatFooterRowHeight(int repeatFooterRowHeight) {
		this.repeatFooterRowHeight = repeatFooterRowHeight;
	}
	public int getTitleRowsHeight() {
		return titleRowsHeight;
	}
	public void setTitleRowsHeight(int titleRowsHeight) {
		this.titleRowsHeight = titleRowsHeight;
	}
	public int getSummaryRowsHeight() {
		return summaryRowsHeight;
	}
	public void setSummaryRowsHeight(int summaryRowsHeight) {
		this.summaryRowsHeight = summaryRowsHeight;
	}
	public HeaderFooterDefinition getHeader() {
		return header;
	}
	public void setHeader(HeaderFooterDefinition header) {
		this.header = header;
	}
	public HeaderFooterDefinition getFooter() {
		return footer;
	}
	public void setFooter(HeaderFooterDefinition footer) {
		this.footer = footer;
	}
}
