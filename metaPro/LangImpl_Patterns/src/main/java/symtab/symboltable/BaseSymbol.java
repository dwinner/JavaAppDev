package symtab.symboltable;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Collections;
import java.util.List;

public abstract class BaseSymbol implements Symbol
{
   protected String name;
   protected Scope scope;

   protected Type type; // a symbol may have a type

   /**
    * You can associate a node in the parse tree that is responsible for defining this symbol.
    */
   protected ParserRuleContext defNode;

   public BaseSymbol(String name)
   {
      this.name = name;
   }

   public BaseSymbol(String name, ParserRuleContext defNode)
   {
      this.name = name;
      this.defNode = defNode;
   }

   public BaseSymbol(String name, Scope scope)
   {
      this.name = name;
      this.scope = scope;
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public Scope getScope()
   {
      return scope;
   }

   @Override
   public void setScope(Scope scope)
   {
      this.scope = scope;
   }

   @Override
   public Type getType()
   {
      return type;
   }

   public void setType(Type type)
   {
      this.type = type;
   }

   public void setDefNode(ParserRuleContext defNode)
   {
      this.defNode = defNode;
   }

   public ParserRuleContext getDefNode()
   {
      return defNode;
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

   @Override
   public String toString()
   {
      String s = "";
      if (scope != null)
      {
         s = scope.getName() + ".";
      }
      if (type != null)
      {
         String ts = type.toString();
         if (type instanceof SymbolWithScope)
         {
            ts = ((SymbolWithScope) type).getFullyQualifiedName(".");
         }
         return '<' + s + getName() + " : " + ts + '>';
      }
      return s + getName();
   }

   public String getFullyQualifiedName(String scopePathSeparator)
   {
      List<Scope> path = scope.getEnclosingPathToRoot();
      Collections.reverse(path);
      String qualifier = Utils.joinScopeNames(path, scopePathSeparator);
      return qualifier + scopePathSeparator + name;
   }

}
