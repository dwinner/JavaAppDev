// Generated from D:\Projects\others\javaAppDev\metaprogramming\antrl4\22-actions\Keywords.g4 by ANTLR 4.x
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KeywordsParser}.
 */
public interface KeywordsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KeywordsParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull KeywordsParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link KeywordsParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull KeywordsParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link KeywordsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull KeywordsParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link KeywordsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull KeywordsParser.ExprContext ctx);
}