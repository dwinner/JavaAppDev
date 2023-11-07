package new_synchronizators;

/**
 * Объекты, создание которых занимает много времени.
 *
 * @author Denis
 */
public class Fat
{
   public Fat()
   {
      // Затратная прерываемая операция
      for (int i = 1; i < 100000; i++)
      {
         d += (Math.PI + Math.E) / (double) i;
      }
   }

   public void operation()
   {
      System.out.print(this);
   }

   @Override
   public String toString()
   {
      return "Fat {" + "d = " + d + ", id = " + id + '}';
   }

   private volatile double d; // Предотвращает оптимизацию
   private static int counter = 0;
   private final int id = counter++;
}
