package com.begcode.report.core.exception;

public class ReportStartupException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReportStartupException(String msg) {
        super(msg);
    }

    public ReportStartupException(Exception ex) {
        super(ex);
        ex.printStackTrace();
    }
}
