package audioplayer.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Вспомогательный класс рекурсивного извлечения файлов из каталогов
 * @author oracle_pr1
 */
public final class FileRecursiveRetriever
{
   private static final Logger LOG = Logger.getLogger(FileRecursiveRetriever.class.getName());
   private static Set<File> allFiles = new TreeSet<>();

   private FileRecursiveRetriever()
   {
      assert false;
   }

   public static File[] retrieveFilesByFilter(File rootPath)
   {
      try
      {
         Files.walkFileTree(Paths.get(rootPath.getAbsolutePath()), new RecursiveFileVisitor());
         return allFiles.toArray(new File[allFiles.size()]);
      }
      catch (IOException ex)
      {
         LOG.log(Level.SEVERE, null, ex);
         return new File[0];
      }
   }

   public static Map<File, Set<File>> associateFilesBySameDirectory(File[] files)
   {
      throw new UnsupportedOperationException("Not supported yet");
   }

   private static class RecursiveFileVisitor extends SimpleFileVisitor<Path>
   {
      RecursiveFileVisitor()
      {
         if (!allFiles.isEmpty())
         {
            allFiles.clear();
         }
      }

      @Override
      public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException
      {
         File visitedFile = filePath.toFile();
         if (visitedFile.canRead() && visitedFile.getName().endsWith(".mp3"))
         {
            allFiles.add(filePath.toFile());
         }
         return FileVisitResult.CONTINUE;
      }
   }
}
