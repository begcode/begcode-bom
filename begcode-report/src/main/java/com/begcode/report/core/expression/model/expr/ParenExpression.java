
package com.begcode.report.core.expression.model.expr;

import com.begcode.report.core.expression.model.Operator;

import java.util.List;


/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public class ParenExpression extends JoinExpression {
	private static final long serialVersionUID = 142186918381087393L;

	public ParenExpression(List<Operator> operators, List<BaseExpression> expressions) {
		super(operators, expressions);
	}
}
