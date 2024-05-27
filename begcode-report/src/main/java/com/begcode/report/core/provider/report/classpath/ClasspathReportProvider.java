
package com.begcode.report.core.provider.report.classpath;

import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.provider.report.ReportFile;
import com.begcode.report.core.provider.report.ReportProvider;
import com.begcode.report.core.utils.SpringContextUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
@Component
public class ClasspathReportProvider implements ReportProvider  {

    @Override
    public InputStream loadReport(String file) {
        Resource resource = SpringContextUtils.getResource(file);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            String newFileName = null;
            if (file.startsWith("classpath:")) {
                newFileName = "classpath*:" + file.substring(10, file.length());
            } else if (file.startsWith("classpath*:")) {
                newFileName = "classpath:" + file.substring(11, file.length());
            }
            if (newFileName != null) {
                return SpringContextUtils.getResourceStream(file);
            }
            throw new ReportException(e);
        }
    }

    @Override
    public String getPrefix() {
        return "classpath";
    }

    @Override
    public void deleteReport(String file) {
    }

    @Override
    public void saveReport(String file, String content) {
    }

    @Override
    public List<ReportFile> getReportFiles() {
        return null;
    }

    @Override
    public boolean enabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
