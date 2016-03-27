package terminating_tasks;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Прерывание заблокированного канала NIO
 *
 * @author Denis
 */
public class NIOInterruption
{
   public static void main(String[] args) throws IOException,
                                                 InterruptedException
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      ServerSocket server = new ServerSocket(8080);
      InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
      SocketChannel sc1 = SocketChannel.open(isa);
      try (SocketChannel sc2 = SocketChannel.open(isa))
      {
         Future<?> f = exec.submit(new NIOBlocked(sc1));
         exec.execute(new NIOBlocked(sc2));
         exec.shutdown();
         TimeUnit.SECONDS.sleep(1);
         f.cancel(true);   // Прерывание через отмену задачи
         TimeUnit.SECONDS.sleep(1);
      }  // Освобождение блокировки через закрытие канала
   }

}

class NIOBlocked implements Runnable
{
   private final SocketChannel sc;
   private final String className = getClass().getName();

   NIOBlocked(SocketChannel socketChannel)
   {
      sc = socketChannel;
   }

   @Override
   public void run()
   {
      try
      {
         System.out.println("Waiting for read() in " + this);
         sc.read(ByteBuffer.allocate(1));
      }
      catch (ClosedByInterruptException e)
      {
         System.out.println(className + ": ClosedByInterruptException");
      }
      catch (AsynchronousCloseException e)
      {
         System.out.println(className + ": AsynchronousCloseException");
      }
      catch (IOException ioEx)
      {
         throw new RuntimeException(ioEx);
      }
      System.out.println("Exiting " + className + " run() " + this);
   }

}
