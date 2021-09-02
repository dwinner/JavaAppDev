package nio2features;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Перечисление содержимого каталога.
 *
 * @author Denis
 */
public class DirectoryList
{
   public static void main(String[] args)
   {
      String dirName = "C:\\Windows";
      try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(dirName)))
      {
         System.out.println("Directory of " + dirName);
         for (Path path : dirStream)
         {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.print(attributes.isDirectory() ? "<DIR> " : "      ");
            StringBuilder strBuilder = new StringBuilder(path.toString().length());
            for (int i = 0; i < path.getNameCount(); i++)
            {
               strBuilder.append(path.getName(i)).append(path.getFileSystem().getSeparator());
            }
            if (!attributes.isDirectory())
            {
               strBuilder.setLength(
                 strBuilder.length() - path.getFileSystem().getSeparator().length());
            }
            System.out.println(strBuilder.toString());
         }
      }
      catch (NotDirectoryException notDirEx)
      {
         notDirEx.printStackTrace();
      }
      catch (InvalidPathException | IOException | SecurityException e)
      {
         e.printStackTrace();
      }
   }

}
