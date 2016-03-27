package audioplayer.controller;

import audioplayer.model.Mp3InfoRetriever;
import audioplayer.model.PlayListInfo;
import audioplayer.model.mp3manipulator.InvalidDataException;
import audioplayer.model.mp3manipulator.UnsupportedTagException;
import audioplayer.utils.FileChooserFactory;
import audioplayer.utils.FileRecursiveRetriever;
import audioplayer.view.PlayTable;
import audioplayer.view.topmenu.BaseFileMenu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Контроллер обработки загрузки файлов для элементов управления меню в модель таблицы плей-листа
 *
 * @author oracle_pr1
 */
public class MenuController
{
   private PlayTable playTable;
   private Lock playListDataLock;
   private static final Logger LOG = Logger.getLogger(MenuController.class.getName());
   private static ExecutorService executor = Executors.newCachedThreadPool();

   /**
    * Инициализация контроллера.
    *
    * @param playTable Объект логики представления плей-листа.
    */
   public MenuController(PlayTable playTable)
   {
      playListDataLock = new ReentrantLock();
      this.playTable = playTable;

      // PENDING: Обработка загрузки аудио-файлов

      MenuItem openFilesItem = BaseFileMenu.getInstance().getOpenFilesItem();
      openFilesItem.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            final List<File> files = FileChooserFactory.getFileChooser().showOpenMultipleDialog(null);
            if (files != null && !files.isEmpty())
            {
               executor.execute(new Runnable()
               {
                  @Override
                  public void run()
                  {
                     loadAudioData(files);
                  }
               });
            }
         }
      });

      // PENDING: Обработка загрузки аудио-файлов из каталога

      MenuItem openFolderItem = BaseFileMenu.getInstance().getOpenFolderItem();
      openFolderItem.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            final File directory = FileChooserFactory.getDirectoryChooser().showDialog(null);
            if (directory != null && directory.list().length > 0)
            {
               executor.execute(new Runnable()
               {
                  @Override
                  public void run()
                  {
                     final File[] audioFiles = FileRecursiveRetriever.retrieveFilesByFilter(directory);
                     if (audioFiles.length > 0)
                     {
                        loadAudioData(Arrays.asList(audioFiles));
                     }
                  }
               });
            }
         }
      });
   }

   /**
    * Получение объекта логики представления плей-листа
    *
    * @return Объект логики представления плей-листа
    */
   public PlayTable getPlayTable()
   {
      return playTable;
   }

   /**
    * Потоко-безопасная загрузка аудио-файлов в модель таблицы плей-листа
    *
    * @param files Список файлов для загрузки.
    */
   private void loadAudioData(List<File> files)
   {
      Mp3InfoRetriever[] audioInfo = new Mp3InfoRetriever[files.size()];
      List<PlayListInfo> playListInfos = new ArrayList<>(audioInfo.length);

      for (int i = 0; i < audioInfo.length; i++)
      {
         try
         {
            audioInfo[i] = new Mp3InfoRetriever(files.get(i).getAbsolutePath());
            if (audioInfo[i] != null)
            {
               PlayListInfo playListInfo = new PlayListInfo(audioInfo[i].getArtist(),
                                                            audioInfo[i].getTrackName(),
                                                            audioInfo[i].getPlayTimeString(),
                                                            audioInfo[i].getBitrateString(),
                                                            audioInfo[i].getSamplingFrequencyString());
               playListInfo.setObjectRetriever(audioInfo[i]);
               playListInfos.add(playListInfo);
            }
         }
         catch (IOException | UnsupportedTagException | InvalidDataException ex)
         {
            LOG.log(Level.SEVERE, null, ex);
         }
      }

      // Обновление модели таблицы плей-листа
      
      playListDataLock.lock();
      try
      {
         playTable.setPlayListData(playListInfos.toArray(new PlayListInfo[playListInfos.size()]));
      }
      finally
      {
         playListDataLock.unlock();
      }      
   }
}
