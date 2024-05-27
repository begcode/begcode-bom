
package com.begcode.report.core.provider.image;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.begcode.report.core.exception.ReportException;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2017年12月22日
 */
@Component
public class HttpsImageProvider implements ImageProvider {

	@Override
	public InputStream getImage(String path) {
		try{
			URL url=new URL(path);
			HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();
			connection.connect();
			InputStream inputStream=connection.getInputStream();
			return inputStream;
		}catch(Exception ex){
			throw new ReportException(ex);
		}
	}

	@Override
	public boolean support(String path) {
		return path.startsWith("https:");
	}

}
