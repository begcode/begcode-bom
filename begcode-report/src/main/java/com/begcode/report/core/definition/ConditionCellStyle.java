
package com.begcode.report.core.definition;

/**
 * @author Jacky.gao
 * @since 2017年4月24日
 */
public class ConditionCellStyle extends CellStyle{
	private static final long serialVersionUID = -3295823703567508310L;
	private int fontSize;
	private String fontFamily;
	private Scope bgcolorScope=Scope.cell;
	private Scope forecolorScope=Scope.cell;
	private Scope fontSizeScope=Scope.cell;
	private Scope fontFamilyScope=Scope.cell;
	private Scope alignScope=Scope.cell;
	private Scope valignScope=Scope.cell;
	private Scope boldScope=Scope.cell;
	private Scope italicScope=Scope.cell;
	private Scope underlineScope=Scope.cell;
	public Scope getBgcolorScope() {
		return bgcolorScope;
	}

	public void setBgcolorScope(Scope bgcolorScope) {
		this.bgcolorScope = bgcolorScope;
	}

	public Scope getForecolorScope() {
		return forecolorScope;
	}

	public void setForecolorScope(Scope forecolorScope) {
		this.forecolorScope = forecolorScope;
	}

	public Scope getFontSizeScope() {
		return fontSizeScope;
	}

	public void setFontSizeScope(Scope fontSizeScope) {
		this.fontSizeScope = fontSizeScope;
	}

	public Scope getFontFamilyScope() {
		return fontFamilyScope;
	}

	public void setFontFamilyScope(Scope fontFamilyScope) {
		this.fontFamilyScope = fontFamilyScope;
	}

	public Scope getAlignScope() {
		return alignScope;
	}

	public void setAlignScope(Scope alignScope) {
		this.alignScope = alignScope;
	}

	public Scope getValignScope() {
		return valignScope;
	}

	public void setValignScope(Scope valignScope) {
		this.valignScope = valignScope;
	}

	public Scope getBoldScope() {
		return boldScope;
	}

	public void setBoldScope(Scope boldScope) {
		this.boldScope = boldScope;
	}

	public Scope getItalicScope() {
		return italicScope;
	}

	public void setItalicScope(Scope italicScope) {
		this.italicScope = italicScope;
	}

	public Scope getUnderlineScope() {
		return underlineScope;
	}

	public void setUnderlineScope(Scope underlineScope) {
		this.underlineScope = underlineScope;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
}
