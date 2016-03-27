package new_synchronizators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Использование Semaphore в Pool ограничивает количество задач, которые могут использовать ресурс
 *
 * @param <T> Тип объектов в пуле
 * @author Denis
 */
public class Pool<T>
{
   public Pool(Class<T> classObject, int aSize)
   {
      size = aSize;
      checkedOut = new boolean[aSize];
      available = new Semaphore(aSize, true);
      // Заполнение пула объектами
      for (int i = 0; i < size; i++)
      {
         try
         {
            items.add(classObject.newInstance());
         }
         catch (InstantiationException | IllegalAccessException ex)
         {
            throw new RuntimeException(ex);
         }
      }
   }

   public T checkOut() throws InterruptedException
   {
      available.acquire();
      return getItem();
   }

   public void checkIn(T x)
   {
      if (releaseItem(x))
      {
         available.release();
      }
   }

   private synchronized T getItem()
   {
      for (int i = 0; i < size; i++)
      {
         if (!checkedOut[i])
         {
            checkedOut[i] = true;
            return items.get(i);
         }
      }
      return null;   // Семафор предотвращает переход в эту точку
   }

   private synchronized boolean releaseItem(T anItem)
   {
      int index = items.indexOf(anItem);
      if (index == -1)
      {
         return false;  // Отсутствует в списке
      }
      if (checkedOut[index])
      {
         checkedOut[index] = false;
         return true;
      }
      return false;  // Не был освобожден
   }

   private int size;
   private List<T> items = new ArrayList<>();
   private volatile boolean[] checkedOut;
   private Semaphore available;
}
