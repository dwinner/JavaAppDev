package pkg02.jfx_filedialogretrievingtest;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.Timer;

/**
 * Тестирование извлечения файлов, полученных из диалоговых окон и загрузка их в отдельном потоке
 *
 * @author oracle_pr1
 */
public class JFX_FileDialogRetrievingTest extends Application
{
   private ProgressBar loadingFilesBar = new ProgressBar(0);
   private ProgressIndicator loadingIndicator = new ProgressIndicator(0);
   private Button startLoading = new Button("Load Data");
   private Button loadPlayList = new Button("Load Playlist");
   private Button savePlayList = new Button("Save playlist");
   private Button clearPlayList = new Button("Clear playlist");
   private Button exitAfterButton = new Button("Exit After");
   private Label exitAfterLabel = new Label("Count Down");
   private PlayTable playTable = new PlayTable(new TableView<PlaylistInfo>());

   
   {
      loadingFilesBar.setPrefSize(250, 20);
      loadingFilesBar.setVisible(false);
      loadingIndicator.setVisible(false);
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(final Stage primaryStage)
   {
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
      {
         @Override
         public void handle(WindowEvent event)
         {
            System.exit(0);
         }
      });

      BorderPane root = new BorderPane();

      HBox topBox = new HBox(15);
      topBox.setPadding(new Insets(20, 10, 20, 10));
      topBox.getChildren().addAll(startLoading, loadPlayList, savePlayList, clearPlayList, exitAfterButton);
      root.setTop(topBox);

      root.setCenter(playTable.getPlayTableView());

      AnchorPane bottom = new AnchorPane();
      HBox bottomBox = new HBox(20.0);
      bottomBox.setPadding(new Insets(20, 10, 20, 10));
      bottomBox.getChildren().addAll(loadingFilesBar, loadingIndicator);
      bottom.getChildren().addAll(bottomBox, exitAfterLabel);
      AnchorPane.setLeftAnchor(bottomBox, 0.0);
      AnchorPane.setRightAnchor(exitAfterLabel, 10.0);
      root.setBottom(bottom);

      Scene scene = new Scene(root, 640, 480);
      PlayTable.columnWidthToScene(playTable.getPlayTableView());
      primaryStage.setTitle("Background file loading");
      primaryStage.setScene(scene);
      primaryStage.show();

      // Загрузка файлов для инициализации данных плей-листа.

      startLoading.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            // Вызов диалогового окна открытия директорий

            DirectoryChooser directoryChooser =
              FileChooserFactory.getDirectoryChooser("d:/Music", "Select directory");
            final File rootDir = directoryChooser.showDialog(primaryStage);
            if (rootDir == null)
            {
               return;
            }
            FileRecursiveRetriever retriever = FileRecursiveRetriever.createRetriever(rootDir);
            final File[] audioFiles = retriever.retrieveFilesByFilter();

            // Создание задачи для загрузки файлов и Запуск объекта Task

            Service<Void> service =
              TaskServiceFactory.createLoadPlaylistService(loadingFilesBar, loadingIndicator, playTable,
                                                           audioFiles, primaryStage);
            service.start();
         }
      });

      // Чистка плей-листа.

      clearPlayList.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            playTable.clearPlayList();
         }
      });

      // Сохранение плей-листа в XML-совместимом формате.

      savePlayList.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            PlaylistSaverLoader.saveCurrentPlaylist(playTable, primaryStage, "Save playlist");
         }
      });

      // Загрузка плей-листа из файла XML

      loadPlayList.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            PlaylistSaverLoader.loadPlaylistToModel(playTable, primaryStage, "Load playlist(s)");
         }
      });

      // Завершение по расписанию

      exitAfterButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            final Stage exitStage =
              StageDialogFactory.newScheduledExitDialog(primaryStage, true, "Exit After");
            exitStage.sizeToScene();
            exitStage.show();

            exitStage.setOnHidden(new EventHandler<WindowEvent>()
            {
               @Override
               public void handle(WindowEvent event)
               {
                  final int startValue = Integer.parseInt(exitStage.getTitle());

                  Timer swingTimer = new Timer(1000, new ActionListener()
                  {
                     
                     {
                        countDown = startValue;
                     }
                     private int countDown;

                     @Override
                     public void actionPerformed(java.awt.event.ActionEvent e)
                     {
                        Platform.runLater(new Runnable()
                        {
                           @Override
                           public void run()
                           {
                              exitAfterLabel.setText("Exit after: " + formatTime(countDown));
                              countDown--;
                           }
                        });
                     }
                  });
                  swingTimer.start();
                  exitAfterButton.setDisable(true);
               }
            });
         }
      });
   }

   /**
    * Форматирование времени.
    *
    * @param timeInSeconds Время в секундах
    * @return Отформатированная строка.
    */
   private static String formatTime(long timeInSeconds)
   {
      long hours = timeInSeconds / 3600;
      String hoursString = (hours < 10) ? ("0" + hours) : ("" + hours);
      long minutes = (timeInSeconds / 60 < 60) ? (timeInSeconds / 60) : ((timeInSeconds / 60) % 60);
      String minutesString = (minutes < 10) ? ("0" + minutes) : ("" + minutes);
      long seconds = (timeInSeconds < 60) ? timeInSeconds : timeInSeconds % 60;
      String secondsString = (seconds < 10) ? ("0" + seconds) : ("" + seconds);
      return hoursString + ":" + minutesString + ":" + secondsString;
   }
}
