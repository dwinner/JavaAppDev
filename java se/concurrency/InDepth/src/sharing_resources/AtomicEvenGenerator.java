package sharing_resources;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Атомарные классы иногда испольуются в обычном коде.
 *
 * @author Denis
 */
public class AtomicEvenGenerator extends IntGenerator
{
   private AtomicInteger currentEvenValue = new AtomicInteger(0);

   @Override
   public int next()
   {
      return currentEvenValue.addAndGet(2);
   }

   public static void main(String[] args)
   {
      EvenChecker.test(new AtomicEvenGenerator());
   }

}
