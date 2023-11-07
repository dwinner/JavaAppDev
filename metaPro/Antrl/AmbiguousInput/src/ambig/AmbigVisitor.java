// Generated from D:/Projects/metaproDev/Antrl/AmbiguousInput\Ambig.g4 by ANTLR 4.9.2
package ambig;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AmbigParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AmbigVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AmbigParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(AmbigParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link AmbigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(AmbigParser.ExprContext ctx);
}