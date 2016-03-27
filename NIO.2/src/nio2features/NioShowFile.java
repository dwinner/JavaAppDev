package nio2features;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Вывод содержимого файла.
 *
 * @author Denis
 */
public class NioShowFile
{
   public static void main(String[] args)
   {
      int i;
      try (InputStream fileStream = Files.newInputStream(Paths.get("test.txt")))
      {
         do
         {
            i = fileStream.read();
            if (i != -1)
            {
               System.out.print((char) i);
            }
         }
         while (i != -1);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}
