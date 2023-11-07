package sharing_resources;

/**
 * Синхронизация по другому объекту.
 *
 * @author Denis
 */
public class SyncObject
{
   public static void main(String[] args)
   {
      final DualSync ds = new DualSync();
      new Thread()
      {
         @Override
         public void run()
         {
            ds.f();
         }

      }.start();
      ds.g();
   }

}

class DualSync
{
   private Object syncObject = new Object();

   synchronized void f()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println("f()");
         Thread.yield();
      }
   }

   void g()
   {
      synchronized (syncObject)
      {
         for (int i = 0; i < 5; i++)
         {
            System.out.println("g()");
            Thread.yield();
         }
      }
   }

}
