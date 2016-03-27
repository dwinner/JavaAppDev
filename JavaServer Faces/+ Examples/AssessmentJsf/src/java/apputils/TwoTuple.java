package apputils;

import java.io.Serializable;

/**
 * Two tuple generic class.
 *
 * @param <A> The first type
 * @param <B> The second type
 * @author Denis
 * @version demo 18-12-2012
 */
public class TwoTuple<A, B> implements Serializable
{
   public TwoTuple(A a, B b)
   {
      first = a;
      second = b;
   }

   /**
    * @return the first
    */
   public A getFirst()
   {
      return first;
   }

   /**
    * @return the second
    */
   public B getSecond()
   {
      return second;
   }

   @Override
   public String toString()
   {
      return "(" + getFirst() + ", " + getSecond() + ")";
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 29 * hash + (getFirst() != null ? getFirst().hashCode() : 0);
      hash = 29 * hash + (getSecond() != null ? getSecond().hashCode() : 0);
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
      @SuppressWarnings("unchecked")
      final TwoTuple<A, B> other = (TwoTuple<A, B>) obj;
      if (getFirst() != other.getFirst() && (getFirst() == null || !first.equals(other.first)))
      {
         return false;
      }
      if (getSecond() != other.getSecond() && (getSecond() == null || !second.equals(other.second)))
      {
         return false;
      }
      return true;
   }

   private A first;
   private B second;
   private static final long serialVersionUID = 1L;
}
