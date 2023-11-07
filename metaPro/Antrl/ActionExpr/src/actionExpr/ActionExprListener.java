// Generated from D:/Projects/metaproDev/Antrl/ActionExpr\ActionExpr.g4 by ANTLR 4.9.2
package actionExpr;

import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ActionExprParser}.
 */
public interface ActionExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ActionExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ActionExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ActionExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ActionExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ActionExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(ActionExprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ActionExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(ActionExprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ActionExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ActionExprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ActionExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ActionExprParser.ExprContext ctx);
}