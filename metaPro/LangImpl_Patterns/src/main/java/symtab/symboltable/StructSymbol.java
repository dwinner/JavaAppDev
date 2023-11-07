package symtab.symboltable;

public class StructSymbol extends SymbolWithScope
{
   public StructSymbol(String name)
   {
      super(name);
   }

   public Symbol resolveMember(String text)
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public Type getType()
   {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
