package nio2features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Копирование файла через NIO.2
 *
 * @author Denis
 */
public class NioCopy
{
   public static void main(String[] args)
   {
      if (args.length != 2)
      {
         System.out.println("Usage: Copy From To");
         return;
      }


      Path source = Paths.get(args[0]);
      Path target = Paths.get(args[1]);
      try
      {
         Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
      }
      catch (IOException ex)
      {
         Logger.getLogger(NioCopy.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

}
