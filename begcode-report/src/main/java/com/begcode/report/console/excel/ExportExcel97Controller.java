
package com.begcode.report.console.excel;

import com.begcode.report.console.AbstractReportBasicController;
import com.begcode.report.console.exception.ReportDesignException;
import com.begcode.report.core.build.ReportBuilder;
import com.begcode.report.core.cache.CacheUtils;
import com.begcode.report.core.definition.ReportDefinition;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.export.ExportConfigure;
import com.begcode.report.core.export.ExportConfigureImpl;
import com.begcode.report.core.export.ExportManager;
import com.begcode.report.core.export.excel.low.Excel97Producer;
import com.begcode.report.core.model.Report;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年7月3日
 */
@Controller
@RequestMapping(value = "/excel97")
public class ExportExcel97Controller extends AbstractReportBasicController {
    @Autowired
    private ReportBuilder reportBuilder;

    @Autowired
    private ExportManager exportManager;
    private Excel97Producer excelProducer = new Excel97Producer();

    /**
     * 分页到处excel
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        buildExcel(req, resp, true, false);
    }

    /**
     * 分sheet到处excel
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = {"/sheet", ""}, method = RequestMethod.GET)
    public void sheet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        buildExcel(req, resp, false, true);
    }

    public void buildExcel(HttpServletRequest req, HttpServletResponse resp, boolean withPage, boolean withSheet) throws IOException {
        String file = req.getParameter("_u");
        if (StringUtils.isBlank(file)) {
            throw new ReportComputeException("Report file can not be null.");
        }
        String fileName = req.getParameter("_n");
        if (StringUtils.isNotBlank(fileName)) {
            fileName = decode(fileName);
        } else {
            fileName = "ureport.xls";
        }
        resp.setContentType("application/octet-stream;charset=ISO8859-1");
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        Map<String, Object> parameters = buildParameters(req);
        OutputStream outputStream = resp.getOutputStream();
        if (file.equals(PREVIEW_KEY)) {
            ReportDefinition reportDefinition = CacheUtils.getReportDefinition(PREVIEW_KEY);
            if (reportDefinition == null) {
                throw new ReportDesignException("Report data has expired,can not do export excel.");
            }
            Report report = reportBuilder.buildReport(reportDefinition, parameters);
            if (withPage) {
                excelProducer.produceWithPaging(report, outputStream);
            } else if (withSheet) {
                excelProducer.produceWithSheet(report, outputStream);
            } else {
                excelProducer.produce(report, outputStream);
            }
        } else {
            ExportConfigure configure = new ExportConfigureImpl(file, parameters, outputStream);
            if (withPage) {
                exportManager.exportExcelWithPaging(configure);
            } else if (withSheet) {
                exportManager.exportExcelWithPagingSheet(configure);
            } else {
                exportManager.exportExcel(configure);
            }
        }
        outputStream.flush();
        outputStream.close();
    }

    public void setReportBuilder(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    public void setExportManager(ExportManager exportManager) {
        this.exportManager = exportManager;
    }

}
