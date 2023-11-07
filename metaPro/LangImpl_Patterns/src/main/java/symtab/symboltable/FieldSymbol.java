package symtab.symboltable;

/**
 * A field symbol is just a variable
 * that lives inside a struct.
 */
public class FieldSymbol extends VariableSymbol {
	public FieldSymbol(String name) {
		super(name);
	}
}