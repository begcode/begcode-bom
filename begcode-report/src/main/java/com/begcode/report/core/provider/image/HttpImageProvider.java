
package com.begcode.report.core.provider.image;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.begcode.report.core.exception.ReportException;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年12月11日
 */
@Component
public class HttpImageProvider implements ImageProvider {

	@Override
	public InputStream getImage(String path) {
		try{
			URL url=new URL(path);
			URLConnection connection=url.openConnection();
			connection.connect();
			InputStream inputStream=connection.getInputStream();
			return inputStream;
		}catch(Exception ex){
			throw new ReportException(ex);
		}
	}

	@Override
	public boolean support(String path) {
		return path.startsWith("http:");
	}

}
