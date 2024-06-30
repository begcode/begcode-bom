package com.begcode.report.console.exception;

import com.begcode.report.console.common.R;
import com.begcode.report.core.exception.ReportException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ReportExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ReportException.class)
    public R globalException(HttpServletResponse response, ReportException ex){

        String message = ex.getMessage();
        return R.error(message);
    }

}
