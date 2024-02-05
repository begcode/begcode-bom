/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.begcode.ureport.console.word;

import com.begcode.ureport.console.AbstractReportBasicController;
import com.begcode.ureport.console.exception.ReportDesignException;
import com.begcode.ureport.core.build.ReportBuilder;
import com.begcode.ureport.core.cache.CacheUtils;
import com.begcode.ureport.core.definition.ReportDefinition;
import com.begcode.ureport.core.exception.ReportComputeException;
import com.begcode.ureport.core.exception.ReportException;
import com.begcode.ureport.core.export.ExportConfigure;
import com.begcode.ureport.core.export.ExportConfigureImpl;
import com.begcode.ureport.core.export.ExportManager;
import com.begcode.ureport.core.export.word.high.WordProducer;
import com.begcode.ureport.core.model.Report;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
