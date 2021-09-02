package nio2features;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Отображение файлов по фильтру.
 *
 * @author Denis
 */
public class FilteredDirectoryList
{
   public static void main(String[] args)
   {
      String dirName = "C:\\Windows";

      // Фильтр файлов для записи.
      DirectoryStream.Filter<Path> howFilter = new DirectoryStream.Filter<Path>()
      {
         @Override
         public boolean accept(Path entry) throws IOException
         {
            if (Files.isWritable(entry))
            {
               return true;
            }
            return false;
         }

      };

      try (DirectoryStream<Path> dirStream =
        Files.newDirectoryStream(Paths.get(dirName), howFilter))
      {
         System.out.println("Directory of " + dirName);

         for (Path entry : dirStream)
         {
            BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);
            System.out.print(attribs.isDirectory() ? "<DIR> " : "    ");
            System.out.println(entry.getName(1));
         }
      }
      catch (NotDirectoryException notDirEx)
      {
         // Ignore
      }
      catch (IOException | InvalidPathException | SecurityException e)
      {
         e.printStackTrace();
      }
   }

}
