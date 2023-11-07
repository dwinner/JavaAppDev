package symtab.symboltable;

import java.util.Collections;
import java.util.List;

/**
 * An abstract class for symbols like structures and functions that are both symbols and scopes.
 */
public abstract class SymbolWithScope extends BaseScope implements Symbol
{
   protected final String name; // Each symbol has a name

   public SymbolWithScope(String name)
   {
      this.name = name;
   }

   @Override public String getName()
   {
      return name;
   }

   @Override public Scope getScope()
   {
      return enclosingScope;
   }

   @Override public void setScope(Scope scope)
   {
      setEnclosingScope(scope);
   }

   @Override public Scope getEnclosingScope()
   {
      return enclosingScope;
   }

   public String getQualifiedName()
   {
      return enclosingScope.getName() + "." + name;
   }

   public String getQualifiedName(String scopePathSeparator)
   {
      return enclosingScope.getName()
        + scopePathSeparator
        + name;
   }

   public String getFullyQualifiedName(String scopePathSeparator)
   {
      List<Scope> path = getEnclosingPathToRoot();
      Collections.reverse(path);
      return Utils.joinScopeNames(path, scopePathSeparator);
   }

   @Override
   public int getNumberOfSymbols()
   {
      return symbols.size();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Symbol))
      {
         return false;
      }

      if (obj == this)
      {
         return true;
      }

      return name.equals(((Symbol) obj).getName());
   }

   @Override
   public int hashCode()
   {
      return name.hashCode();
   }

}
