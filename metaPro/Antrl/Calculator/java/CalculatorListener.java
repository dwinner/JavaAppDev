// Generated from Calculator.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CalculatorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(CalculatorParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(CalculatorParser.OperandContext ctx);
}