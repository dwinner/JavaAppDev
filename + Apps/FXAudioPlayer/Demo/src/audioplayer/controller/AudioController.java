package audioplayer.controller;

import audioplayer.view.playpanel.PanelUniter;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

import static javafx.scene.media.MediaPlayer.Status.*;

/**
 * Компонент управления проигрыванием звука.
 *
 * @author oracle_pr1
 * @version 1.0.0 21.06.2012
 */
public class AudioController
{
   private MediaPlayer player;
   private boolean ready = false;
   private boolean repeat = false;
   private boolean stopRequested = false;
   private boolean atEndOfMedia = false;
   private Duration duration;
   private PanelUniter panelUniter;

   /**
    * Создание аудио-контроллера.
    *
    * @param aPlayer Медиа-плейер.
    * @param pUniter (View) Панель элементов управления.
    */
   public AudioController(MediaPlayer aPlayer, PanelUniter pUniter)
   {
      player = aPlayer;
      panelUniter = pUniter;

      // PENDING: Управление проигрыванием для кнопки Play

      final ToggleButton playButton = pUniter.getBasePlayBar().getPlayButton();
      playButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent actionEvent)
         {
            if (playButton.isSelected())
            {
               startPlay();
            }
            else
            {
               player.stop();
               panelUniter.getBasePlayBar().getStopButton().setSelected(true);
            }
         }
      });

      // PENDING: Управление паузой для кнопки Pause

      final ToggleButton pauseButton = pUniter.getBasePlayBar().getPauseButton();
      pauseButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            if (pauseButton.isSelected())
            {
               player.pause();
            }
            else
            {
               player.play();
               playButton.setSelected(true);
            }
         }
      });

      // PENDING: Управление остановкой проигрывания

      final ToggleButton stopButton = pUniter.getBasePlayBar().getStopButton();
      stopButton.selectedProperty().addListener(new ChangeListener<Boolean>()
      {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable,
                             Boolean oldValue,
                             Boolean newValue)
         {
            if (newValue.booleanValue())
            {
               player.stop();
            }
            else
            {
               player.play();
               playButton.setSelected(true);
            }
         }
      });

      // PENDING: Обновление UI-компонентов при изменении времени проигрывания.

      player.currentTimeProperty().addListener(new ChangeListener<Duration>()
      {
         @Override
         public void changed(ObservableValue<? extends Duration> observable,
                             Duration oldValue,
                             Duration newValue)
         {
            updateValues();
         }
      });

      // PENDING: Возобновление процесса проигрывания.

      player.setOnPlaying(new Runnable()
      {
         @Override
         public void run()
         {
            if (stopRequested)
            {
               player.pause();
               stopRequested = false;
            }
         }
      });

      // PENDING: Запрос паузы

      player.setOnPaused(new Runnable()
      {
         @Override
         public void run()
         {
            // TODO: Изменение состояния кнопки Play
         }
      });

      // PENDING: Инициализация состояния готовности проигрывания.

      player.setOnReady(new Runnable()
      {
         @Override
         public void run()
         {
            initPlayer();
         }
      });

      // PENDING: Управление повтором проигрывания записи.

      player.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);

      // PENDING: Управление плейером в конце трека

      player.setOnEndOfMedia(new Runnable()
      {
         @Override
         public void run()
         {
            if (!repeat)
            {
               stopRequested = true;   // TODO: Изменение состояния кнопки Play
               atEndOfMedia = true;
            }
         }
      });

      // PENDING: Управление временем проигрывания.

      final Slider timeSlider = panelUniter.getBaseTimeBar().getTimeSlider();
      timeSlider.valueProperty().addListener(new InvalidationListener()
      {
         @Override
         public void invalidated(Observable observable)
         {
            if (timeSlider.isValueChanging())
            {
               if (duration != null)
               {
                  player.seek(duration.multiply(timeSlider.getValue() / 100.0));
               }
               updateValues();
            }
         }
      });

      // PENDING: Управление громкостью звука.

      final Slider volumeSlider = panelUniter.getBaseVolumeBar().getVolumeSlider();
      volumeSlider.valueProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            if (volumeSlider.isValueChanging())
            {
               player.setVolume(volumeSlider.getValue() / 100.0);
            }
         }
      });
   }

   /**
    * Действия в начале проигрывания.
    */
   public void startPlay()
   {
      updateValues();
      Status status = player.getStatus();
      if (status == UNKNOWN || status == HALTED)
      {
         return;
      }
      if (status == PAUSED || status == READY || status == STOPPED)
      {
         if (atEndOfMedia)
         {
            player.seek(player.getStartTime());
            atEndOfMedia = false;
            updateValues();
         }
         player.play();
      }
      else
      {
         player.pause();
      }
   }

   /**
    * Установка плейера в состояние готовности.
    */
   private void initPlayer()
   {
      if (!ready)
      {
         duration = player.getMedia().getDuration();
         updateValues();
      }
      ready = true;
   }

   /**
    * Обновление UI-компонентов по различным событиям.
    */
   private void updateValues()
   {
      final Label playTimeLabel = panelUniter.getBaseTimeBar().getCurrentTimeLabel();
      final Slider timeSlider = panelUniter.getBaseTimeBar().getTimeSlider();
      final Slider volumeSlider = panelUniter.getBaseVolumeBar().getVolumeSlider();
      if (timeSlider != null && volumeSlider != null && duration != null)
      {
         Platform.runLater(new Runnable()
         {
            @Override
            public void run()
            {
               Duration currentTime = player.getCurrentTime();
               playTimeLabel.setText(formatTime(currentTime, duration));
               timeSlider.setDisable(duration.isUnknown());
               if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO)
                 && !timeSlider.isValueChanging())
               {
                  timeSlider.setValue(currentTime.divide(duration.toMillis()).toMillis() * 100.0);
               }
               if (!volumeSlider.isValueChanging())
               {
                  volumeSlider.setValue((int) Math.round(player.getVolume() * 100));
               }
            }
         });
      }
   }

   /**
    * Форматирование продолжительности временных меток.
    *
    * @param elapsed Текущая продолжительность времени
    * @param duration Общая продолжительность времени
    * @return Строка форматирования для меток.
    */
   private static String formatTime(Duration elapsed, Duration duration)
   {
      int intElapsed = (int) Math.floor(elapsed.toSeconds());
      int elapsedHours = intElapsed / (60 * 60);
      if (elapsedHours > 0)
      {
         intElapsed -= elapsedHours * 60 * 60;
      }
      int elapsedMinutes = intElapsed / 60;
      int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

      if (duration.greaterThan(Duration.ZERO))
      {
         int intDuration = (int) Math.floor(duration.toSeconds());
         int durationHours = intDuration / (60 * 60);
         if (durationHours > 0)
         {
            intDuration -= durationHours * 60 * 60;
         }
         int durationMinutes = intDuration / 60;
         int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

         if (durationHours > 0)
         {
            return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
                                 durationHours, durationMinutes, durationSeconds);
         }
         else
         {
            return String.format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes,
                                 durationSeconds);
         }
      }
      else
      {
         if (elapsedHours > 0)
         {
            return String.format("%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
         }
         else
         {
            return String.format("%02d:%02d", elapsedMinutes, elapsedSeconds);
         }
      }
   }
}
