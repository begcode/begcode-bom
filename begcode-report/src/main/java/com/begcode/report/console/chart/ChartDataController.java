package com.begcode.report.console.chart;

import com.begcode.report.console.AbstractReportBasicController;
import com.begcode.report.core.cache.CacheUtils;
import com.begcode.report.core.chart.ChartData;
import com.begcode.report.core.utils.UnitUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "/chart")
public class ChartDataController extends AbstractReportBasicController {

    @RequestMapping(value = "/storeData", method = RequestMethod.POST)
    @ResponseBody
    public void storeData(HttpServletRequest req) throws ServletException, IOException {
        String file = req.getParameter("_u");
        file = decode(file);
        String chartId = req.getParameter("_chartId");
        ChartData chartData = CacheUtils.getChartData(chartId);
        if (chartData == null) {
            return;
        }
        String base64Data = req.getParameter("_base64Data");
        String prefix = "data:image/png;base64,";
        if (base64Data != null) {
            if (base64Data.startsWith(prefix)) {
                base64Data = base64Data.substring(prefix.length(), base64Data.length());
            }
        }
        chartData.setBase64Data(base64Data);
        String width = req.getParameter("_width");
        String height = req.getParameter("_height");
        chartData.setHeight(UnitUtils.pixelToPoint(Integer.parseInt(height)));
        chartData.setWidth(UnitUtils.pixelToPoint(Integer.parseInt(width)));
    }

}
