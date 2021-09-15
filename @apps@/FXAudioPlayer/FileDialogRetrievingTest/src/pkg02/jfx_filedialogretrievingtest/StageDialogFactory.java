package pkg02.jfx_filedialogretrievingtest;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Фабрика объектов диалоговых окон для графов сцен JavaFX
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 12-07-2012
 */
public class StageDialogFactory
{
   /**
    * Диалог, который вызывется, если модель таблицы сильно загружена другим потоком выполнения.
    *
    * @param owner Родительское окно верхнего уровня
    * @param modality Флаг модальности диалогового окна
    * @param title Заголовок диалогового окна
    * @return Дочерний Stage
    */
   public static Stage newBusyDialog(final Stage owner, final boolean modality, final String title)
   {
      return new Stage()
      {
         
         {
            initOwner(owner);
            initModality(modality ? Modality.APPLICATION_MODAL : Modality.NONE);
            setOpacity(0.9);
            setTitle(title);
            toFront();
            Group root = new Group();
            Scene scene = new Scene(root, 250, 150, Color.WHITE);
            setScene(scene);
            VBox vBox = new VBox(20);
            Label messageLabel = new Label("Please wait until the previous operation has been\n"
              + "finished. And try again after this");
            Button closeButton = new Button("Close");
            closeButton.setOnAction(new EventHandler<ActionEvent>()
            {
               @Override
               public void handle(ActionEvent event)
               {
                  close();
               }
            });
            vBox.getChildren().addAll(messageLabel, closeButton);
            root.getChildren().add(vBox);
         }
      };
   }

   /**
    * Диалог, который вызывается в случае длительной загрузки плей-листов из соотв. файлов.
    *
    * @param owner Родительское окно верхнего уровня
    * @param modality Флаг модальности диалогового окна
    * @param title Заголовок диалогового окна
    * @return Дочерний Stage
    */
   public static Stage newWaitDialog(final Stage owner, final boolean modality, final String title)
   {
      return new Stage()
      {
         private final String dummy = "";

         
         {
            setOnShowing(new EventHandler<WindowEvent>()
            {
               @Override
               public void handle(WindowEvent event)
               {
                  FadeTransition stageFade =
                    new FadeTransition(Duration.millis(3000), owner.getScene().getRoot());
                  stageFade.setFromValue(1.0);
                  stageFade.setToValue(0.05);
                  stageFade.setCycleCount(1);
                  stageFade.play();
               }
            });

            initOwner(owner);
            initModality(modality ? Modality.APPLICATION_MODAL : Modality.NONE);
            setOpacity(0.9);
            setTitle(title);
            toFront();

            AnchorPane root = new AnchorPane();
            Scene scene = new Scene(root, 250, 100, Color.BEIGE);
            setScene(scene);

            VBox vBox = new VBox(10);

            HBox topBox = new HBox(10);
            ProgressIndicator progressIndicator = new ProgressIndicator(-1);
            topBox.getChildren().add(progressIndicator);

            HBox bottomBox = new HBox(10);
            ProgressBar progressBar = new ProgressBar(-1);
            bottomBox.getChildren().add(progressBar);

            progressBar.setPrefSize(200, 20);
            VBox.setVgrow(progressBar, Priority.ALWAYS);
            progressBar.progressProperty().bindBidirectional(progressIndicator.progressProperty());
            vBox.getChildren().addAll(topBox, bottomBox);

            root.getChildren().add(vBox);

            setOnHiding(new EventHandler<WindowEvent>()
            {
               @Override
               public void handle(WindowEvent event)
               {
                  FadeTransition stageFade =
                    new FadeTransition(Duration.millis(3000), owner.getScene().getRoot());
                  stageFade.setFromValue(0.05);
                  stageFade.setToValue(1.0);
                  stageFade.setCycleCount(1);
                  stageFade.play();
               }
            });
         }
      };
   }

   public static Stage newScheduledExitDialog(final Stage owner, final boolean modality, final String title)
   {
      return new Stage()
      {
         private final String dummy = "";

         
         {
            initOwner(owner);
            initModality(modality ? Modality.APPLICATION_MODAL : Modality.NONE);
            setTitle(title);
            toFront();

            Group root = new Group();
            root.setTranslateX(50);
            root.setTranslateY(20);
            root.setAutoSizeChildren(true);
            Scene scene = new Scene(root, 300, 200);
            setScene(scene);

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(20);
            gridPane.setVgap(10);

            Label hoursLabel = new Label("Hours");
            GridPane.setHalignment(hoursLabel, HPos.LEFT);
            GridPane.setValignment(hoursLabel, VPos.BASELINE);

            Label minutesLabel = new Label("Minutes");
            GridPane.setHalignment(minutesLabel, HPos.LEFT);
            GridPane.setValignment(minutesLabel, VPos.BASELINE);

            Label secondsLabel = new Label("Seconds");
            GridPane.setHalignment(secondsLabel, HPos.LEFT);
            GridPane.setValignment(secondsLabel, VPos.BASELINE);

            final TextField hoursTextField = new TextField("00");
            hoursTextField.setPrefColumnCount(2);
            GridPane.setHalignment(secondsLabel, HPos.LEFT);
            GridPane.setValignment(secondsLabel, VPos.BASELINE);

            final TextField minutesTextField = new TextField("00");
            minutesTextField.setPrefColumnCount(2);
            GridPane.setHalignment(secondsLabel, HPos.LEFT);
            GridPane.setValignment(secondsLabel, VPos.BASELINE);

            final TextField secondsTextField = new TextField("00");
            secondsTextField.setPrefColumnCount(2);
            GridPane.setHalignment(secondsLabel, HPos.LEFT);
            GridPane.setValignment(secondsLabel, VPos.BASELINE);

            Button confirmButton = new Button("OK!");

            gridPane.add(hoursLabel, 0, 0);
            gridPane.add(hoursTextField, 1, 0);
            gridPane.add(minutesLabel, 0, 1);
            gridPane.add(minutesTextField, 1, 1);
            gridPane.add(secondsLabel, 0, 2);
            gridPane.add(secondsTextField, 1, 2);
            gridPane.add(confirmButton, 0, 3);

            confirmButton.setOnAction(new EventHandler<ActionEvent>()
            {
               @Override
               public void handle(ActionEvent event)
               {
                  try
                  {
                     int seconds = Integer.parseInt(secondsTextField.getText());
                     int minutes = Integer.parseInt(minutesTextField.getText());
                     int hours = Integer.parseInt(hoursTextField.getText());
                     final int exitAfterInSeconds = seconds + minutes * 60 + hours * 60 * 60;
                     setTitle("" + exitAfterInSeconds);  // Фиктивное значение

                     new Timer().schedule(new TimerTask()
                     {
                        @Override
                        public void run()
                        {
                           System.exit(0);
                        }
                     }, exitAfterInSeconds * 1000); // Завершение через exitAfterInSeconds секунд                     
                                          
                     close();
                  }
                  catch (NumberFormatException e)
                  {
                     Logger.getLogger(
                       StageDialogFactory.class.getName()).log(Level.SEVERE, e.getLocalizedMessage());
                  }
               }
            });

            root.getChildren().addAll(gridPane);
         }
      };
   }

   private StageDialogFactory()
   {
   }
}
