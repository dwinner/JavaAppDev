package recipes;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Наблюдение за изменениями в директории.
 *
 * @author Denis
 */
public class MonitorFolder
{
   public static void main(String[] args)
   {
      MonitorFolder example = new MonitorFolder();
      example.start();
   }

   private void start()
   {
      try
      {
         System.out.println("Watch Event, press q<Enter> to exit");
         FileSystem fileSystem = FileSystems.getDefault();
         WatchService service = fileSystem.newWatchService();
         Path path = fileSystem.getPath(".");
         System.out.println("Watching: " + path.toAbsolutePath());
         path.register(service, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
         boolean shouldContinue = true;
         while (shouldContinue)
         {
            WatchKey key = service.poll(250, TimeUnit.MILLISECONDS);

            // Для выхода из программы
            while (System.in.available() > 0)
            {
               int readChar = System.in.read();
               if (readChar == 'q' || readChar == 'Q')
               {
                  shouldContinue = false;
                  break;
               }
            }
            if (key == null)  // Событий не было
            {
               continue;
            }
            for (WatchEvent<?> event : key.pollEvents())
            {
               if (event.kind() == OVERFLOW)
               {
                  continue;
               }
               @SuppressWarnings("unchecked")
               WatchEvent<Path> ev = (WatchEvent<Path>) event;
               Path filename = ev.context();
               System.out.println("Event detected: " + filename.toString() + " " + ev.kind());
            }
            boolean valid = key.reset();
            if (!valid) // Проверка актуальности WatchKey
            {
               break;
            }
         }
      }
      catch (IOException | InterruptedException e)
      {
         e.printStackTrace();
      }
   }

}
