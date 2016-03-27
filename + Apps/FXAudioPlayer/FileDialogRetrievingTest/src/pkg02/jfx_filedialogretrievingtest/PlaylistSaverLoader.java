package pkg02.jfx_filedialogretrievingtest;

import java.beans.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.inbox.dwinner.id3library.InvalidDataException;
import ru.inbox.dwinner.id3library.UnsupportedTagException;

/**
 * Вспомогательный класс для многопоточной загрузки плей-листов.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 11-07-2012
 */
public class PlaylistSaverLoader
{
   private static final Logger LOG = Logger.getLogger(PlaylistSaverLoader.class.getName());
   private static final Map<String, List<String>> EXTENSION_MAP = new HashMap<>(1);
   private static final String START_DIR = System.getProperty("user.dir");
   private static FileChooser fileChooser;

   static
   {
      EXTENSION_MAP.put("FX Playlist", Arrays.asList("*.fxplaylist"));
   }

   /**
    * Сохранение текущего плей-листа в XML-совместимом формате.
    *
    * @param playTable Плей-лист
    * @param parentStage Родительское окно верхнего уровня
    * @param dialogTitle Заголовок окна.
    */
   public static void saveCurrentPlaylist(PlayTable playTable, Stage parentStage, String dialogTitle)
   {
      if (fileChooser == null)
      {
         fileChooser = FileChooserFactory.getFileChooser(START_DIR, "dummy", EXTENSION_MAP);
      }
      fileChooser.setTitle(dialogTitle);

      File fileToSave = fileChooser.showSaveDialog(parentStage);
      if (fileToSave == null)
      {
         return;
      }
      else if (!fileToSave.exists())
      {
         if (!fileToSave.getName().endsWith(".fxplaylist"))
         {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".fxplaylist");
         }
      }
      else if (!fileToSave.canWrite())
      {
         // В файл нельзя записывать, но платформа ОС скорее всего скажет нам об этом
      }

      // Собственно процесс сохранения

      ObservableList<PlaylistInfo> listToSave = playTable.getPlayListData();
      PlaylistInfo[] playList = listToSave.toArray(new PlaylistInfo[listToSave.size()]);
      PlaylistMediator[] playListSavers = new PlaylistMediator[playList.length];
      for (int i = 0; i < playListSavers.length; i++)
      {
         playListSavers[i] = new PlaylistMediator(playList[i]);
      }

      try
      {
         try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileToSave)))
         {
            encoder.setPersistenceDelegate(PlaylistMediator.class, new DefaultPersistenceDelegate()
            {
               @Override
               protected Expression instantiate(Object oldInstance, Encoder out)
               {
                  PlaylistMediator saver = (PlaylistMediator) oldInstance;
                  return new Expression(oldInstance, PlaylistMediator.class, "new", new Object[]
                    {
                       saver.getArtist(),
                       saver.getTrack(),
                       saver.getPlayTime(),
                       saver.getBitrate(),
                       saver.getFrequency(),
                       saver.getAudioFileName()
                    });
               }
            });
            encoder.writeObject(playListSavers);
         }
      }
      catch (FileNotFoundException ex)
      {
         LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
      }
   }

   /**
    * Многопоточная загрузка плей-листа.
    *
    * @param playTable Плей-лист
    * @param parentStage Родительское окно верхнего уровня
    * @param dialogTitle Заголовок диалогового окна.
    */
   public static void loadPlaylistToModel(PlayTable playTable, Stage parentStage, String dialogTitle)
   {
      if (fileChooser == null)
      {
         fileChooser = FileChooserFactory.getFileChooser(START_DIR, "dummy", EXTENSION_MAP);
      }
      final List<File> fxplaylistFiles = fileChooser.showOpenMultipleDialog(parentStage);
      if (fxplaylistFiles == null || fxplaylistFiles.isEmpty())
      {
         return;
      }

      ExecutorService executorService = Executors.newFixedThreadPool(fxplaylistFiles.size());
      for (File playlistFile : fxplaylistFiles)
      {
         executorService.execute(
           ConcurrentLoader.createConcurrentLoader(playlistFile, playTable, parentStage));
      }
   }

   /**
    * Многопоточный загрузчик плей-листа.
    */
   private static class ConcurrentLoader implements Runnable
   {
      /**
       * Фабричный метод создания объекта
       *
       * @param playlistFile Файл плей-листа
       * @param aPlayTable Плей-лист
       * @return Объект загрузчика
       */
      private static ConcurrentLoader createConcurrentLoader(File playlist, PlayTable table, Stage parent)
      {
         return new ConcurrentLoader(playlist, table, parent);
      }
      private File fxplaylistFile;
      private PlayTable playTable;
      private Stage parentStage;
      private Stage dialogStage;

      /**
       * Задание начальных параметоров загрузки плей-листа.
       *
       * @param playlistFile Файл плей-листа
       * @param aPlayTable Плей-лист
       */
      private ConcurrentLoader(File playlistFile, PlayTable aPlayTable, Stage parent)
      {
         fxplaylistFile = playlistFile;
         playTable = aPlayTable;
         parentStage = parent;
      }

      @Override
      public void run()
      {
         try
         {
            Platform.runLater(new Runnable()
            {
               @Override
               public void run()
               {
                  dialogStage = StageDialogFactory.
                    newWaitDialog(parentStage, true, "Wait loading playlist(s)");
                  dialogStage.sizeToScene();
                  dialogStage.show();
               }
            });

            try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(fxplaylistFile)))
            {
               Object playlistObject = decoder.readObject();
               if (playlistObject instanceof PlaylistMediator[])
               {
                  PlaylistMediator[] playListSavers = (PlaylistMediator[]) playlistObject;
                  PlaylistInfo[] playListInfos = new PlaylistInfo[playListSavers.length];
                  for (int i = 0; i < playListInfos.length; i++)
                  {
                     PlaylistInfo playListInfo =
                       new PlaylistInfo(playListSavers[i].getArtist(), playListSavers[i].getTrack(),
                                        playListSavers[i].getPlayTime(), playListSavers[i].getBitrate(),
                                        playListSavers[i].getFrequency());
                     playListInfo.setObjectRetriever(
                       new Mp3InfoRetriever(playListSavers[i].getAudioFileName()));
                     playListInfos[i] = playListInfo;
                  }
                  playTable.setPlayListData(playListInfos);
               }
               else
               {
                  // FIXME: Файл не соответствует формату сохранения fx-плейлистов
               }
            }

            Platform.runLater(new Runnable()
            {
               @Override
               public void run()
               {
                  if (dialogStage != null)
                  {
                     dialogStage.hide();
                     dialogStage.close();
                  }
               }
            });
         }
         catch (IOException | UnsupportedTagException | InvalidDataException ex)
         {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
         }
      }
   }

   private PlaylistSaverLoader()
   {
   }
}
