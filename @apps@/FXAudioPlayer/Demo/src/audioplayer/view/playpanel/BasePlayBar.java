package audioplayer.view.playpanel;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * Базовая панель кнопок проигрывания.
 *
 * @author oracle_pr1
 * @version 1.0.0 21.06.2012
 */
public class BasePlayBar extends HBox
{
   private ToggleButton playButton;  // Кнопка "Play"
   private ToggleButton pauseButton; // Кнопка "Pause"
   private ToggleButton stopButton;  // Кнопка "Stop"
   private ToggleGroup toggleGroup;

   /**
    * Получение кнопки Play
    *
    * @return Кнопка "Play"
    */
   public ToggleButton getPlayButton()
   {
      return playButton;
   }

   /**
    * Получение кнопки Pause
    *
    * @return Кнопка "Pause"
    */
   public ToggleButton getPauseButton()
   {
      return pauseButton;
   }

   /**
    * Получение кнопки Stop
    *
    * @return Кнопка "Stop"
    */
   public ToggleButton getStopButton()
   {
      return stopButton;
   }

   /**
    * Инициализация панели кнопок проигрывания
    *
    * @param play Кнопка "Play"
    * @param pause Кнопка "Pause"
    * @param stop Кнопка "Stop"
    */
   public BasePlayBar(ToggleButton play, ToggleButton pause, ToggleButton stop)
   {
      toggleGroup = new ToggleGroup();

      playButton = play;
      playButton.setToggleGroup(toggleGroup);
      
      pauseButton = pause;
      pauseButton.setToggleGroup(toggleGroup);
      
      stopButton = stop;
      stopButton.setToggleGroup(toggleGroup);
      
      getChildren().addAll(play, pause, stop);
      configureBasePlayBar();
   }

   /**
    * Настройка базовой панели кнопок проигрывания.
    */
   private void configureBasePlayBar()
   {      
      setSpacing(10);
      for (Node aNode : getChildrenUnmodifiable())
      {
         setMargin(aNode, new Insets(10, 10, 10, 10));
      }
   }
}
