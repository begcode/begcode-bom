
package com.begcode.report.core.expression.parse;

import java.util.ArrayList;
import java.util.List;

import com.begcode.report.core.dsl.ReportParserBaseVisitor;
import com.begcode.report.core.dsl.ReportParserParser;
import com.begcode.report.core.dsl.ReportParserParser.*;
import com.begcode.report.core.exception.ReportParseException;
import com.begcode.report.core.expression.model.Expression;
import com.begcode.report.core.expression.model.Op;
import com.begcode.report.core.expression.model.Operator;
import com.begcode.report.core.expression.model.condition.Join;
import com.begcode.report.core.expression.model.expr.*;
import com.begcode.report.core.expression.model.expr.ifelse.*;
import com.begcode.report.core.expression.parse.builder.ExpressionBuilder;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author Jacky.gao
 * @since 2016年11月18日
 */
public class ExpressionVisitor extends ReportParserBaseVisitor<Expression> {
	private List<ExpressionBuilder> expressionBuilders;
	public ExpressionVisitor(List<ExpressionBuilder> expressionBuilders) {
		this.expressionBuilders=expressionBuilders;
	}

	@Override
	public Expression visitEntry(ReportParserParser.EntryContext ctx) {
		StringBuilder sb=new StringBuilder();
		List<ReportParserParser.ExpressionContext> exprs=ctx.expression();
		List<Expression> list=new ArrayList<Expression>();
		for(ReportParserParser.ExpressionContext exprContext:exprs){
			sb.append(exprContext.getText());
			Expression expr=visitExpression(exprContext);
			list.add(expr);
		}
		ExpressionBlock block=new ExpressionBlock();
		block.setExpressionList(list);
		block.setExpr(sb.toString());
		return block;
	}

	@Override
	public Expression visitExpression(ReportParserParser.ExpressionContext ctx) {
		ReportParserParser.ExprCompositeContext exprCompositeContext=ctx.exprComposite();
		ReportParserParser.IfExprContext ifExprContext=ctx.ifExpr();
		ReportParserParser.CaseExprContext caseExprContext=ctx.caseExpr();
		ReportParserParser.VariableAssignContext assignCtx=ctx.variableAssign();
		ReportParserParser.ReturnExprContext returnCtx=ctx.returnExpr();
		if(exprCompositeContext!=null){
			return parseExprComposite(exprCompositeContext);
		}else if(ifExprContext!=null){
			IfExpression expr = parseIfExprContext(ifExprContext);
			return expr;
		}else if(caseExprContext!=null){
			IfExpression expr = parseCaseExprContext(caseExprContext);
			return expr;
		}else if(assignCtx!=null){
			VariableAssignExpression expr=new VariableAssignExpression();
			expr.setExpr(assignCtx.getText());
			expr.setVariable(assignCtx.variable().Identifier().getText());
			expr.setExpression(parseItemContext(assignCtx.item()));
			return expr;
		}else if(returnCtx!=null){
			return parseExpr(returnCtx.expr());
		}else{
			throw new ReportParseException("Expression ["+ctx.getText()+"] is invalid.");
		}
	}

