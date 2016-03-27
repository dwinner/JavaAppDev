package pkg02.jfx_filedialogretrievingtest;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.inbox.dwinner.id3library.ID3Wrapper;
import ru.inbox.dwinner.id3library.InvalidDataException;
import ru.inbox.dwinner.id3library.Mp3File;
import ru.inbox.dwinner.id3library.UnsupportedTagException;

/**
 * Рекурсивное извлечение файлов по фильтру.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 09-07-2012
 */
public class FileRecursiveRetriever
{
   private static final Logger LOG = Logger.getLogger(FileRecursiveRetriever.class.getName());
   private static final String[] DEFAULT_VALID_EXTS = new String[]
   {
      ".mp3"
   };
   private Set<File> extractedFiles = new TreeSet<>();
   private File rootPath;
   private String[] validExtensions;

   /**
    * Фабричный метод создания объекта FileRecursiveRetriever
    *
    * @param root Корневой каталог
    * @param validExts Массив допустимых расширений
    * @return
    */
   public static FileRecursiveRetriever createRetriever(File root, String... validExts)
   {
      return new FileRecursiveRetriever(root, validExts);
   }

   /**
    * Конструктор извлечения файлов.
    *
    * @param root Корневой каталог
    * @param validExts Массив допустимых расширений.
    */
   private FileRecursiveRetriever(File root, String... validExts)
   {
      if (!root.isDirectory())
      {
         throw new AssertionError("File object must be directory: " + root);
      }
      rootPath = root;
      validExtensions = (validExts == null || validExts.length == 0) ? DEFAULT_VALID_EXTS : validExts;
   }

   /**
    * Извлечение файлов по фильтру в массив
    *
    * @return Массив извлеченных файлов.
    */
   public File[] retrieveFilesByFilter()
   {
      try
      {
         Files.walkFileTree(Paths.get(rootPath.getAbsolutePath()), new RecursiveFileVisitor());
         return extractedFiles.toArray(new File[extractedFiles.size()]);
      }
      catch (IOException ex)
      {
         LOG.log(Level.SEVERE, null, ex);
         return new File[0];
      }
   }

   /**
    * Группировка файлов по директориям.
    *
    * @return Карта по ассоциациям Каталог => Набор файлов в каталоге
    */
   public Map<File, Set<File>> groupFilesByDirectory()
   {
      File[] audioFiles = (extractedFiles.isEmpty())
        ? retrieveFilesByFilter()
        : extractedFiles.toArray(new File[extractedFiles.size()]);

      Map<File, Set<File>> audioMap = new HashMap<>();
      Set<File> parentDirs = new TreeSet<>();
      for (File audioFile : audioFiles)
      {
         parentDirs.add(audioFile.getParentFile());
      }
      for (File dirFile : parentDirs)
      {
         Set<File> audioFileSet = new TreeSet<>();
         audioFileSet.addAll(Arrays.asList(dirFile.listFiles(new FileFilter()
         {
            @Override
            public boolean accept(File pathname)
            {
               if (pathname.isDirectory())
               {
                  return false;
               }
               for (String extension : validExtensions)
               {
                  if (pathname.getName().endsWith(extension))
                  {
                     return true;
                  }
               }
               return false;
            }
         })));
         audioMap.put(dirFile, audioFileSet);
      }

      return audioMap;
   }

   /**
    * Группировка файлов по артисту.
    *
    * @return Карта Артист => Множество файлов.
    * @throws UnsupportedTagException
    * @throws InvalidDataException
    */
   public Map<String, Set<File>> groupFilesByArtist() throws UnsupportedTagException,
                                                             InvalidDataException
   {
      File[] audioFiles = (extractedFiles.isEmpty())
        ? retrieveFilesByFilter()
        : extractedFiles.toArray(new File[extractedFiles.size()]);

      Map<String, Set<File>> mapByArtist = new HashMap<>();
      for (File audioFile : audioFiles)
      {
         Mp3File currentMp3File;
         try
         {
            currentMp3File = new Mp3File(audioFile.getAbsolutePath());
            ID3Wrapper id3Wrapper =
              new ID3Wrapper(currentMp3File.getId3v1Tag(), currentMp3File.getId3v2Tag());
            String artist = id3Wrapper.getArtist();
            artist = isNullOrEmpty(artist) ? "Unknown" : capitalizeFirstChar(artist.trim()); // TODO: Locale
            if (!mapByArtist.containsKey(artist))
            {
               mapByArtist.put(artist, new HashSet<File>());
            }
            mapByArtist.get(artist).add(audioFile);
         }
         catch (IOException ex)
         {
            String artist = "Unknown"; // TODO: Locale
            if (!mapByArtist.containsKey(artist))
            {
               mapByArtist.put(artist, new HashSet<File>());
            }
            mapByArtist.get(artist).add(audioFile);
         }
      }

      return mapByArtist;
   }

   /**
    * Проверка строки на пустоту
    *
    * @param toCheck Строка для преобразования
    * @return true, если строка нулевая или пустая, false в противном случае
    */
   private static boolean isNullOrEmpty(String toCheck)
   {
      return toCheck == null || toCheck.trim().isEmpty() ? true : false;
   }

   /**
    * Преобразование первой буквы строки в верхний регистр
    *
    * @param toProcess Строка для преобразования
    * @return Преобразованная строка
    */
   private static String capitalizeFirstChar(String toProcess)
   {
      if (isNullOrEmpty(toProcess))
      {
         return toProcess;
      }
      return toProcess.substring(0, 1).toUpperCase(Locale.getDefault()) + toProcess.substring(1);
   }

   /**
    * Класс-посетитель файлов.
    */
   private class RecursiveFileVisitor extends SimpleFileVisitor<Path>
   {
      /**
       * Конструктор посетителя. Чистит "кэш" извлеченных файлов по необходимости.
       */
      private RecursiveFileVisitor()
      {
         if (!extractedFiles.isEmpty())
         {
            extractedFiles.clear();
         }
      }

      @Override
      public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException
      {
         File visitedFile = filePath.toFile();
         for (String extension : validExtensions)
         {
            if (visitedFile.canRead() && visitedFile.getName().endsWith(extension))
            {
               extractedFiles.add(filePath.toFile());
            }
         }
         return FileVisitResult.CONTINUE;
      }
   }

}
