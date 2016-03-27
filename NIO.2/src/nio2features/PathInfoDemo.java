package nio2features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Извлечение мета-информации о пути и файле.
 *
 * @author Denis
 */
public class PathInfoDemo
{
   public static void main(String[] args)
   {
      Path filePath = Paths.get("test.txt");

      System.out.println("File name: " + filePath.getName(0));
      System.out.println("Path: " + filePath);
      System.out.println("Absolute path: " + filePath.toAbsolutePath());
      System.out.println("Parent: " + filePath.getParent());

      System.out.println(Files.exists(filePath) ? "File exists" : "File does not exist");
      try
      {
         System.out.println(Files.isHidden(filePath) ? "File is hidden" : "File is not hidden");
      }
      catch (IOException ex)
      {
         Logger.getLogger(PathInfoDemo.class.getName()).log(Level.SEVERE, null, ex);
      }

      System.out.println(Files.isWritable(filePath) ? "File is writable" : "File is not writable");
      System.out.println(Files.isReadable(filePath) ? "File is readable" : "File is not readable");
      try
      {
         BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);

         System.out.println(attributes.isDirectory() ? "File is directory" : "File is not directory");
         System.out.println(attributes.isRegularFile() ? "File is a normal file" : "File is not a normal file");
         System.out.println(attributes.isSymbolicLink() ? "File is symbolic link" : "File is not symbolic link");

         System.out.println("File last modified: " + attributes.lastModifiedTime());
         System.out.println("File size: " + attributes.size() + " bytes");
      }
      catch (IOException ex)
      {
         Logger.getLogger(PathInfoDemo.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
