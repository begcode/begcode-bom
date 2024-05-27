
package com.begcode.report.core.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.image.ChartImageProcessor;
import com.begcode.report.core.image.ImageProcessor;
import com.begcode.report.core.image.ImageType;
import com.begcode.report.core.image.StaticImageProcessor;
import org.apache.poi.util.IOUtils;
import org.springframework.util.Base64Utils;


/**
 * @author Jacky.gao
 * @since 2017年3月20日
 */
public class ImageUtils {
	private static Map<ImageType, ImageProcessor<?>> imageProcessorMap=new HashMap<ImageType,ImageProcessor<?>>();
	static{
		StaticImageProcessor staticImageProcessor=new StaticImageProcessor();
		imageProcessorMap.put(ImageType.image, staticImageProcessor);
		ChartImageProcessor chartImageProcessor=new ChartImageProcessor();
		imageProcessorMap.put(ImageType.chart, chartImageProcessor);
	}

	public static InputStream base64DataToInputStream(String base64Data){
		byte[] bytes=Base64Utils.decodeFromString(base64Data);
		ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
		return inputStream;
	}

	@SuppressWarnings("unchecked")
	public static String getImageBase64Data(ImageType type,Object data,int width,int height){
		ImageProcessor<Object> targetProcessor=(ImageProcessor<Object>)imageProcessorMap.get(type);
		if(targetProcessor==null){
			throw new ReportComputeException("Unknow image type :"+type);
		}
		InputStream inputStream = targetProcessor.getImage(data);
		try{
			if(width>0 && height>0){
				BufferedImage inputImage=ImageIO.read(inputStream);
				BufferedImage outputImage = new BufferedImage(width,height,BufferedImage.TYPE_USHORT_565_RGB);
				Graphics2D g = outputImage.createGraphics();
		        g.drawImage(inputImage, 0, 0, width,height, null);
		        g.dispose();
		        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		        ImageIO.write(outputImage, "png", outputStream);
		        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			}
			byte[] bytes= IOUtils.toByteArray(inputStream);
			return Base64Utils.encodeToString(bytes);
		}catch(Exception ex){
			throw new ReportComputeException(ex);
		}finally{
			IOUtils.closeQuietly(inputStream);
		}
	}
}
