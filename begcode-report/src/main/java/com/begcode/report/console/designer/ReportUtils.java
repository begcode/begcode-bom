
package com.begcode.report.console.designer;

import com.begcode.report.console.exception.ReportDesignException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Jacky.gao
 * @since 2017年1月26日
 */
public class ReportUtils {
	public static String decodeFileName(String fileName){
		if(fileName==null){
			return fileName;
		}
		try {
			fileName=URLDecoder.decode(fileName, "utf-8");
			fileName=URLDecoder.decode(fileName, "utf-8");
			return fileName;
		} catch (UnsupportedEncodingException e) {
			throw new ReportDesignException(e);
		}
	}
}
