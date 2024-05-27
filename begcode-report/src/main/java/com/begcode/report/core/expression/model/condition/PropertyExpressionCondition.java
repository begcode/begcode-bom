
package com.begcode.report.core.expression.model.condition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.begcode.report.core.Utils;
import com.begcode.report.core.build.Context;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.data.ExpressionData;
import com.begcode.report.core.model.Cell;
import org.apache.commons.lang3.StringUtils;
/**
 * @author Jacky.gao
 * @since 2016年11月22日
 */
public class PropertyExpressionCondition extends BaseCondition {
	private ConditionType type=ConditionType.property;
	@JsonIgnore
	private String leftProperty;
	@JsonIgnore
	private Expression rightExpression;
	@Override
	Object computeLeft(Cell cell, Cell currentCell, Object obj, Context context) {
		if(StringUtils.isNotBlank(leftProperty)){
			return Utils.getProperty(obj, leftProperty);
		}else{
			return cell.getData();
		}
	}

	@Override
	Object computeRight(Cell cell,Cell currentCell,Object obj,Context context) {
		ExpressionData<?> exprData=rightExpression.execute(cell, currentCell,context);
		return extractExpressionData(exprData);
	}

	@Override
	public ConditionType getType() {
		return type;
	}

	public void setLeftProperty(String leftProperty) {
		this.leftProperty = leftProperty;
	}
	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}
	public String getLeftProperty() {
		return leftProperty;
	}
	public Expression getRightExpression() {
		return rightExpression;
	}
}
