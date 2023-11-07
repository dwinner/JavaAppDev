import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Данная программа реализует простой сервер, который ожидает
 * обращения по порту 8189 и возвращает клиенту данные, полученные от него.
 * @version 1.20 2004-08-03
 * @author Cay Horstmann
 */
public class EchoServer
{
    public static void main(String[] args)
    {
        try
        {
            // Создание сокета на стороне сервера.
            ServerSocket s = new ServerSocket(8189);
            
            // Ожидание обращения клиента.
            Socket incoming = s.accept();
            try
            {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream, true
                    /* автоматическая передача оставшихся данных */);
                
                out.println("Hello! Enter BYE to exit.");
                
                // Передача клиенту полученных от него данных.
                boolean done = false;
                while (!done && in.hasNextLine())
                {                    
                    String line = in.nextLine();
                    out.println("Echo: " + line);
                    if (line.trim().equals("BYE"))
                    {
                        done = true;
                    }
                }
            }
            finally
            {
                incoming.close();
            }
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
}
