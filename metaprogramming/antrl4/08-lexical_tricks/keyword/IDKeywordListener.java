// Generated from D:\Projects\others\javaAppDev\metaprogramming\antrl4\8-lexical_tricks\IDKeyword.g4 by ANTLR 4.x
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IDKeywordParser}.
 */
public interface IDKeywordListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IDKeywordParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull IDKeywordParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IDKeywordParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull IDKeywordParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IDKeywordParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull IDKeywordParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link IDKeywordParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull IDKeywordParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link IDKeywordParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull IDKeywordParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IDKeywordParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull IDKeywordParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IDKeywordParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull IDKeywordParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IDKeywordParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull IDKeywordParser.IdContext ctx);
}