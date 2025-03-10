
package com.begcode.report.console.image;


import com.begcode.report.console.AbstractReportBasicController;
import com.begcode.report.core.cache.ResourceCache;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping(value = ImageController.URL)
public class ImageController extends AbstractReportBasicController {

    static final String URL = "/image";

    /**
	 * 获取图片资源
	 *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("_key");
        if (StringUtils.isNotBlank(key)) {
            byte[] bytes = (byte[]) ResourceCache.getObject(key);
            InputStream input = new ByteArrayInputStream(bytes);
            OutputStream output = resp.getOutputStream();
            resp.setContentType("image/png");
            try {
                IOUtils.copy(input, output);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        } else {
            //processImage(req, resp);
        }
    }

}
