
package com.begcode.report.core.expression.parse.builder;

import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.expression.model.expr.BaseExpression;

/**
 * @author Jacky.gao
 * @since 2016年12月23日
 */
public interface ExpressionBuilder {
	BaseExpression build(ReportParserParser.UnitContext unitContext);
	boolean support(ReportParserParser.UnitContext unitContext);
}
