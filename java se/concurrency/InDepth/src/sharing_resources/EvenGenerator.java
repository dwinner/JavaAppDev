package sharing_resources;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 18:17 Конфликт потоков.
 */
public class EvenGenerator extends IntGenerator
{
   private int currentEvenValue = 0;

   @Override
   public int next()
   {
      ++currentEvenValue;  // Опасная точка
      Thread.yield();   // Ускорим сбой
      ++currentEvenValue;
      return currentEvenValue;
   }

   public static void main(String[] args)
   {
      EvenChecker.test(new EvenGenerator());
   }

}