	private Expression parseExprComposite(ReportParserParser.ExprCompositeContext exprCompositeContext) {
		if(exprCompositeContext instanceof ReportParserParser.SingleExprCompositeContext){
			ReportParserParser.SingleExprCompositeContext singleExprCompositeContext=(ReportParserParser.SingleExprCompositeContext)exprCompositeContext;
			ReportParserParser.ExprContext exprContext=singleExprCompositeContext.expr();
			return parseExpr(exprContext);
		}else if(exprCompositeContext instanceof ReportParserParser.ParenExprCompositeContext){
			ReportParserParser.ParenExprCompositeContext parenExprCompositeContext=(ReportParserParser.ParenExprCompositeContext)exprCompositeContext;
			ReportParserParser.ExprCompositeContext childExprCompositeContext=parenExprCompositeContext.exprComposite();
			return parseExprComposite(childExprCompositeContext);
		}else if(exprCompositeContext instanceof ReportParserParser.TernaryExprCompositeContext){
			ReportParserParser.TernaryExprCompositeContext ternaryExprCompositeContext=(ReportParserParser.TernaryExprCompositeContext)exprCompositeContext;
			ReportParserParser.TernaryExprContext ternaryExprContext=ternaryExprCompositeContext.ternaryExpr();
			List<ReportParserParser.IfConditionContext> ifConditionContexts=ternaryExprContext.ifCondition();
			IfExpression expr=new IfExpression();
			expr.setConditionList(parseCondtionList(ifConditionContexts, ternaryExprContext.join()));
			ReportParserParser.BlockContext firstBlockContext=ternaryExprContext.block(0);
			expr.setExpression(parseBlock(firstBlockContext));

			ReportParserParser.BlockContext secondBlockContext=ternaryExprContext.block(1);
			ElseExpression elseExpr=new ElseExpression();
			elseExpr.setExpression(parseBlock(secondBlockContext));
			expr.setElseExpression(elseExpr);
			return expr;
		}else if(exprCompositeContext instanceof ReportParserParser.ComplexExprCompositeContext){
			ReportParserParser.ComplexExprCompositeContext complexExprCompositeContext=(ReportParserParser.ComplexExprCompositeContext)exprCompositeContext;
			ReportParserParser.ExprCompositeContext leftExprCompositeContext=complexExprCompositeContext.exprComposite(0);
			Expression leftExpression=parseExprComposite(leftExprCompositeContext);
			ReportParserParser.ExprCompositeContext rightExprCompositeContext=complexExprCompositeContext.exprComposite(1);
			Expression rightExpression=parseExprComposite(rightExprCompositeContext);
			String op=complexExprCompositeContext.Operator().getText();
			Operator operator= Operator.parse(op);
			List<BaseExpression> expressions=new ArrayList<BaseExpression>();
			expressions.add((BaseExpression)leftExpression);
			expressions.add((BaseExpression)rightExpression);
			List<Operator> operators=new ArrayList<Operator>();
			operators.add(operator);
			ParenExpression expression=new ParenExpression(operators, expressions);
			expression.setExpr(complexExprCompositeContext.getText());
			return expression;
		}else{
			throw new ReportParseException("Unknow context :"+exprCompositeContext);
		}
	}

	private ExpressionBlock parseExpressionBlock(List<ReportParserParser.ExprBlockContext> contexts){
		StringBuilder sb=new StringBuilder();
		List<Expression> expressionList=new ArrayList<Expression>();
		for(ExprBlockContext ctx:contexts){
			sb.append(ctx.getText());
			ReportParserParser.VariableAssignContext assignContext=ctx.variableAssign();
			if(assignContext!=null){
				ReportParserParser.VariableContext varCtx=assignContext.variable();
				String variableName=varCtx.Identifier().getText();
				VariableAssignExpression assignExpr=new VariableAssignExpression();
				assignExpr.setExpr(assignContext.getText());
				assignExpr.setVariable(variableName);
				ItemContext itemCtx=assignContext.item();
				BaseExpression itemExpr=parseItemContext(itemCtx);
				assignExpr.setExpression(itemExpr);
				expressionList.add(assignExpr);
			}
			IfExprContext ifCtx=ctx.ifExpr();
			if(ifCtx!=null){
				IfExpression ifExpr=parseIfExprContext(ifCtx);
				expressionList.add(ifExpr);
			}
			CaseExprContext caseCtx=ctx.caseExpr();
			if(caseCtx!=null){
				IfExpression caseExpr = parseCaseExprContext(caseCtx);
				expressionList.add(caseExpr);
			}
		}
		ExpressionBlock blockExpr=new ExpressionBlock();
		blockExpr.setExpressionList(expressionList);
		blockExpr.setExpr(sb.toString());
		return blockExpr;
	}

