package symtab.symboltable;

/** A marginally useful object to track predefined and global scopes. */
public class SymbolTable {
	public BaseScope PREDEFINED = new PredefinedScope();
	public GlobalScope GLOBALS = new GlobalScope(PREDEFINED);

	public SymbolTable() {
	}

	public void initTypeSystem() {
	}

	public void definePredefinedSymbol(Symbol s) {
		PREDEFINED.define(s);
	}

	public void defineGlobalSymbol(Symbol s) {
		GLOBALS.define(s);
	}
}
