
package com.begcode.report.core.expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ScriptErrorListener extends BaseErrorListener {
	private List<ErrorInfo> infos= new ArrayList<>();
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,Object offendingSymbol, int line, int charPositionInLine,String msg, RecognitionException e) {
		infos.add(new ErrorInfo(line,charPositionInLine,msg));
	}
	public List<ErrorInfo> getInfos() {
		return infos;
	}
}
