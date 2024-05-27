
package com.begcode.report.core.definition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.build.paging.HeaderFooter;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.expression.model.data.ObjectExpressionData;
import com.begcode.report.core.expression.model.data.ObjectListExpressionData;

import java.io.Serializable;
import java.util.List;


/**
 * @author Jacky.gao
 * @since 2017年1月16日
 */
public class HeaderFooterDefinition implements Serializable{
	private static final long serialVersionUID = -7239528314017768029L;
	private String left;
	private String center;
	private String right;
	private String fontFamily="宋体";
	private int fontSize=10;
	private String forecolor="0,0,0";
	private boolean bold;
	private boolean italic;
	private boolean underline;
	private int height=30;
	private int margin=30;
	@JsonIgnore
	private Expression leftExpression;
	@JsonIgnore
	private Expression centerExpression;
	@JsonIgnore
	private Expression rightExpression;
	public HeaderFooter buildHeaderFooter(int pageIndex, Context context){
		HeaderFooter hf=new HeaderFooter();
		hf.setBold(bold);
		hf.setFontFamily(fontFamily);
		hf.setFontSize(fontSize);
		hf.setForecolor(forecolor);
		hf.setHeight(height);
		hf.setItalic(italic);
		hf.setUnderline(underline);
		hf.setMargin(margin);
		context.setPageIndex(pageIndex);
		if(leftExpression!=null){
			Object obj = buildExpression(context,leftExpression);
			if(obj!=null){
				hf.setLeft(obj.toString());
			}
		}
		if(centerExpression!=null){
			Object obj = buildExpression(context,centerExpression);
			if(obj!=null){
				hf.setCenter(obj.toString());
			}
		}
		if(rightExpression!=null){
			Object obj = buildExpression(context,rightExpression);
			if(obj!=null){
				hf.setRight(obj.toString());
			}
		}
		return hf;
	}
	private Object buildExpression(Context context,Expression expr) {
		ExpressionData<?> data=expr.execute(context.getRootCell(), context.getRootCell(), context);
		Object obj=null;
		if(data instanceof ObjectExpressionData){
			obj=((ObjectExpressionData)data).getData();
		}else if(data instanceof ObjectListExpressionData){
			ObjectListExpressionData listData=(ObjectListExpressionData)data;
			if(listData!=null){
				List<?> list=listData.getData();
				if(list!=null && list.size()>0){
					obj="";
					for(Object o:list){
						if(o==null){
							continue;
						}
						if(!obj.equals("")){
							obj+=",";
						}
						obj+=o.toString();
					}
				}
			}
		}
		return obj;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public String getForecolor() {
		return forecolor;
	}
	public void setForecolor(String forecolor) {
		this.forecolor = forecolor;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	public boolean isUnderline() {
		return underline;
	}
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public Expression getLeftExpression() {
		return leftExpression;
	}
	public void setLeftExpression(Expression leftExpression) {
		this.leftExpression = leftExpression;
	}
	public Expression getCenterExpression() {
		return centerExpression;
	}
	public void setCenterExpression(Expression centerExpression) {
		this.centerExpression = centerExpression;
	}
	public Expression getRightExpression() {
		return rightExpression;
	}
	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}
}
