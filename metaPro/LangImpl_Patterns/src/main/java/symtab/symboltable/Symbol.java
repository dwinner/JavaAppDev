package symtab.symboltable;

/**
 * A symbol has a name and a scope.
 */
public interface Symbol
{
   String getName();

   Scope getScope();

   void setScope(Scope scope);

   int hashCode();

   boolean equals(Object o);

   public Type getType();

}