	private IfExpression parseIfExprContext(IfExprContext ifExprContext) {
		IfExpression expr=new IfExpression();
		expr.setExpr(ifExprContext.getText());
		IfPartContext ifPartContext=ifExprContext.ifPart();
		List<IfConditionContext> ifConditionContexts=ifPartContext.ifCondition();
		List<JoinContext> joinContexts=ifPartContext.join();
		expr.setConditionList(parseCondtionList(ifConditionContexts,joinContexts));
		ExpressionBlock blockExpr=parseBlock(ifPartContext.block());
		expr.setExpression(blockExpr);
		List<ElseIfPartContext> elseIfPartContexts=ifExprContext.elseIfPart();
		if(elseIfPartContexts!=null && elseIfPartContexts.size()>0){
			List<ElseIfExpression> elseIfExpressionList=new ArrayList<ElseIfExpression>();
			for(ElseIfPartContext elseIfContext:elseIfPartContexts){
				ifConditionContexts=elseIfContext.ifCondition();
				joinContexts=elseIfContext.join();
				ElseIfExpression elseIfExpr=new ElseIfExpression();
				elseIfExpr.setConditionList(parseCondtionList(ifConditionContexts,joinContexts));
				elseIfExpr.setExpression(parseBlock(elseIfContext.block()));
				elseIfExpressionList.add(elseIfExpr);
			}
			expr.setElseIfExpressions(elseIfExpressionList);
		}

		ElsePartContext elsePartContext=ifExprContext.elsePart();
		if(elsePartContext!=null){
			ElseExpression elseExpression=new ElseExpression();
			elseExpression.setExpression(parseBlock(elsePartContext.block()));
			expr.setElseExpression(elseExpression);
		}
		return expr;
	}

	private ExpressionBlock parseBlock(BlockContext blockCtx){
		List<ExprBlockContext> exprBlockCtxs=blockCtx.exprBlock();
		ReturnExprContext returnCtx=blockCtx.returnExpr();
		ExpressionBlock block=null;
		if(exprBlockCtxs!=null){
			block=parseExpressionBlock(exprBlockCtxs);
		}
		if(returnCtx!=null){
			if(block==null)block=new ExpressionBlock();
			block.setReturnExpression(parseExpr(returnCtx.expr()));
		}
		return block;
	}

	private IfExpression parseCaseExprContext(CaseExprContext caseExprContext) {
		IfExpression expr=new IfExpression();
		List<ElseIfExpression> elseIfExpressionList=new ArrayList<ElseIfExpression>();
		expr.setElseIfExpressions(elseIfExpressionList);
		List<CasePartContext> casePartContexts=caseExprContext.casePart();
		for(CasePartContext casePartContext:casePartContexts){
			List<IfConditionContext> ifConditionContexts=casePartContext.ifCondition();
			List<JoinContext> joinContexts=casePartContext.join();
			ElseIfExpression elseIfExpr=new ElseIfExpression();
			elseIfExpr.setConditionList(parseCondtionList(ifConditionContexts,joinContexts));
			elseIfExpr.setExpr(casePartContext.getText());
			ExpressionBlock blockExpr=parseBlock(casePartContext.block());
			elseIfExpr.setExpression(blockExpr);
			elseIfExpressionList.add(elseIfExpr);
		}
		return expr;
	}


	private Expression parseExpr(ExprContext exprContext) {
		List<BaseExpression> expressions=new ArrayList<BaseExpression>();
		List<Operator> operators=new ArrayList<Operator>();
		List<ItemContext> itemContexts = exprContext.item();
		List<TerminalNode> operatorNodes=exprContext.Operator();
		for(int i=0;i<itemContexts.size();i++){
			ItemContext itemContext=itemContexts.get(i);
			BaseExpression expr=parseItemContext(itemContext);
			expressions.add(expr);
			if(i>0){
				TerminalNode operatorNode=operatorNodes.get(i-1);
				String op=operatorNode.getText();
				operators.add(Operator.parse(op));
			}
		}
		ParenExpression expression=new ParenExpression(operators, expressions);
		expression.setExpr(exprContext.getText());
		return expression;
	}

