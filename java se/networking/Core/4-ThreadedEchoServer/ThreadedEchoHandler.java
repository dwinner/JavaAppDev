import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Этот класс обрабатывает данные введенные клиентом
 * в рамках одного соединения.
 */
public class ThreadedEchoHandler implements Runnable
{
    private Socket incoming;
    
    /**
     * Конструктор обработчика.
     * @param i Сокет для соединения.
     */
    public ThreadedEchoHandler(Socket i)
    {
        incoming = i;
    }

    @Override
    public void run()
    {
        try
        {
            try
            {
                InputStream inStream = incoming.getInputStream();
                OutputStream outputStream = incoming.getOutputStream();
                
                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outputStream, true
                    /* автоматическая передача оставшихся данных */);
                
                out.println("Hello! Enter BYE to exit.");
                
                // Передача клиенту полученных от него данных
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
