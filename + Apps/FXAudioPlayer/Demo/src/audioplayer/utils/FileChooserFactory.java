package audioplayer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Статическая фабрика объектов диалоговых окон для выбора файлов.
 *
 * @author oracle_pr1
 */
public final class FileChooserFactory
{
   private final static String DEFAULT_START_PATH = System.getProperty("user.home");
   private final static String DEFAULT_WINDOW_TITLE = "Choose audiofile(s)/directory for playing";
   private final static String DEFAULT_DESCRIPTION = "Supported audio";
   private final static FileChooser.ExtensionFilter DEFAULT_EXTENSION_FILTER =
     new FileChooser.ExtensionFilter(DEFAULT_DESCRIPTION, "*.mp3");
   // ------------------------------- Зарезервированные объекты диалоговых окон ------------------------------
   private final static FileChooser fileChooser = new FileChooser();
   private final static DirectoryChooser directoryChooser = new DirectoryChooser();

   /**
    * Получение диалогового окна для выбора файлов
    *
    * @param dir Начальный каталог выбора
    * @param title Заголовок окна
    * @param descr Описание для поддерживаемых расширений
    * @param extList Список поддерживаемых расширений
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser(String dir, String title, String descr, List<String> extList)
   {
      assert dir != null && !dir.trim().isEmpty();
      assert title != null && !title.trim().isEmpty();
      resetFileChooser();

      fileChooser.setInitialDirectory(new File(dir));
      fileChooser.setTitle(title);
      if (extList != null && extList.size() > 0)
      {
         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(descr, extList));
      }
      else
      {
         fileChooser.getExtensionFilters().add(DEFAULT_EXTENSION_FILTER);
      }

      return fileChooser;
   }

   /**
    * Получение диалогового окна для выбора файлов
    *
    * @param dir Начальный каталог выбора
    * @param title Заголовок окна
    * @param descr Описание для поддерживаемых расширений
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser(String dir, String title, String descr)
   {
      return getFileChooser(dir, title, descr, DEFAULT_EXTENSION_FILTER.getExtensions());
   }

   /**
    * Получение диалогового окна для выбора файлов
    *
    * @param dir Начальный каталог выбора
    * @param title Заголовок окна
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser(String dir, String title)
   {
      return getFileChooser(dir, title, DEFAULT_DESCRIPTION);
   }

   /**
    * Получение диалогового окна для выбора файлов
    *
    * @param dir Начальный каталог выбора
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser(String dir)
   {
      return getFileChooser(dir, DEFAULT_WINDOW_TITLE);
   }

   /**
    * Получение диалогового окна для выбора файлов
    *
    * @return Диалоговое окно выбора файлов
    */
   public static FileChooser getFileChooser()
   {
      return getFileChooser(DEFAULT_START_PATH);
   }

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
    * Получение диалогового окна для выбора директории
    *
    * @param dir Начальный каталог выбора
    * @param title Заголовок окна
    * @return Диалоговое окно выбора директорий
    */
   public static DirectoryChooser getDirectoryChooser(String dir, String title)
   {
      assert dir != null && !dir.trim().isEmpty();
      assert title != null && !title.trim().isEmpty();

      directoryChooser.setInitialDirectory(new File(dir));
      directoryChooser.setTitle(title);

      return directoryChooser;
   }

   /**
    * Получение диалогового окна для выбора директории
    *
    * @param dir Начальный каталог выбора
    * @return Диалоговое окно выбора директорий
    */
   public static DirectoryChooser getDirectoryChooser(String dir)
   {
      return getDirectoryChooser(dir, DEFAULT_WINDOW_TITLE);
   }

   /**
    * Получение диалогового окна для выбора директории
    *
    * @return Диалоговое окно выбора директорий
    */
   public static DirectoryChooser getDirectoryChooser()
   {
      return getDirectoryChooser(DEFAULT_START_PATH);
   }

   private FileChooserFactory()
   {
      assert false;
   }
}
