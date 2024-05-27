package com.begcode.report.core.export.pdf.font.impl.fangsong;


import com.begcode.report.core.export.pdf.font.FontRegister;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
@Component
public class FangSongFontRegister implements FontRegister {

	public String getFontName() {
		return "仿宋";
	}

	public String getFontPath() {
		return path() + "fangsong/SIMFANG.TTF";
	}
}
