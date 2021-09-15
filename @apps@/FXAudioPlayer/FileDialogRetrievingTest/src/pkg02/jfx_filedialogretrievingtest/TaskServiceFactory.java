package pkg02.jfx_filedialogretrievingtest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import ru.inbox.dwinner.id3library.InvalidDataException;
import ru.inbox.dwinner.id3library.UnsupportedTagException;

/**
 * Фабрика объектов для фоновых задач JFX.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 11-07-2012
 */
public class TaskServiceFactory
{
   /**
    * Получение сервисного потока загрузки плей-листов.
    *
    * @param progressBar Прогресс-бар для индикации загрузки
    * @param progressIndicator Индикатор выполнения загрузки
    * @param playTable Агрегирующий объект для вида и модели таблицы
    * @param files Массив загруженных файлов
    * @param parentStage Родительское окно верхнего уровня
    * @return
    */
   public static Service<Void> createLoadPlaylistService(final ProgressBar progressBar,
                                                         final ProgressIndicator progressIndicator,
                                                         final PlayTable playTable,
                                                         final File[] files,
                                                         final Stage parentStage)
   {
      return new Service<Void>()
      {
         @Override
         protected Task<Void> createTask()
         {
            return new FileRetrieverTask(progressBar, progressIndicator, playTable, files, parentStage);
         }
      };
   }

   /**
    * Задача для извлечения данных модели в отдельном потоке выполнения.
    */
   private static class FileRetrieverTask extends Task<Void>
   {
      private static AtomicBoolean atomicBusySignal = new AtomicBoolean(false);
      private final ProgressBar updateProgressBar;
      private final ProgressIndicator updateIndicator;
      private final PlayTable playTable;
      private final File[] audioFiles;
      private final int totalIterations;
      private final Stage parentStage;

      /**
       * Инициализация данных и UI-компонентов для задачи.
       *
       * @param updateBar Прогресс-бар для индикации загрузки
       * @param indicator Индикатор выполнения загрузки
       * @param playTable Агрегирующий объект для вида и модели таблицы
       * @param loadedFiles Массив загруженных файлов
       * @param primaryStage Родительское окно верхнего уровня
       */
      FileRetrieverTask(ProgressBar updateBar, ProgressIndicator indicator, PlayTable playTable,
                        File[] loadedFiles, Stage primaryStage)
      {
         updateProgressBar = updateBar;
         updateIndicator = indicator;
         this.playTable = playTable;
         audioFiles = loadedFiles;
         totalIterations = loadedFiles.length;
         parentStage = primaryStage;
         
         updateProgressBar.setVisible(true);
         updateIndicator.setVisible(true);
      }

      @Override
      protected Void call() throws Exception
      {
         if (atomicBusySignal.get())
         {
            Platform.runLater(new Runnable()
            {
               @Override
               public void run()
               {
                  Stage busyStageDialog = StageDialogFactory.newBusyDialog(parentStage, true, "Wait A lot");
                  busyStageDialog.sizeToScene();
                  busyStageDialog.show();
               }
            });
            return null;
         }
         atomicBusySignal.set(true);

         Mp3InfoRetriever[] audioInfo = new Mp3InfoRetriever[totalIterations];

         for (int i = 0; i < totalIterations; i++)
         {
            try
            {
               audioInfo[i] = new Mp3InfoRetriever(audioFiles[i].getAbsolutePath());
               if (audioInfo[i] != null)
               {
                  final PlaylistInfo currentPlayListInfo =
                    new PlaylistInfo(audioInfo[i].getArtist(), audioInfo[i].getTrackName(),
                                     audioInfo[i].getPlayTimeString(), audioInfo[i].getBitrateString(),
                                     audioInfo[i].getSamplingFrequencyString());
                  currentPlayListInfo.setObjectRetriever(audioInfo[i]);
                  Platform.runLater(new Runnable()
                  {
                     @Override
                     public void run()
                     {
                        playTable.setPlayListData(currentPlayListInfo);
                     }
                  });
               }

               updateProgress(i, totalIterations);
            }
            catch (IOException | UnsupportedTagException | InvalidDataException e)
            {
               LOG.log(Level.SEVERE, null, e);
            }
         }

         atomicBusySignal.set(false);
         return null;
      }

      @Override
      protected void succeeded()
      {
         super.succeeded();
         updateProgress(0, totalIterations);
         if (atomicBusySignal.get())
         {
            atomicBusySignal.set(false);
         }
         
         updateProgressBar.setVisible(false);
         updateIndicator.setVisible(false);
      }

      @Override
      protected void cancelled()
      {
         super.cancelled();
         updateProgress(0, totalIterations);
         if (atomicBusySignal.get())
         {
            atomicBusySignal.set(false);
         }
      }

      @Override
      protected void failed()
      {
         super.failed();
         updateProgress(0, totalIterations);
         if (atomicBusySignal.get())
         {
            atomicBusySignal.set(false);
         }
      }

      @Override
      protected void updateProgress(long workDone, long max)
      {
         if (!updateProgressBar.isVisible())
         {
            updateProgressBar.setVisible(true);
         }
         if (!updateIndicator.isVisible())
         {
            updateIndicator.setVisible(true);
         }        
         
         updateProgressBar.setProgress((double) workDone / (double) max);
         updateIndicator.setProgress((double) workDone / (double) max);
      }
   }

   private TaskServiceFactory()
   {
      assert false;
   }
   private static final Logger LOG = Logger.getLogger(TaskServiceFactory.class.getName());
}
