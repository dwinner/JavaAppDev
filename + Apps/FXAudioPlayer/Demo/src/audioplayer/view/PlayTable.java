package audioplayer.view;

import audioplayer.model.PlayListInfo;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author oracle_pr1
 */
public class PlayTable
{
   private TableView<PlayListInfo> playTableView;
   private ObservableList<PlayListInfo> playListData;

   public PlayTable(TableView<PlayListInfo> aPlayTableView)
   {
      playTableView = aPlayTableView;

      // Заголовки столбцов таблицы.

      TableColumn<PlayListInfo, String> artistColumn = new TableColumn<>("Artist");
      TableColumn<PlayListInfo, String> trackColumn = new TableColumn<>("Track");
      TableColumn<PlayListInfo, String> durationColumn = new TableColumn<>("Duration");
      TableColumn<PlayListInfo, String> bitrateColumn = new TableColumn<>("Bitrate");
      TableColumn<PlayListInfo, String> freqColumn = new TableColumn<>("Sampling frequency");

      // Связывание столбцов таблицы с элементами модели

      artistColumn.setCellValueFactory(new PropertyValueFactory<PlayListInfo, String>("artist"));
      trackColumn.setCellValueFactory(new PropertyValueFactory<PlayListInfo, String>("track"));
      durationColumn.setCellValueFactory(new PropertyValueFactory<PlayListInfo, String>("duration"));
      bitrateColumn.setCellValueFactory(new PropertyValueFactory<PlayListInfo, String>("bitrate"));
      freqColumn.setCellValueFactory(new PropertyValueFactory<PlayListInfo, String>("frequency"));

      // Добавление столбцов к табличному виду

      playTableView.getColumns().addAll(
        Arrays.asList(artistColumn, trackColumn, durationColumn, bitrateColumn, freqColumn));

      // Изображение сигнала отсутствия данных

      Image nodataImage = new Image(getClass().getResourceAsStream("./images/nodata.jpg"));
      ImageView nodataImageView = new ImageView(nodataImage);
      playTableView.setPlaceholder(nodataImageView);      
   }

   public TableView<PlayListInfo> getPlayTableView()
   {
      return playTableView;
   }

   public ObservableList<PlayListInfo> getPlayListData()
   {
      return FXCollections.unmodifiableObservableList(playListData);
   }

   public void setPlayListData(PlayListInfo... dataModel)
   {
      if (playListData == null)
      {
         playListData = FXCollections.<PlayListInfo>observableArrayList(dataModel);
         playTableView.setItems(playListData);
      }
      else
      {
         playListData.addAll(dataModel);
      }
   }

   public void clearPlayList()
   {
      if (playListData != null && !playListData.isEmpty())
      {
         playListData.clear();
      }
   }

   /**
    * Установка предпочтительных размеров столбцов.
    *
    * @param width Ширина столбца.
    */
   public void columnWidthToScene()
   {
      if (playTableView.getScene() == null)
      {
         throw new AssertionError();
      }

      final int columnCount = playTableView.getColumns().size();
      double startSceneWidth = playTableView.getScene().getWidth();

      for (TableColumn<PlayListInfo, ?> tableColumn : playTableView.getColumns())
      {
         tableColumn.setPrefWidth(startSceneWidth / columnCount);
      }

      playTableView.getScene().widthProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            for (TableColumn<PlayListInfo, ?> tableColumn : playTableView.getColumns())
            {
               tableColumn.setPrefWidth(newValue.doubleValue() / columnCount);
            }
         }
      });
   }
}
