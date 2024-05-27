
package com.begcode.report.core.provider.image;

import java.io.InputStream;

/**
 * @author Jacky.gao
 * @since 2017年3月6日
 */
public interface ImageProvider {
	InputStream getImage(String path);
	boolean support(String path);
}
