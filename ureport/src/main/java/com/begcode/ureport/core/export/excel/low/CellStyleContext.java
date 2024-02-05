package com.begcode.ureport.core.export.excel.low;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.begcode.ureport.core.definition.Alignment;
import com.begcode.ureport.core.definition.Border;
import com.begcode.ureport.core.definition.CellStyle;
import com.begcode.ureport.core.model.Cell;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


/**
 * @author Jacky.gao
 * @since 2017年7月3日
 */
public class CellStyleContext {
    private final Map<String, HSSFCellStyle> styleMap = new HashMap<String, HSSFCellStyle>();

    public HSSFCellStyle produceXSSFCellStyle(HSSFWorkbook wb, Cell cell) {
        String name = cell.getName();
        if (cell.getCustomCellStyle() == null) {
            if (styleMap.containsKey(name)) {
                return styleMap.get(name);
            } else {
                HSSFCellStyle cellStyle = createNewCellStyle(wb, cell);
                styleMap.put(name, cellStyle);
                return cellStyle;
            }
        } else {
            String styleId = name + "=>" + buildStyleId(cell);
            if (styleMap.containsKey(styleId)) {
                return styleMap.get(styleId);
            } else {
                HSSFCellStyle cellStyle = createNewCellStyle(wb, cell);
                styleMap.put(styleId, cellStyle);
                return cellStyle;
            }
        }
    }

    private HSSFCellStyle createNewCellStyle(HSSFWorkbook wb, Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        CellStyle customStyle = cell.getCustomCellStyle();
        CellStyle rowStyle = cell.getRow().getCustomCellStyle();
        CellStyle colStyle = cell.getColumn().getCustomCellStyle();
        HSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        String bgcolor = cellStyle.getBgcolor();
        if (customStyle != null && StringUtils.isNotBlank(customStyle.getBgcolor())) {
            bgcolor = customStyle.getBgcolor();
        }
        if (rowStyle != null && StringUtils.isNotBlank(rowStyle.getBgcolor())) {
            bgcolor = rowStyle.getBgcolor();
        }
        if (colStyle != null && StringUtils.isNotBlank(colStyle.getBgcolor())) {
            bgcolor = colStyle.getBgcolor();
        }
        if (StringUtils.isNotEmpty(bgcolor)) {
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            HSSFColor hssfColor = buildHSSFColor(wb, bgcolor);
            style.setFillForegroundColor(hssfColor.getIndex());
        }
        Border topBorder = cellStyle.getTopBorder();
        if (customStyle != null && customStyle.getTopBorder() != null) {
            topBorder = customStyle.getTopBorder();
        }
        if (topBorder != null) {
            BorderStyle borderStyle = getBorderStyle(topBorder);
            HSSFColor borderColor = buildHSSFColor(wb, topBorder.getColor());
            style.setTopBorderColor(borderColor.getIndex());
            style.setBorderTop(borderStyle);
        }
        Border bottomBorder = cellStyle.getBottomBorder();
        if (customStyle != null && customStyle.getBottomBorder() != null) {
            bottomBorder = customStyle.getBottomBorder();
        }
        if (bottomBorder != null) {
            BorderStyle borderStyle = getBorderStyle(bottomBorder);
            HSSFColor borderColor = buildHSSFColor(wb, bottomBorder.getColor());
            style.setBottomBorderColor(borderColor.getIndex());
            style.setBorderBottom(borderStyle);
        }
        Border leftBorder = cellStyle.getLeftBorder();
        if (customStyle != null && customStyle.getLeftBorder() != null) {
            leftBorder = customStyle.getLeftBorder();
        }
        if (leftBorder != null) {
            BorderStyle borderStyle = getBorderStyle(leftBorder);
            HSSFColor borderColor = buildHSSFColor(wb, leftBorder.getColor());
            style.setLeftBorderColor(borderColor.getIndex());
            style.setBorderLeft(borderStyle);
        }
        Border rightBorder = cellStyle.getRightBorder();
        if (customStyle != null && customStyle.getRightBorder() != null) {
            rightBorder = customStyle.getRightBorder();
        }
        if (rightBorder != null) {
            BorderStyle borderStyle = getBorderStyle(rightBorder);
            HSSFColor borderColor = buildHSSFColor(wb, rightBorder.getColor());
            style.setRightBorderColor(borderColor.getIndex());
            style.setBorderRight(borderStyle);
        }
        Alignment align = cellStyle.getAlign();
        if (customStyle != null && customStyle.getAlign() != null) {
            align = customStyle.getAlign();
        }
        if (rowStyle != null && rowStyle.getAlign() != null) {
            align = rowStyle.getAlign();
        }
        if (colStyle != null && colStyle.getAlign() != null) {
            align = colStyle.getAlign();
        }
        if (align != null) {
            if (align.equals(Alignment.left)) {
                style.setAlignment(HorizontalAlignment.LEFT);
            } else if (align.equals(Alignment.center)) {
                style.setAlignment(HorizontalAlignment.CENTER);
            } else if (align.equals(Alignment.right)) {
                style.setAlignment(HorizontalAlignment.RIGHT);
            }
        }
        Alignment valign = cellStyle.getValign();
        if (customStyle != null && customStyle.getValign() != null) {
            valign = customStyle.getValign();
        }
        if (rowStyle != null && rowStyle.getValign() != null) {
            valign = rowStyle.getValign();
        }
        if (colStyle != null && colStyle.getValign() != null) {
            valign = colStyle.getValign();
        }
        if (valign != null) {
            if (valign.equals(Alignment.top)) {
                style.setVerticalAlignment(VerticalAlignment.TOP);
            } else if (valign.equals(Alignment.middle)) {
                style.setVerticalAlignment(VerticalAlignment.CENTER);
            } else if (valign.equals(Alignment.bottom)) {
                style.setVerticalAlignment(VerticalAlignment.BOTTOM);
            }
        }
        HSSFFont font = wb.createFont();
        font.setFontHeight((short) (cellStyle.getFontSize() * 20));
        Boolean bold = cellStyle.getBold();
        if (customStyle != null && customStyle.getBold() != null) {
            bold = customStyle.getBold();
        }
        if (rowStyle != null && rowStyle.getBold() != null) {
            bold = rowStyle.getBold();
        }
        if (colStyle != null && colStyle.getBold() != null) {
            bold = colStyle.getBold();
        }
        if (bold != null) {
            font.setBold(bold);
        }
        Boolean italic = cellStyle.getItalic();
        if (customStyle != null && customStyle.getItalic() != null) {
            italic = customStyle.getItalic();
        }
        if (rowStyle != null && rowStyle.getItalic() != null) {
            italic = rowStyle.getItalic();
        }
        if (colStyle != null && colStyle.getItalic() != null) {
            italic = colStyle.getItalic();
        }
        if (italic != null) {
            font.setItalic(italic);
        }
        Boolean underline = cellStyle.getUnderline();
        if (customStyle != null && customStyle.getUnderline() != null) {
            underline = customStyle.getUnderline();
        }
        if (rowStyle != null && rowStyle.getUnderline() != null) {
            underline = rowStyle.getUnderline();
        }
        if (colStyle != null && colStyle.getUnderline() != null) {
            underline = colStyle.getUnderline();
        }
        if (underline != null && underline) {
            font.setUnderline((byte) 1);
        }
        String forecolor = cellStyle.getForecolor();
        if (customStyle != null && customStyle.getForecolor() != null) {
            forecolor = customStyle.getForecolor();
        }
        if (rowStyle != null && rowStyle.getForecolor() != null) {
            forecolor = rowStyle.getForecolor();
        }
        if (colStyle != null && colStyle.getForecolor() != null) {
            forecolor = colStyle.getForecolor();
        }
        if (StringUtils.isEmpty(forecolor)) {
            forecolor = "0,0,0";
        }
        HSSFColor fontColor = buildHSSFColor(wb, forecolor);
        font.setColor(fontColor.getIndex());
        String fontFamily = cellStyle.getFontFamily();
        if (customStyle != null && customStyle.getFontFamily() != null) {
            fontFamily = customStyle.getFontFamily();
        }
        if (rowStyle != null && rowStyle.getFontFamily() != null) {
            fontFamily = rowStyle.getFontFamily();
        }
        if (colStyle != null && colStyle.getFontFamily() != null) {
            fontFamily = colStyle.getFontFamily();
        }
        if (fontFamily != null) {
            font.setFontName(fontFamily);
        }
        style.setFont(font);
        return style;
    }

