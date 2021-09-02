package sharing_resources;

public class SerialNumberGenerator
{
   private static volatile int serialNumber = 0;

   public synchronized static int nextSerialNumber()
   {  // Инкремент в Java не является атомарной операцией => не является потоково-безопасной
      return serialNumber++;
   }

   private SerialNumberGenerator()
   {
   }

}
