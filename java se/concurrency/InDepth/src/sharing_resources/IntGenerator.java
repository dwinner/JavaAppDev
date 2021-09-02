package sharing_resources;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 17:52 Числовой генератор.
 */
public abstract class IntGenerator
{
   private volatile boolean canceled = false;

   public abstract int next();

   public void cancel()
   {  // Для отмены
      canceled = true;
   }

   public boolean isCanceled()
   {
      return canceled;
   }

}
