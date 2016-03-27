package playtimeviewtest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Панель времени.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class PlayTimeView implements PlacedInPane
{
   private PlayTimeView()
   {
   }
   private Label timeLabel = new Label("00:00:00/00:00:00");
   private ProgressBar timeBar = new ProgressBar(.0);
   private Slider timeSlider = new Slider(0., 1., .0);
   private ProgressIndicator timeIndicator = new ProgressIndicator(.0);

   
   {
      timeBar.progressProperty().bind(timeSlider.valueProperty());
      timeIndicator.progressProperty().bind(timeSlider.valueProperty());
      timeSlider.setPrefWidth(150.);
      timeBar.setPrefWidth(timeSlider.getPrefWidth());
      timeSlider.widthProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            timeBar.setPrefWidth(newValue.doubleValue());
         }
      });
   }

   /**
    * Единый экземпляр панели времени
    *
    * @return Единый экземпляр панели времени
    */
   public static PlayTimeView getInstance()
   {
      return PlayTimeViewHolder.INSTANCE;
   }

   @Override
   public Pane layoutComponents()
   {
      GridPane rootPane = new GridPane();

      AnchorPane labelsPane = new AnchorPane();
      Label legendLabel = new Label("Time: ");
      AnchorPane.setLeftAnchor(legendLabel, .0);
      AnchorPane.setRightAnchor(timeLabel, .0);
      labelsPane.getChildren().addAll(legendLabel, timeLabel);

      GridPane.setConstraints(labelsPane, 0, 0);
      GridPane.setHalignment(labelsPane, HPos.LEFT);
      GridPane.setHgrow(labelsPane, Priority.SOMETIMES);
      GridPane.setMargin(labelsPane, new Insets(10.));
      GridPane.setValignment(labelsPane, VPos.CENTER);
      GridPane.setVgrow(labelsPane, Priority.NEVER);

      GridPane.setConstraints(timeBar, 0, 1);
      GridPane.setHalignment(timeBar, HPos.LEFT);
      GridPane.setHgrow(timeBar, Priority.ALWAYS);
      GridPane.setMargin(timeBar, new Insets(10.));
      GridPane.setValignment(timeBar, VPos.CENTER);
      GridPane.setVgrow(timeBar, Priority.NEVER);

      GridPane.setConstraints(timeSlider, 0, 2);
      GridPane.setHalignment(timeSlider, HPos.LEFT);
      GridPane.setHgrow(timeSlider, Priority.ALWAYS);
      GridPane.setMargin(timeSlider, new Insets(10.));
      GridPane.setValignment(timeSlider, VPos.CENTER);
      GridPane.setVgrow(timeSlider, Priority.NEVER);

      GridPane.setConstraints(timeIndicator, 1, 0, 1, 3);
      GridPane.setHalignment(timeIndicator, HPos.RIGHT);
      GridPane.setHgrow(timeIndicator, Priority.SOMETIMES);
      GridPane.setMargin(timeIndicator, new Insets(10.));
      GridPane.setValignment(timeIndicator, VPos.BASELINE);
      GridPane.setVgrow(timeIndicator, Priority.NEVER);

      rootPane.getChildren().addAll(labelsPane, timeBar, timeSlider, timeIndicator);
      rootPane.setStyle("-fx-border-color: deepskyblue;\n"
        + "-fx-border-insets: 5;\n"
        + "-fx-border-width: 2;\n"
        + "-fx-border-style: solid;"
        + "-fx-border-radius: 7;\n");
      return rootPane;
   }

   /**
    * Хранитель экземпляра PlayTimeView.
    */
   private static class PlayTimeViewHolder
   {
      private static final PlayTimeView INSTANCE = new PlayTimeView();

      private PlayTimeViewHolder()
      {
      }
   }

   /**
    * Метка текущего/полного времени
    *
    * @return Метка текущего/полного времени
    */
   public Label getTimeLabel()
   {
      return timeLabel;
   }

   /**
    * Прогресс прошедшего времени
    *
    * @return Прогресс прошедшего времени
    */
   public ProgressBar getTimeBar()
   {
      return timeBar;
   }

   /**
    * Слайдер прошедшего времени
    *
    * @return Слайдер прошедшего времени
    */
   public Slider getTimeSlider()
   {
      return timeSlider;
   }

   /**
    * Индикатор прошедшего времени
    *
    * @return Индикатор прошедшего времени
    */
   public ProgressIndicator getTimeIndicator()
   {
      return timeIndicator;
   }
}
