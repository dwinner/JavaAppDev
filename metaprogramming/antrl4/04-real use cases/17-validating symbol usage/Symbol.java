/**
 * A generic programming language symbol
 */
public class Symbol
{
   public enum Type
   {
      tINVALID, tVOID, tINT, tFLOAT
   }

   String name; // All symbols at least have a name
   Type type;
   Scope scope; // All symbols know what scope contains them.

   public Symbol(String name)
   {
      this.name = name;
   }

   public Symbol(String name, Type type)
   {
      this(name);
      this.type = type;
   }

   public String getName()
   {
      return name;
   }

   public String toString()
   {
      return type != Type.tINVALID ? '<' + getName() + ":" + type + '>' : getName();
   }
}
