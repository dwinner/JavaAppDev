package playvolumeviewtest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Панель управления звуком
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class PlayVolumeView implements PlacedInPane
{
   private PlayVolumeView()
   {
   }
   private Label volumeLabel = new Label("Volume: ");
   private Label balanceLabel = new Label("Balance: ");
   private Slider volumeSlider = new Slider(0., 1., 0.);
   private Slider balanceSlider = new Slider(0., 1., 0.);
   private static ImageView onImage =
     new ImageView(new Image(PlayVolumeView.class.getResource("Sound.png").toExternalForm()));
   private static ImageView offImage =
     new ImageView(new Image(PlayVolumeView.class.getResource("Mute.png").toExternalForm()));
   private static Tooltip onTooltip = new Tooltip("Sound On");
   private static Tooltip offTooltip = new Tooltip("Sound Off");
   private ToggleButton soundOnOffButton = ToggleButtonBuilder.create().selected(false).tooltip(offTooltip).graphic(
     onImage).build();

   
   {
      volumeSlider.setPrefWidth(100.);
      balanceSlider.setPrefWidth(volumeSlider.getPrefWidth());
      volumeSlider.widthProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            balanceSlider.setPrefWidth(newValue.doubleValue());
         }
      });

      soundOnOffButton.selectedProperty().addListener(new ChangeListener<Boolean>()
      {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable,
                             Boolean oldValue,
                             Boolean newValue)
         {
            soundOnOffButton.setTooltip(newValue.booleanValue() ? onTooltip : offTooltip);
            soundOnOffButton.setGraphic(newValue.booleanValue() ? offImage : onImage);            
         }
      });
   }

   /**
    * Получение экземпляра PlayVolumeView
    * @return Экземпляр PlayVolumeView
    */
   public static PlayVolumeView getInstance()
   {
      return PlayVolumeViewHolder.INSTANCE;
   }

   @Override
   public Pane layoutComponents()
   {
      GridPane volumePane = new GridPane();

      GridPane.setConstraints(volumeLabel, 0, 0);
      GridPane.setMargin(volumeLabel, new Insets(10.));
      GridPane.setHalignment(volumeLabel, HPos.LEFT);
      GridPane.setValignment(volumeLabel, VPos.BASELINE);

      GridPane.setConstraints(volumeSlider, 1, 0);
      GridPane.setMargin(volumeSlider, new Insets(10.));
      GridPane.setHalignment(volumeSlider, HPos.RIGHT);
      GridPane.setValignment(volumeSlider, VPos.BASELINE);
      GridPane.setHgrow(volumeSlider, Priority.ALWAYS);

      GridPane.setConstraints(balanceLabel, 0, 1);
      GridPane.setMargin(balanceLabel, new Insets(10.));
      GridPane.setHalignment(balanceLabel, HPos.LEFT);
      GridPane.setValignment(balanceLabel, VPos.BASELINE);

      GridPane.setConstraints(balanceSlider, 1, 1);
      GridPane.setMargin(balanceSlider, new Insets(10.));
      GridPane.setHalignment(balanceSlider, HPos.RIGHT);
      GridPane.setValignment(balanceSlider, VPos.BASELINE);
      GridPane.setHgrow(balanceSlider, Priority.ALWAYS);

      GridPane.setConstraints(soundOnOffButton, 1, 2, 2, 1);
      GridPane.setMargin(soundOnOffButton, new Insets(10.));
      GridPane.setHalignment(soundOnOffButton, HPos.RIGHT);
      GridPane.setValignment(soundOnOffButton, VPos.BOTTOM);
      GridPane.setHgrow(soundOnOffButton, Priority.NEVER);
      GridPane.setVgrow(soundOnOffButton, Priority.NEVER);

      volumePane.getChildren().addAll(volumeLabel, volumeSlider, balanceLabel, balanceSlider, soundOnOffButton);
      volumePane.setStyle("-fx-border-color: deepskyblue;\n"
        + "-fx-border-insets: 5;\n"
        + "-fx-border-width: 2;\n"
        + "-fx-border-style: solid;"
        + "-fx-border-radius: 7;\n");
      return volumePane;
   }

   /**
    * Хранитель единственной ссылки PlayVolumeView.
    */
   private static class PlayVolumeViewHolder
   {
      private static final PlayVolumeView INSTANCE = new PlayVolumeView();

      private PlayVolumeViewHolder()
      {
      }
   }

   /**
    * Метка для звука
    * @return Метка для звука
    */
   public Label getVolumeLabel()
   {
      return volumeLabel;
   }

   /**
    * Метка для баланса
    * @return Метка для баланса
    */
   public Label getBalanceLabel()
   {
      return balanceLabel;
   }

   /**
    * Слайдер управления звуком
    * @return Слайдер управления звуком
    */
   public Slider getVolumeSlider()
   {
      return volumeSlider;
   }

   /**
    * Слайдер управления балансом звучания
    * @return Слайдер управления балансом звучания
    */
   public Slider getBalanceSlider()
   {
      return balanceSlider;
   }

   /**
    * Кнопка включения/выключения звука
    * @return Кнопка включения/выключения звука
    */
   public ToggleButton getSounOnOff()
   {
      return soundOnOffButton;
   }
}
