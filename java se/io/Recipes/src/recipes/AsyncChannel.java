package recipes;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Асинхронные коммуникации между сервером и клиентом.
 *
 * @author Denis
 */
public class AsyncChannel
{
   public static void main(String[] args) throws UnknownHostException
   {
      AsyncChannel example = new AsyncChannel();
      example.start();
   }

   private void start() throws UnknownHostException
   {
      hostAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 2583);

      Thread serverThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            serverStart();
         }

      });
      serverThread.start();

      Thread clientThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            clientStart();
         }

      });
      clientThread.start();
   }

   /**
    * Старт сокета клиента.
    */
   private void clientStart()
   {
      try
      {
         try (AsynchronousSocketChannel clientSocketChannel = AsynchronousSocketChannel.open())
         {
            Future<Void> connectFuture = clientSocketChannel.connect(hostAddress);
            connectFuture.get(); // Ждем, пока подключение не станет доступно
            OutputStream os = Channels.newOutputStream(clientSocketChannel);
            try (ObjectOutputStream oos = new ObjectOutputStream(os))
            {
               for (int i = 0; i < 5; i++)
               {
                  oos.writeObject("Look at me " + i);
                  Thread.sleep(1000);
               }
               oos.writeObject("EOF");
            }
         }
      }
      catch (InterruptedException | ExecutionException | IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Старт прослушивания на сокете сервера.
    */
   private void serverStart()
   {
      try
      {
         AsynchronousServerSocketChannel serverSocketChannel =
           AsynchronousServerSocketChannel.open().bind(hostAddress);
         Future<AsynchronousSocketChannel> serverFuture = serverSocketChannel.accept();
         final AsynchronousSocketChannel clientSocket = serverFuture.get();
         System.out.println("Connected!");
         if (clientSocket != null && clientSocket.isOpen())
         {
            try (InputStream connectionInputStream = Channels.newInputStream(clientSocket);
                 ObjectInputStream ois = new ObjectInputStream(connectionInputStream))
            {
               while (true)
               {
                  Object object = ois.readObject();
                  if (object.equals("EOF"))
                  {
                     clientSocket.close();
                     break;
                  }
                  System.out.println("Received: " + object);
               }
            }
         }
      }
      catch (IOException | InterruptedException | ExecutionException | ClassNotFoundException e)
      {
         e.printStackTrace();
      }
   }

   // private AsynchronousSocketChannel clientWorker;
   private InetSocketAddress hostAddress;
}
