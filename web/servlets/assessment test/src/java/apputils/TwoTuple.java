package apputils;

/**
 * Two tuple generic class.
 *
 * @param <A> The first type
 * @param <B> The second type
 * @author Denis
 * @version demo 18-12-2012
 */
public class TwoTuple<A, B>
{
   public TwoTuple(A a, B b)
   {
      first = a;
      second = b;
   }

   @Override
   public String toString()
   {
      return "(" + first + ", " + second + ")";
   }

   public final A first;
   public final B second;
}
