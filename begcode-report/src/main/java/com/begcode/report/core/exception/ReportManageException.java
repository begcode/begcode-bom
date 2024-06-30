package com.begcode.report.core.exception;

public class ReportManageException extends RuntimeException {
    private static final long serialVersionUID = 2970559370876740683L;
    public ReportManageException(String msg) {
        super(msg);
    }
    public ReportManageException(Exception ex) {
        super(ex);
        ex.printStackTrace();
    }
}
