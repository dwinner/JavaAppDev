package pkg02.jfx_filedialogretrievingtest;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Фабрика объектов диалоговых окон для выбора файлов.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 09-07-2012
 */
public class FileChooserFactory
{
   private final static FileChooser fileChooser = new FileChooser();
   private final static DirectoryChooser directoryChooser = new DirectoryChooser();

   /**
    * Сброс имеющихся расширений файлов в объекте FileChooser.
    */
   private static void resetFileChooser()
   {
      if (fileChooser.getExtensionFilters().isEmpty())
      {
         fileChooser.getExtensionFilters().clear();
      }
   }

   /**
    * Получение диалогового окна выбора файлов
    *
    * @param directory Начальный каталог выбора
    * @param title Заголовок окна
    * @param fileExtensions Карта расширений Описание => Список расширений
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser(String directory, String title,
                                            Map<String, List<String>> fileExtensions)
   {
      if (directory == null || directory.trim().isEmpty())
      {
         throw new AssertionError("Directory must be legal");
      }
      resetFileChooser();

      fileChooser.setInitialDirectory(new File(directory));
      if (title != null && !title.trim().isEmpty())
      {
         fileChooser.setTitle(title);
      }

      if (fileExtensions != null && !fileExtensions.isEmpty())
      {
         for (Entry<String, List<String>> extensionsEntry : fileExtensions.entrySet())
         {
            String description = extensionsEntry.getKey();
            List<String> extensions = extensionsEntry.getValue();

            if (description != null && !description.trim().isEmpty())
            {
               if (extensions != null && !extensions.isEmpty())
               {
                  fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(description, extensions));
               }
            }
         }
      }

      return fileChooser;
   }

   /**
    * Получение диалогового окна для выбора директории
    *
    * @param dir Начальный каталог выбора
    * @param title Заголовок окна
    * @return Диалоговое окно выбора директорий
    */
   public static DirectoryChooser getDirectoryChooser(String dir, String title)
   {
      if (dir == null || dir.trim().isEmpty())
      {
         throw new AssertionError("Directory must be legal");
      }
      directoryChooser.setInitialDirectory(new File(dir));

      if (title != null && !title.trim().isEmpty())
      {
         directoryChooser.setTitle(title);
      }

      return directoryChooser;
   }

   private FileChooserFactory()
   {
   }
}
