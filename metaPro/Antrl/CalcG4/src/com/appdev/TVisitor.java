// Generated from D:/Projects/metaproDev/Antrl/CalcG4\T.g4 by ANTLR 4.9.2
package com.appdev;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(TParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(TParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(TParser.ExprContext ctx);
}