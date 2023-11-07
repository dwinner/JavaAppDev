package symtab.symboltable;

public class LocalScope extends BaseScope {
	public LocalScope(Scope enclosingScope) {
		super(enclosingScope);
	}

	@Override
	public String getName() {
		return "Local Scope";
	}
}
