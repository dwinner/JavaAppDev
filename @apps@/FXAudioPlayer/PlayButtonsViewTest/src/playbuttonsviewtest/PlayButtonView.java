package playbuttonsviewtest;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Единичный класс представления кнопочной панели проигрывания.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class PlayButtonView implements PlacedInPane
{
   private PlayButtonView()
   {
   }
   private Button playButton = ButtonFactory.newButton(new ImageView(PlayButtonView.class.getResource(
     "icons/Play.png").toExternalForm()), "Play", "PLAY_TRACK");
   private Button pauseButton = ButtonFactory.newButton(new ImageView(PlayButtonView.class.getResource(
     "icons/Pause.png").toExternalForm()), "Pause", "PAUSE_TRACK");
   private Button stopButton = ButtonFactory.newButton(new ImageView(PlayButtonView.class.getResource(
     "icons/Stop.png").toExternalForm()), "Stop", "STOP_TRACK");
   private Button nextButton = ButtonFactory.newButton(new ImageView(PlayButtonView.class.getResource(
     "icons/NextTrack.png").toExternalForm()), "Next", "NEXT_TRACK");
   private Button prevButton = ButtonFactory.newButton(new ImageView(PlayButtonView.class.getResource(
     "icons/PreviousTrack.png").toExternalForm()), "Previous", "PREV_TRACK");
   private ToggleButton repeatTrackButton = ButtonFactory.newToggleButton(new ImageView(PlayButtonView.class.
     getResource("icons/Repeat.png").toExternalForm()), "Repeat track", "REPEAT_TRACK");
   private ToggleButton repeatPlaylistButton = ButtonFactory.
     newToggleButton(new ImageView(PlayButtonView.class.getResource("icons/RepeatPlaylist.png").
     toExternalForm()), "Repeat playlist", "REPEAT_PLAYLIST");

   /**
    * Получение однажды созданной ссылки PlayButtonView
    *
    * @return Ссылка PlayButtonView
    */
   public static PlayButtonView getInstance()
   {
      return PlayButtonViewHolder.INSTANCE;
   }

   @Override
   public Pane layoutComponents()
   {
      AnchorPane rootView = new AnchorPane();

      HBox buttonBox = new HBox(10.);
      buttonBox.setPadding(new Insets(10.));
      buttonBox.getChildren().addAll(playButton, pauseButton, stopButton, nextButton, prevButton,
                                     repeatTrackButton, repeatPlaylistButton);
      AnchorPane.setLeftAnchor(buttonBox, 10.);
      rootView.getChildren().add(buttonBox);      
      buttonBox.setStyle("-fx-border-color: deepskyblue;\n"
        + "-fx-border-insets: 5;\n"
        + "-fx-border-width: 2;\n"
        + "-fx-border-style: solid;"
        + "-fx-border-radius: 7;\n");
      
      return rootView;
   }

   /**
    * Хранитель единичной ссылки.
    */
   private static class PlayButtonViewHolder
   {
      private static final PlayButtonView INSTANCE = new PlayButtonView();

      private PlayButtonViewHolder()
      {
      }
   }

   /**
    * Кнопка начала игры
    *
    * @return Кнопка начала игры
    */
   public Button getPlayButton()
   {
      return playButton;
   }

   /**
    * Кнопка паузы
    *
    * @return Кнопка паузы
    */
   public Button getPauseButton()
   {
      return pauseButton;
   }

   /**
    * Кнопка останова проигрывания
    *
    * @return Кнопка останова проигрывания
    */
   public Button getStopButton()
   {
      return stopButton;
   }

   /**
    * Кнопка перехода к следующему треку
    *
    * @return Кнопка перехода к следующему треку
    */
   public Button getNextButton()
   {
      return nextButton;
   }

   /**
    * Кнопка перехода к предыдущему треку
    *
    * @return Кнопка перехода к предыдущему треку
    */
   public Button getPrevButton()
   {
      return prevButton;
   }

   /**
    * Кнопка повторения трека
    *
    * @return Кнопка повторения трека
    */
   public ToggleButton getRepeatTrackButton()
   {
      return repeatTrackButton;
   }

   /**
    * Кнопка повторения плей-листа
    *
    * @return Кнопка повторения плей-листа
    */
   public ToggleButton getRepeatPlaylistButton()
   {
      return repeatPlaylistButton;
   }
}
