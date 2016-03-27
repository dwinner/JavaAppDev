package audioplayer.view.playpanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

/**
 * Базовая панель времени.
 *
 * @author oracle_pr1
 * @version 1.0.0 21.06.2012
 */
public class BaseTimeBar extends GridPane
{
   private Label timeLabel;    // Метка для времени.
   private Label currentTimeLabel; // Метка процесса времени.
   private ProgressIndicator timeIndicator;    // Индикатор проигрывания.
   private Slider timeSlider;  // Слайдер процесса проигрывания.
   private ProgressBar timeProgressBar;    // Прогресс-бар проигрывания.

   /**
    * Получение метки для времени.
    *
    * @return Метка для времени
    */
   public Label getTimeLabel()
   {
      return timeLabel;
   }

   /**
    * Получение метки процесса времени.
    *
    * @return Метка процесса времени
    */
   public Label getCurrentTimeLabel()
   {
      return currentTimeLabel;
   }

   /**
    * Получение индикатора проигрывания.
    *
    * @return Индикатор проигрывания
    */
   public ProgressIndicator getTimeIndicator()
   {
      return timeIndicator;
   }

   /**
    * Получение слайдера процесса проигрывания.
    *
    * @return Слайдер процесса проигрывания
    */
   public Slider getTimeSlider()
   {
      return timeSlider;
   }

   /**
    * Получение прогресс-бара проигрывания.
    *
    * @return Прогресс-бар проигрывания
    */
   public ProgressBar getTimeProgressBar()
   {
      return timeProgressBar;
   }

   /**
    * Инициализация базовой панели времени.
    *
    * @param aTimeLabel Метка для времени
    * @param aCurTimeLabel Метка процесса времени
    * @param aTimeIndicator Индикатор проигрывания
    * @param aTimeSlider Слайдер процесса проигрывания
    * @param aTimeProgressBar Прогресс-бар проигрывания
    */
   public BaseTimeBar(Label aTimeLabel,
                      Label aCurTimeLabel,
                      ProgressIndicator aTimeIndicator,
                      Slider aTimeSlider,
                      ProgressBar aTimeProgressBar)
   {
      timeLabel = aTimeLabel;
      currentTimeLabel = aCurTimeLabel;
      timeIndicator = aTimeIndicator;
      timeSlider = aTimeSlider;
      timeProgressBar = aTimeProgressBar;
      layoutComponents();
      bindComponents();
   }

   /**
    * Расположение компонентов в сетке.
    */
   private void layoutComponents()
   {
      setHgap(10);
      setVgap(10);
      setPadding(new Insets(10, 10, 10, 10));

      add(timeLabel, 0, 0);
      add(currentTimeLabel, 1, 0);
      add(timeIndicator, 3, 0);

      add(timeSlider, 0, 1, 3, 1);
      add(timeProgressBar, 0, 2, 3, 1);
   }

   /**
    * Привязка связанных компонентов.
    */
   private void bindComponents()
   {
      timeSlider.valueProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
         {
            if (timeSlider.getMax() <= 0)
            {
               return;
            }
            timeIndicator.setProgress(newValue.doubleValue() / timeSlider.getMax());
            timeProgressBar.setProgress(newValue.doubleValue() / timeSlider.getMax());
         }
      });
   }
}
