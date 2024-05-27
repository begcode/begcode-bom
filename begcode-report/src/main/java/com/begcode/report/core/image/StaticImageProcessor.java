
package com.begcode.report.core.image;


import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Logger;

import com.begcode.report.core.Utils;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.provider.image.ImageProvider;
import com.begcode.report.core.utils.SpringContextUtils;

/**
 * @author Jacky.gao
 * @since 2017年3月20日
 */
public class StaticImageProcessor implements ImageProcessor<String> {
	private Logger log= Logger.getGlobal();
	@Override
	public InputStream getImage(String path) {
		Collection<ImageProvider> imageProviders= Utils.getImageProviders();
		ImageProvider targetImageProvider=null;
		for(ImageProvider provider:imageProviders){
			if(provider.support(path)){
				targetImageProvider=provider;
				break;
			}
		}
		if(targetImageProvider==null){
			throw new ReportComputeException("Unsupport image path :"+path);
		}
		try{
			InputStream inputStream=targetImageProvider.getImage(path);
			return inputStream;
		}catch(Exception ex){
			log.warning("Image ["+path+"] not exist,use default picture.");
			String imageNotExistPath="classpath:com/bstek/ureport/image/image-not-exist.jpg";
			return SpringContextUtils.getResourceStream(imageNotExistPath);
		}
	}
}
