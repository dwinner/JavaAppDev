// Generated from D:/Projects/metaproDev/Antrl/SemanticPredicateExample1\Enum2.g4 by ANTLR 4.9.2
package com.appdev;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Enum2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Enum2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Enum2Parser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(Enum2Parser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link Enum2Parser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(Enum2Parser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link Enum2Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Enum2Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Enum2Parser#enumDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDecl(Enum2Parser.EnumDeclContext ctx);
}