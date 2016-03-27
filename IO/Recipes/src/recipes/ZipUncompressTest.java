package recipes;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Распаковка сжатых файлов.
 *
 * @author Denis
 */
public class ZipUncompressTest
{
   public static void main(String[] args)
   {
      ZipUncompressTest example = new ZipUncompressTest();
      example.start();
   }

   private void start()
   {
      ZipFile file = null;
      try
      {
         file = new ZipFile("wpf4.5.zip");
         FileSystem fileSystem = FileSystems.getDefault();
         Enumeration<? extends ZipEntry> entries = file.entries();
         String uncompressedDirectory = "uncompressed/";
         Files.createDirectory(fileSystem.getPath(uncompressedDirectory));
         while (entries.hasMoreElements())
         {
            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory())
            {
               System.out.println("Creating Directory: " + uncompressedDirectory + entry.getName());
               Files.createDirectories(fileSystem.getPath(uncompressedDirectory + entry.getName()));
            }
            else
            {
               InputStream is = file.getInputStream(entry);
               System.out.println("File: " + entry.getName());
               BufferedInputStream bis = new BufferedInputStream(is);
               String uncompressedFileName = uncompressedDirectory + entry.getName();
               Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
               Files.createFile(uncompressedFilePath);
               try (FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName))
               {
                  while (bis.available() > 0)
                  {
                     fileOutput.write(bis.read());
                  }
               }
               System.out.println("Written: " + entry.getName());
            }
         }
      }
      catch (IOException ioEx)
      {
         ioEx.printStackTrace();
      }
   }

}
