package symtab.symboltable;

public class GlobalScope extends BaseScope {
	public GlobalScope(Scope scope) {
        super(scope);
    }

    @Override
    public String getName() {
        return "Global Scope";
    }
}