    private BorderStyle getBorderStyle(Border border) {
        if (border.getStyle().equals(com.begcode.ureport.core.definition.BorderStyle.solid)) {
            return BorderStyle.THIN;
        } else if (border.getStyle().equals(com.begcode.ureport.core.definition.BorderStyle.dashed)) {
            return BorderStyle.DASHED;
        } else if (border.getStyle().equals(com.begcode.ureport.core.definition.BorderStyle.doublesolid)) {
            return BorderStyle.DOUBLE;
        }
        return null;
    }

    private HSSFColor buildHSSFColor(HSSFWorkbook wb, String colorStr) {
        String[] color = colorStr.split(",");
        HSSFPalette palette = wb.getCustomPalette();
        byte r = BigInteger.valueOf(Integer.parseInt(color[0])).byteValue();
        byte g = BigInteger.valueOf(Integer.parseInt(color[1])).byteValue();
        byte b = BigInteger.valueOf(Integer.parseInt(color[2])).byteValue();
        HSSFColor targetColor = palette.findColor(r, g, b);
        if (targetColor == null) {
            palette.setColorAtIndex(HSSFColorPredefined.LAVENDER.getIndex(), r, g, b);
            targetColor = palette.getColor(HSSFColorPredefined.LAVENDER.getIndex());
        }
        return targetColor;
    }

