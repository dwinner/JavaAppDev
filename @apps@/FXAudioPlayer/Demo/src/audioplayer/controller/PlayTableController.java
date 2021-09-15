package audioplayer.controller;

import audioplayer.model.MediaCreator;
import audioplayer.model.Mp3InfoRetriever;
import audioplayer.model.PlayListInfo;
import audioplayer.view.PlayTable;
import audioplayer.view.playpanel.PanelUniter;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

/**
 * Контроллер управления поведением таблицы плей-листа.
 *
 * @author oracle_pr1
 */
public class PlayTableController
{
   private static final Logger LOG = Logger.getLogger(PlayTableController.class.getName());
   private final PanelUniter panelUniter;
   private AudioController currentAudioController;
   private PlayTable playTable;
   private PlayListInfo previousPlayListInfo;
   private PlayListInfo currentPlayListInfo;
   private int previousSelectedIndex;
   private int currentSelectedIndex;
   private TablePosition previousTablePosition;
   private TablePosition currentTablePosition;

   /**
    *
    * @param aPanelUniter
    * @param playTable
    */
   public PlayTableController(PanelUniter aPanelUniter, PlayTable playTable)
   {
      this.playTable = playTable;
      panelUniter = aPanelUniter;

      // PENDING: Выделения строки таблицы

      TableView<PlayListInfo> tableView = playTable.getPlayTableView();
      tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayListInfo>()
      {
         @Override
         public void changed(ObservableValue<? extends PlayListInfo> observable,
                             PlayListInfo oldValue,
                             PlayListInfo newValue)
         {
            previousPlayListInfo = oldValue;
            currentPlayListInfo = newValue;
         }
      });
      tableView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            previousSelectedIndex = oldValue.intValue();
            currentSelectedIndex = newValue.intValue();
         }
      });

      // PENDING: Получения фокуса на строке таблицы

      tableView.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>()
      {
         @Override
         public void changed(ObservableValue<? extends TablePosition> observable,
                             TablePosition oldValue,
                             TablePosition newValue)
         {
            previousTablePosition = oldValue;
            currentTablePosition = newValue;
         }
      });

      // PENDING: Двойной щелчок на строке таблицы

      tableView.setOnMousePressed(new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent event)
         {
            if (event.getClickCount() == 2)
            {
               startPlaying();
            }
         }
      });

      // PENDING: Событие действия на строке таблицы

      tableView.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent keyEvent)
         {
            if (keyEvent.getCode() == KeyCode.ENTER)
            {
               startPlaying();
            }
         }
      });
   }

   private void startPlaying()
   {
      if (currentPlayListInfo != null)
      {
         Mp3InfoRetriever currRetriever = currentPlayListInfo.getObjectRetriever();
         File currAudioFile = currRetriever.getAudioFile();
         MediaPlayer currMediaPlayer;
         try
         {
            MediaPlayer prevMediaPlayer = MediaCreator.getCurrentMediaPlayer();
            if (prevMediaPlayer != null)
            {
               prevMediaPlayer.stop();
            }
            currMediaPlayer =
              MediaCreator.newInstance(currAudioFile.toURI().toURL().toExternalForm());
            currentAudioController = new AudioController(currMediaPlayer, panelUniter);
            currMediaPlayer.play();
         }
         catch (MalformedURLException ex)
         {
            LOG.log(Level.SEVERE, null, ex);
         }
      }
   }

   public AudioController getCurrentAudioController()
   {
      return currentAudioController;
   }

   public void setCurrentAudioController(AudioController currentAudioController)
   {
      this.currentAudioController = currentAudioController;
   }

   public PlayTable getPlayTable()
   {
      return playTable;
   }

   public void setPlayTable(PlayTable playTable)
   {
      this.playTable = playTable;
   }

   public PlayListInfo getPreviousPlayListInfo()
   {
      return previousPlayListInfo;
   }

   public void setPreviousPlayListInfo(PlayListInfo previousPlayListInfo)
   {
      this.previousPlayListInfo = previousPlayListInfo;
   }

   public PlayListInfo getCurrentPlayListInfo()
   {
      return currentPlayListInfo;
   }

   public void setCurrentPlayListInfo(PlayListInfo currentPlayListInfo)
   {
      this.currentPlayListInfo = currentPlayListInfo;
   }

   public int getPreviousSelectedIndex()
   {
      return previousSelectedIndex;
   }

   public void setPreviousSelectedIndex(int previousSelectedIndex)
   {
      this.previousSelectedIndex = previousSelectedIndex;
   }

   public int getCurrentSelectedIndex()
   {
      return currentSelectedIndex;
   }

   public void setCurrentSelectedIndex(int currentSelectedIndex)
   {
      this.currentSelectedIndex = currentSelectedIndex;
   }

   public TablePosition getPreviousTablePosition()
   {
      return previousTablePosition;
   }

   public void setPreviousTablePosition(TablePosition previousTablePosition)
   {
      this.previousTablePosition = previousTablePosition;
   }

   public TablePosition getCurrentTablePosition()
   {
      return currentTablePosition;
   }

   public void setCurrentTablePosition(TablePosition currentTablePosition)
   {
      this.currentTablePosition = currentTablePosition;
   }
}
