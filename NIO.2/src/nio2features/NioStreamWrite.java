package nio2features;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Потоковый вывод на базе NIO.2
 *
 * @author Denis
 */
public class NioStreamWrite
{
   public static void main(String[] args)
   {
      try (OutputStream outputStream =
        new BufferedOutputStream(Files.newOutputStream(Paths.get("test2.txt"))))
      {
         for (int i = 0; i < 26; i++)
         {
            outputStream.write((byte) ('A' + i));
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}
