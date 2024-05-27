
package com.begcode.report.core;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2016年5月25日
 */
@Component
public class UReportPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
	public UReportPropertyPlaceholderConfigurer() {
		setIgnoreUnresolvablePlaceholders(true);
		setOrder(100);
	}
}
