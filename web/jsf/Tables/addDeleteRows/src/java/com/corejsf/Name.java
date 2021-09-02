package com.corejsf;

public class Name
{
   private String first;
   private String second;
   private boolean editable;

   public Name()
   {
   }

   public Name(String first, String second)
   {
      this.first = first;
      this.second = second;
   }

   public String getFirst()
   {
      return first;
   }

   public void setFirst(String first)
   {
      this.first = first;
   }

   public String getSecond()
   {
      return second;
   }

   public void setSecond(String second)
   {
      this.second = second;
   }

   public boolean isEditable()
   {
      return editable;
   }

   public void setEditable(boolean editable)
   {
      this.editable = editable;
   }

   @Override
   public int hashCode()
   {
      int hash = 3;
      hash = 67 * hash + (this.first != null ? this.first.hashCode() : 0);
      hash = 67 * hash + (this.second != null ? this.second.hashCode() : 0);
      hash = 67 * hash + (this.editable ? 1 : 0);
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Name other = (Name) obj;
      if ((this.first == null) ? (other.first != null) : !this.first.equals(other.first))
      {
         return false;
      }
      if ((this.second == null) ? (other.second != null) : !this.second.equals(other.second))
      {
         return false;
      }
      if (this.editable != other.editable)
      {
         return false;
      }
      return true;
   }

}