    private String buildStyleId(Cell cell) {
        CellStyle customStyle = cell.getCustomCellStyle();
        CellStyle rowStyle = cell.getRow().getCustomCellStyle();
        CellStyle colStyle = cell.getColumn().getCustomCellStyle();
        StringBuilder sb = new StringBuilder();
        String bgcolor = customStyle.getBgcolor();
        if (rowStyle != null && StringUtils.isNotBlank(rowStyle.getBgcolor())) {
            bgcolor = rowStyle.getBgcolor();
        }
        if (colStyle != null && StringUtils.isNotBlank(colStyle.getBgcolor())) {
            bgcolor = colStyle.getBgcolor();
        }
        if (StringUtils.isNotEmpty(bgcolor)) {
            sb.append("bgcolor:").append(bgcolor);
        }
        Border topBorder = customStyle.getTopBorder();
        if (topBorder != null) {
            sb.append("topborder:").append(buildBorderStyleId(topBorder));
        }
        Border bottomBorder = customStyle.getBottomBorder();
        if (bottomBorder != null) {
            sb.append("bottomborder:").append(buildBorderStyleId(bottomBorder));
        }
        Border leftBorder = customStyle.getLeftBorder();
        if (leftBorder != null) {
            sb.append("leftborder:").append(buildBorderStyleId(leftBorder));
        }
        Border rightBorder = customStyle.getRightBorder();
        if (rightBorder != null) {
            sb.append("rightborder:").append(buildBorderStyleId(rightBorder));
        }
        Alignment align = customStyle.getAlign();
        if (rowStyle != null && rowStyle.getAlign() != null) {
            align = rowStyle.getAlign();
        }
        if (colStyle != null && colStyle.getAlign() != null) {
            align = colStyle.getAlign();
        }
        if (align != null) {
            if (align.equals(Alignment.left)) {
                sb.append("align:").append(HorizontalAlignment.LEFT.name());
            } else if (align.equals(Alignment.center)) {
                sb.append("align:").append(HorizontalAlignment.CENTER.name());
            } else if (align.equals(Alignment.right)) {
                sb.append("align:").append(HorizontalAlignment.RIGHT.name());
            }
        }
        Alignment valign = customStyle.getValign();
        if (rowStyle != null && rowStyle.getValign() != null) {
            valign = rowStyle.getValign();
        }
        if (colStyle != null && colStyle.getValign() != null) {
            valign = colStyle.getValign();
        }
        if (valign != null) {
            if (valign.equals(Alignment.top)) {
                sb.append("valign:").append(VerticalAlignment.TOP.name());
            } else if (valign.equals(Alignment.middle)) {
                sb.append("valign:").append(VerticalAlignment.CENTER.name());
            } else if (valign.equals(Alignment.bottom)) {
                sb.append("valign:").append(VerticalAlignment.BOTTOM.name());
            }
        }
        Boolean underline = customStyle.getUnderline();
        if (rowStyle != null && rowStyle.getUnderline() != null) {
            underline = rowStyle.getUnderline();
        }
        if (colStyle != null && colStyle.getUnderline() != null) {
            underline = colStyle.getUnderline();
        }
        sb.append("underline:").append(underline);
        Boolean bold = customStyle.getBold();
        if (rowStyle != null && rowStyle.getBold() != null) {
            bold = rowStyle.getBold();
        }
        if (colStyle != null && colStyle.getBold() != null) {
            bold = colStyle.getBold();
        }
        sb.append("bold:").append(bold);
        Boolean italic = customStyle.getItalic();
        if (rowStyle != null && rowStyle.getItalic() != null) {
            italic = rowStyle.getItalic();
        }
        if (colStyle != null && colStyle.getItalic() != null) {
            italic = colStyle.getItalic();
        }
        sb.append("italic:").append(italic);
        String forecolor = customStyle.getForecolor();
        if (rowStyle != null && rowStyle.getForecolor() != null) {
            forecolor = rowStyle.getForecolor();
        }
        if (colStyle != null && colStyle.getForecolor() != null) {
            forecolor = colStyle.getForecolor();
        }
        sb.append("forecolor:").append(forecolor);
        String font = customStyle.getFontFamily();
        if (rowStyle != null && rowStyle.getFontFamily() != null) {
            font = rowStyle.getFontFamily();
        }
        if (colStyle != null && colStyle.getFontFamily() != null) {
            font = colStyle.getFontFamily();
        }
        sb.append("font:").append(font);
        int fontSize = customStyle.getFontSize();
        if (rowStyle != null && rowStyle.getFontSize() > 0) {
            fontSize = rowStyle.getFontSize();
        }
        if (colStyle != null && colStyle.getFontSize() > 0) {
            fontSize = colStyle.getFontSize();
        }
        sb.append("font-size:").append(fontSize);
        return sb.toString();
    }

    private String buildBorderStyleId(Border border) {
        StringBuilder sb = new StringBuilder();
        if (border.getStyle() != null) {
            sb.append("border-style:").append(border.getStyle().name());
        }
        sb.append("border-color:").append(border.getColor());
        sb.append("border-width:").append(border.getWidth());
        return sb.toString();
    }
}
