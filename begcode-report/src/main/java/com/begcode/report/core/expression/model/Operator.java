
package com.begcode.report.core.expression.model;

import com.begcode.report.core.exception.ReportParseException;

/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public enum Operator {
	Add,Subtract,Multiply,Divide,Complementation;
	public static Operator parse(String op){
		if(op.equals("+")){
			return Add;
		}else if(op.equals("-")){
			return Subtract;
		}else if(op.equals("*")){
			return Multiply;
		}else if(op.equals("/")){
			return Divide;
		}else if(op.equals("%")){
			return Complementation;
		}else{
			throw new ReportParseException("Unknow operator :"+op);
		}
	}
	@Override
	public String toString() {
		switch(this){
		case Add:
			return "+";
		case Divide:
			return "/";
		case Multiply:
			return "*";
		case Subtract:
			return "-";
		case Complementation:
			return "%";
		}
		throw new IllegalArgumentException("Unknow operator: ["+this+"]");
	};
}
