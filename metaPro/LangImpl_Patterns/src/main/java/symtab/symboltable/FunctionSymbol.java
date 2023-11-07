package symtab.symboltable;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * You can associate a node in the parse tree
 * that is responsible for defining this function symbol.
 */
public class FunctionSymbol extends SymbolWithScope implements TypedSymbol {
	protected ParserRuleContext defNode;
	protected Type retType;

	public FunctionSymbol(String name) {
		super(name);
	}

	public FunctionSymbol(String name, ParserRuleContext defNode) {
		super(name);
		this.defNode = defNode;
	}

	public void setDefNode(ParserRuleContext defNode) {
		this.defNode = defNode;
	}

	public ParserRuleContext getDefNode() {
		return defNode;
	}

	@Override
	public Type getType() {
		return retType;
	}

	@Override
	public void setType(Type type) {
		retType = type;
	}

	/**
	 * Return the number of VariableSymbols
	 * specifically defined in the scope.
	 *
	 * This is useful as either the number of parameters
	 * or the number of parameters and locals
	 * depending on how you build the scope tree.
	 */
	public int getNumberOfVariables() {
		return Utils.filter(symbols.values(), VariableSymbol.class::isInstance).size();
	}

	public int getNumberOfParameters() {
		return Utils.filter(symbols.values(), ParameterSymbol.class::isInstance).size();
	}

	@Override
	public String toString() {
		return name + " : " + super.toString();
	}
}
