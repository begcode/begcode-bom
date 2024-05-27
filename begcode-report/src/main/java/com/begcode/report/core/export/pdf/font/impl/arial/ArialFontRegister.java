package com.begcode.report.core.export.pdf.font.impl.arial;


import com.begcode.report.core.export.pdf.font.FontRegister;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
@Component
public class ArialFontRegister implements FontRegister {

	public String getFontName() {
		return "Arial";
	}

	public String getFontPath() {
		return path() + "arial/ARIAL.TTF";
	}
}
