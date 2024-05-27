
package com.begcode.report.core.export.pdf.font;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.utils.SpringContextUtils;

/**
 * @author Jacky.gao
 * @since 2014年4月22日
 */
public class FontBuilder  {
    private static final Map<String, BaseFont> fontMap = new HashMap<String, BaseFont>();
    public static final Map<String, String> fontPathMap = new HashMap<String, String>();
    private static List<String> systemFontNameList = new ArrayList<String>();

    public static Font getFont(String fontName, int fontSize, boolean fontBold, boolean fontItalic, boolean underLine) {
        BaseFont baseFont = fontMap.get(fontName);
        Font font = null;
        if (baseFont != null) {
            font = new Font(baseFont);
        } else {
            font = FontFactory.getFont(fontName);
        }
        font.setSize(fontSize);
        int fontStyle = Font.NORMAL;
        if (fontBold && fontItalic && underLine) {
            fontStyle = Font.BOLD | Font.ITALIC | Font.UNDERLINE;
        } else if (fontBold) {
            if (fontItalic) {
                fontStyle = Font.BOLD | Font.ITALIC;
            } else if (underLine) {
                fontStyle = Font.BOLD | Font.UNDERLINE;
            } else {
                fontStyle = Font.BOLD;
            }
        } else if (fontItalic) {
            if (underLine) {
                fontStyle = Font.ITALIC | Font.UNDERLINE;
            } else if (fontBold) {
                fontStyle = Font.ITALIC | Font.BOLD;
            } else {
                fontStyle = Font.ITALIC;
            }
        } else if (underLine) {
            fontStyle = Font.UNDERLINE;
        }
        font.setStyle(fontStyle);
        return font;
    }

    public static java.awt.Font getAwtFont(String fontName, int fontStyle, float size) {
        if (systemFontNameList.contains(fontName)) {
            return new java.awt.Font(fontName, fontStyle, new Float(size).intValue());
        }
        String fontPath = fontPathMap.get(fontName);
        if (fontPath == null) {
            fontName = "宋体";
            fontPath = fontPathMap.get(fontName);
            if (fontPath == null) {
                return null;
            }
        }
        InputStream inputStream = null;
        try {
            inputStream = SpringContextUtils.getResourceStream(fontPath);
            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(fontStyle, size);
        } catch (Exception e) {
            throw new ReportException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    static {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = environment.getAvailableFontFamilyNames();
        for (String name : fontNames) {
            systemFontNameList.add(name);
        }
        Collection<FontRegister> fontRegisters = SpringContextUtils.getAllBeansOfType(FontRegister.class);
        for (FontRegister fontReg : fontRegisters) {
            String fontName = fontReg.getFontName();
            String fontPath = fontReg.getFontPath();
            if (StringUtils.isEmpty(fontPath) || StringUtils.isEmpty(fontName)) {
                continue;
            }
            try {
                BaseFont baseFont = getIdentityFont(fontName, fontPath, SpringContextUtils.getApplicationContext());
                if (baseFont == null) {
                    throw new ReportComputeException("Font " + fontPath + " does not exist");
                }
                fontMap.put(fontName, baseFont);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ReportComputeException(e);
            }
        }
    }

    private static BaseFont getIdentityFont(String fontFamily, String fontPath, ApplicationContext applicationContext) throws DocumentException, IOException {
        if (!fontPath.startsWith(ApplicationContext.CLASSPATH_URL_PREFIX)) {
            fontPath = ApplicationContext.CLASSPATH_URL_PREFIX + fontPath;
        }
        String fontName = fontPath;
        int lastSlashPos = fontPath.lastIndexOf("/");
        if (lastSlashPos != -1) {
            fontName = fontPath.substring(lastSlashPos + 1, fontPath.length());
        }
        if (fontName.toLowerCase().endsWith(".ttc")) {
            fontName = fontName + ",0";
        }
        InputStream inputStream = null;
        try {
            fontPathMap.put(fontFamily, fontPath);
            inputStream = applicationContext.getResource(fontPath).getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            BaseFont baseFont = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, bytes, null);
            baseFont.setSubset(true);
            return baseFont;
        } finally {
            if (inputStream != null) inputStream.close();
        }
    }
}
