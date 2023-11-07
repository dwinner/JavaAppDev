package symtab.symboltable;

public class VariableSymbol extends BaseSymbol implements TypedSymbol
{
   public VariableSymbol(String name)
   {
      super(name);
   }

   public VariableSymbol(String name, Scope scope)
   {
      super(name, scope);
   }

}
