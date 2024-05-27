
package com.begcode.report.console;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jacky.gao
 * @since 2017年3月8日
 */
public class RequestHolder {
	private static final ThreadLocal<HttpServletRequest> requestThreadLocal=new ThreadLocal<HttpServletRequest>();
	public static void setRequest(HttpServletRequest request){
		requestThreadLocal.set(request);
	}
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request;
	}
	public static void clean(){
		requestThreadLocal.remove();
	}
}
