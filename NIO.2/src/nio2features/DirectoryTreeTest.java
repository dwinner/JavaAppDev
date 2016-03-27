package nio2features;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Дерево обхода каталога.
 *
 * @author Denis
 */
public class DirectoryTreeTest
{
   public static void main(String[] args)
   {
      String dirName = "D:/ProgramLibrary";
      System.out.println("Directory tree starting with " + dirName + ":\n");
      try
      {
         Files.walkFileTree(Paths.get(dirName), new MyFileVisitor());
      }
      catch (IOException ex)
      {
         Logger.getLogger(DirectoryTreeTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}

class MyFileVisitor extends SimpleFileVisitor<Path>
{
   @Override
   public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
   {
      return super.preVisitDirectory(dir, attrs);
   }

   @Override
   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
   {
      System.out.println(file);
      return FileVisitResult.CONTINUE;
   }

   @Override
   public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
   {
      return super.visitFileFailed(file, exc);
   }

   @Override
   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
   {
      return super.postVisitDirectory(dir, exc);
   }

}
