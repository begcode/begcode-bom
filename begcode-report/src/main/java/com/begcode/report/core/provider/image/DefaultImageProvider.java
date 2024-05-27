
package com.begcode.report.core.provider.image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.utils.SpringContextUtils;


/**
 * @author Jacky.gao
 * @since 2017年3月6日
 */
@Component
public class DefaultImageProvider implements ImageProvider  {
	private String baseWebPath = SpringContextUtils.getWebBasePath();
	@Override
	public InputStream getImage(String path) {
		try {
			if(path.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX) || path.startsWith("/WEB-INF")){
				return SpringContextUtils.getResourceStream(path);
			}else{
				path=baseWebPath+path;
				return new FileInputStream(path);
			}
		} catch (IOException e) {
			throw new ReportComputeException(e);
		}
	}

	@Override
	public boolean support(String path) {
		if(path.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)){
			return true;
		}else if(baseWebPath!=null && (path.startsWith("/") || path.startsWith("/WEB-INF"))){
			return true;
		}
		return false;
	}
}
