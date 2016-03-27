package ru.inbox.dwinner.id3library;

public class MutableInteger
{
   private int value;

   public MutableInteger(int value)
   {
      this.value = value;
   }

   public void increment()
   {
      value++;
   }

   public int getValue()
   {
      return value;
   }

   public void setValue(int value)
   {
      this.value = value;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof MutableInteger))
      {
         return false;
      }
      if (super.equals(obj))
      {
         return true;
      }
      MutableInteger other = (MutableInteger) obj;
      if (value != other.value)
      {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 97 * hash + this.value;
      return hash;
   }
}
