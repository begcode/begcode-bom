
package com.begcode.report.console.designer;

import com.begcode.report.console.AbstractReportBasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author summer
 * @Date: 2022/1/14
 * Description: 查询表单控制器
 */
@Controller
@RequestMapping(value = "/searchFormDesigner")
public class SearchFormDesignerController extends AbstractReportBasicController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String searchForm() {

		return "searchform.html";
	}

}
