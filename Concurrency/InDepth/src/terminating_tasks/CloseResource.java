package terminating_tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Прерывание заблокированной задачи путем закрытия нижележащих ресурсов.
 *
 * @author Denis
 */
public class CloseResource
{
   public static void main(String[] args) throws IOException,
                                                 InterruptedException
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      ServerSocket server = new ServerSocket(8080);
      try (InputStream socketInput = new Socket("localhost", 8080).getInputStream())
      {
         exec.execute(new IOBlocked(socketInput));
         exec.execute(new IOBlocked(System.in));
         TimeUnit.MILLISECONDS.sleep(100);
         System.out.println("Shutting down all threads");
         exec.shutdownNow();
         TimeUnit.SECONDS.sleep(1);
         System.out.println("Closing " + socketInput.getClass().getName());
      }  // socketInput.close() освободит заблокированный поток
      TimeUnit.SECONDS.sleep(1);
      System.out.println("Closing " + System.in.getClass().getName());
      System.in.close();   // НЕ освободит заблокированный поток, ожидая ввода
   }

}
