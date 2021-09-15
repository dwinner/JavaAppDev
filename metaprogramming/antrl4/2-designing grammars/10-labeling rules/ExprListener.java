// Generated from D:\Projects\others\javaAppDev\metaprogramming\antrl4\10-Labeling rule\Expr.g4 by ANTLR 4.x
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(@NotNull ExprParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(@NotNull ExprParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterE(@NotNull ExprParser.EContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitE(@NotNull ExprParser.EContext ctx);
}