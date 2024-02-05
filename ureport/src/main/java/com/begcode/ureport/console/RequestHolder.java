package com.begcode.ureport.console;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Jacky.gao
 * @since 2017年3月8日
 */
public class RequestHolder {
    private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    public static void clean() {
        requestThreadLocal.remove();
    }
}
