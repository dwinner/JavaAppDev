package audioplayer.view.playpanel;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Панель, интегрирующая UI-элементы управления плейером.
 *
 * @author oracle_pr1
 * @version 1.0.0 21.06.2012
 */
public class PanelUniter extends HBox
{
   private BasePlayBar basePlayBar;    // Кнопочная панель проигрывания
   private BaseTimeBar baseTimeBar;    // Панель времени проигрывания
   private BaseVolumeBar baseVolumeBar;    // Панель управления звуком

   /**
    * Получение кнопочной панели проигрывания.
    *
    * @return Кнопочная панель проигрывания
    */
   public BasePlayBar getBasePlayBar()
   {
      return basePlayBar;
   }

   /**
    * Получение временной панели проигрывания.
    *
    * @return Панель времени проигрывания
    */
   public BaseTimeBar getBaseTimeBar()
   {
      return baseTimeBar;
   }

   /**
    * Получение панели управления звуком.
    *
    * @return Панель управления звуком
    */
   public BaseVolumeBar getBaseVolumeBar()
   {
      return baseVolumeBar;
   }

   /**
    * Инициализация интегрирующей панели по умолчанию.
    */
   public PanelUniter()
   {
      this(20);
   }

   /**
    * Инициализация интегрирующей панели с настроенными узлами графа.
    *
    * @param spacing Расстояние между соседними панелями по горизонтали (в пикселях)
    */
   public PanelUniter(double spacing)
   {
      super(spacing);

      // PENDING: Создание кнопочной панели проигрывания

      ToggleButton playButton = new ToggleButton("Play");
      ToggleButton pauseButton = new ToggleButton("Pause");
      ToggleButton stopButton = new ToggleButton("Stop");
      basePlayBar = new BasePlayBar(playButton, pauseButton, stopButton);

      // PENDING: Создание временной панели проигрывания
      
      Label timeLabel = new Label("Time:");
      Label currentTimeLabel = new Label("00:00");
      ProgressIndicator timeIndicator = new ProgressIndicator();
      Slider timeSlider = new Slider();
      ProgressBar progressBar = new ProgressBar();
      baseTimeBar = new BaseTimeBar(timeLabel,
                                    currentTimeLabel,
                                    timeIndicator,
                                    timeSlider,
                                    progressBar);

      // PENDING: Создание панели управления звуком
      
      Label volumeLabel = new Label("Volume:");
      Slider volumeSlider = new Slider();
      ToggleButton soundSwitcher = new ToggleButton("On/Off");
      baseVolumeBar = new BaseVolumeBar(volumeLabel, volumeSlider, soundSwitcher);

      layoutPanels();
   }

   /**
    * Настройка расположения панелей-узлов.
    */
   private void layoutPanels()
   {
      // TODO: Расположение панелей.
      VBox vBox = new VBox(15);
      vBox.getChildren().add(basePlayBar);
      vBox.getChildren().add(baseVolumeBar);

      getChildren().addAll(vBox, baseTimeBar);
   }
}
