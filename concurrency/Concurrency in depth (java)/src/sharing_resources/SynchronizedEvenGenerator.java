package sharing_resources;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 18:34 Упрощение работы с MuTex'ами с
 * использованием ключевого слова synchronized.
 */
public class SynchronizedEvenGenerator extends IntGenerator
{
   private int currentEvenValue = 0;

   @Override
   public synchronized int next()
   {
      ++currentEvenValue;
      Thread.yield();
      ++currentEvenValue;
      return currentEvenValue;
   }

   public static void main(String[] args)
   {
      EvenChecker.test(new SynchronizedEvenGenerator());
   }

}