	private ExpressionConditionList parseCondtionList(List<IfConditionContext> ifConditionContexts, List<JoinContext> joinContexts){
		List<ExpressionCondition> list=new ArrayList<ExpressionCondition>();
		List<Join> joins=new ArrayList<Join>();
		for(int i=0;i<ifConditionContexts.size();i++){
			IfConditionContext context=ifConditionContexts.get(i);
			ExprContext left=context.expr(0);
			ExprContext right=context.expr(1);
			Expression leftExpr=parseExpr(left);
			Expression rightExpr=parseExpr(right);
			Op op=Op.parse(context.OP().getText());
			ExpressionCondition condition=new ExpressionCondition(leftExpr,op,rightExpr);
			list.add(condition);
			if(i>0){
				JoinContext joinContext=joinContexts.get(i-1);
				String text=joinContext.getText();
				Join join=Join.and;
				if(text.equals("or") || text.equals("||")){
					join=Join.or;
				}
				joins.add(join);
			}
		}
		return new ExpressionConditionList(list,joins);
	}

	public BaseExpression parseItemContext(ItemContext itemContext){
		BaseExpression expression=null;
		if(itemContext instanceof SimpleJoinContext){
			SimpleJoinContext simpleJoinContext=(SimpleJoinContext)itemContext;
			expression=visitSimpleJoin(simpleJoinContext);
		}else if(itemContext instanceof ParenJoinContext){
			ParenJoinContext parenJoinContext=(ParenJoinContext)itemContext;
			expression=visitParenJoin(parenJoinContext);
		}else if(itemContext instanceof SingleParenJoinContext){
			SingleParenJoinContext singleContext=(SingleParenJoinContext)itemContext;
			ItemContext childItemContext=singleContext.item();
			expression=parseItemContext(childItemContext);
		}else{
			throw new ReportParseException("Unknow context :"+itemContext);
		}
		return expression;
	}

	@Override
	public BaseExpression visitSimpleJoin(SimpleJoinContext ctx) {
		List<BaseExpression> expressions=new ArrayList<BaseExpression>();
		List<Operator> operators=new ArrayList<Operator>();
		List<UnitContext> unitContexts=ctx.unit();
		List<TerminalNode> operatorNodes=ctx.Operator();
		for(int i=0;i<unitContexts.size();i++){
			UnitContext unitContext=unitContexts.get(i);
			BaseExpression expr=buildExpression(unitContext);
			expressions.add(expr);
			if(i>0){
				TerminalNode operatorNode=operatorNodes.get(i-1);
				String op=operatorNode.getText();
				operators.add(Operator.parse(op));
			}
		}
		if(operators.size()==0 && expressions.size()==1){
			return expressions.get(0);
		}
		JoinExpression expression=new JoinExpression(operators,expressions);
		expression.setExpr(ctx.getText());
		return expression;
	}

	@Override
	public BaseExpression visitParenJoin(ParenJoinContext ctx) {
		List<BaseExpression> expressions=new ArrayList<BaseExpression>();
		List<Operator> operators=new ArrayList<Operator>();
		List<ItemContext> itemContexts=ctx.item();
		List<TerminalNode> operatorNodes=ctx.Operator();
		for(int i=0;i<itemContexts.size();i++){
			ItemContext itemContext=itemContexts.get(i);
			BaseExpression expr=parseItemContext(itemContext);
			expressions.add(expr);
			if(i>0){
				TerminalNode operatorNode=operatorNodes.get(i-1);
				String op=operatorNode.getText();
				operators.add(Operator.parse(op));
			}
		}
		ParenExpression expression=new ParenExpression(operators, expressions);
		expression.setExpr(ctx.getText());
		return expression;
	}

	private BaseExpression buildExpression(UnitContext unitContext){
		for(ExpressionBuilder builder:expressionBuilders){
			if(builder.support(unitContext)){
				return builder.build(unitContext);
			}
		}
		throw new ReportParseException("Unknow context :"+unitContext);
	}
}
