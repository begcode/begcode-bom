
package com.begcode.report.core.image;

import java.io.InputStream;

/**
 * @author Jacky.gao
 * @since 2017年3月20日
 */
public interface ImageProcessor<T> {
	InputStream getImage(T data);
}
