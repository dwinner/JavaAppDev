// Generated from D:/Projects/metaproDev/Antrl/SemanticPredicateExample2\Data.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DataParser}.
 */
public interface DataListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DataParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(DataParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(DataParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(DataParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(DataParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link DataParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequence(DataParser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DataParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequence(DataParser.SequenceContext ctx);
}