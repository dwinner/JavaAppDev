import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Данная программа реализует многопоточный сервер, который
 * ожидает обращения по порту 8189 и возвращает клиенту
 * переданные им данные.
 * @author Cay Horstmann
 * @version 1.20 2004-08-03
 */
public class ThreadedEchoServer
{
    public static void main(String[] args)
    {
        try
        {
            int i = 1;
            ServerSocket s = new ServerSocket(8189);
            while (true)
            {                
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
}
