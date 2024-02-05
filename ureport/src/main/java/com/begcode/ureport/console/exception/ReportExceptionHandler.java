package com.begcode.ureport.console.exception;

import com.begcode.ureport.console.common.R;
import com.begcode.ureport.core.exception.ReportException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: summer
 * @Date: 2022/2/11 22:20
 * @Description:
 **/
@ControllerAdvice
public class ReportExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ReportException.class)
    public R globalException(HttpServletResponse response, ReportException ex) {

        String message = ex.getMessage();
        return R.error(message);
    }

}
