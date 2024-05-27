
package com.begcode.report.console.word;

import com.begcode.report.console.AbstractReportBasicController;
import com.begcode.report.console.exception.ReportDesignException;
import com.begcode.report.core.build.ReportBuilder;
import com.begcode.report.core.cache.CacheUtils;
import com.begcode.report.core.definition.ReportDefinition;
import com.begcode.report.core.exception.ReportComputeException;
import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.export.ExportConfigure;
import com.begcode.report.core.export.ExportConfigureImpl;
import com.begcode.report.core.export.ExportManager;
import com.begcode.report.core.export.word.high.WordProducer;
import com.begcode.report.core.model.Report;
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
 * @author summer
 * @Date: 2022/1/14
 * Description: word控制器
 */
@Controller
@RequestMapping(value = "/word")
public class ExportWordController extends AbstractReportBasicController {
    @Autowired
    private ReportBuilder reportBuilder;
    @Autowired
    private ExportManager exportManager;

    private WordProducer wordProducer = new WordProducer();

    /**
     * 导出word
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void buildWord(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String file = req.getParameter("_u");
        file = decode(file);
        if (StringUtils.isBlank(file)) {
            throw new ReportComputeException("Report file can not be null.");
        }
        OutputStream outputStream = resp.getOutputStream();
        try {
            String fileName = req.getParameter("_n");
            fileName = buildDownloadFileName(file, fileName, ".docx");
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            resp.setContentType("application/octet-stream;charset=ISO8859-1");
            resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            Map<String, Object> parameters = buildParameters(req);
            if (file.equals(PREVIEW_KEY)) {
                ReportDefinition reportDefinition = CacheUtils.getReportDefinition(PREVIEW_KEY);
                if (reportDefinition == null) {
                    throw new ReportDesignException("Report data has expired,can not do export word.");
                }
                Report report = reportBuilder.buildReport(reportDefinition, parameters);
                wordProducer.produce(report, outputStream);
            } else {
                ExportConfigure configure = new ExportConfigureImpl(file, parameters, outputStream);
                exportManager.exportWord(configure);
            }
        } catch (Exception ex) {
            throw new ReportException(ex);
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    public void setReportBuilder(ReportBuilder reportBuilder) {
        this.reportBuilder = reportBuilder;
    }

    public void setExportManager(ExportManager exportManager) {
        this.exportManager = exportManager;
    }

}
