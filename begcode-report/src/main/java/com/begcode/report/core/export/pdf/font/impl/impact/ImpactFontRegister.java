package com.begcode.report.core.export.pdf.font.impl.impact;


import com.begcode.report.core.export.pdf.font.FontRegister;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
@Component
public class ImpactFontRegister implements FontRegister {

	public String getFontName() {
		return "Impact";
	}

	public String getFontPath() {
		return path() + "impact/IMPACT.TTF";
	}
}
