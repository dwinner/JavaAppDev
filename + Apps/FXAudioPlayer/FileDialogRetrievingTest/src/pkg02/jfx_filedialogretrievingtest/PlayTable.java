package pkg02.jfx_filedialogretrievingtest;

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
 * Класс, агрегирующий модель и вид таблицы.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0
 */
public class PlayTable
{
   private TableView<PlaylistInfo> playTableView;
   private ObservableList<PlaylistInfo> playListData;

   /**
    * Инициализация табличного вида
    *
    * @param aPlayTableView Табличного вид
    */
   public PlayTable(TableView<PlaylistInfo> aPlayTableView)
   {
      playTableView = aPlayTableView;
      generateTableColumns();

      // Изображение сигнала отсутствия данных

      Image nodataImage = new Image(getClass().getResourceAsStream("nodata.jpg"));
      ImageView nodataImageView = new ImageView(nodataImage);
      playTableView.setPlaceholder(nodataImageView);      
   }

   /**
    * Настройка столбцов таблицы.
    */
   private void generateTableColumns()
   {
      // Заголовки столбцов таблицы.

      TableColumn<PlaylistInfo, String> artistColumn = new TableColumn<>("Artist"); // TODO: Locate
      TableColumn<PlaylistInfo, String> trackColumn = new TableColumn<>("Track");   // TODO: Locate
      TableColumn<PlaylistInfo, String> durationColumn = new TableColumn<>("Duration");   // TODO: Locate
      TableColumn<PlaylistInfo, String> bitrateColumn = new TableColumn<>("Bitrate");  // TODO: Locate
      TableColumn<PlaylistInfo, String> freqColumn = new TableColumn<>("Sampling frequency");   // TODO: Locate

      // Связывание столбцов таблицы с элементами модели

      artistColumn.setCellValueFactory(new PropertyValueFactory<PlaylistInfo, String>("artist"));
      trackColumn.setCellValueFactory(new PropertyValueFactory<PlaylistInfo, String>("track"));
      durationColumn.setCellValueFactory(new PropertyValueFactory<PlaylistInfo, String>("duration"));
      bitrateColumn.setCellValueFactory(new PropertyValueFactory<PlaylistInfo, String>("bitrate"));
      freqColumn.setCellValueFactory(new PropertyValueFactory<PlaylistInfo, String>("frequency"));

      // Добавление столбцов к табличному виду

      playTableView.getColumns().addAll(
        Arrays.asList(artistColumn, trackColumn, durationColumn, bitrateColumn, freqColumn));
   }

   /**
    * Получение табличного вида
    *
    * @return Табличного вид
    */
   public TableView<PlaylistInfo> getPlayTableView()
   {
      return playTableView;
   }

   /**
    * Получение немодифицированного представления модели данных таблицы
    *
    * @return Немодифицированное представления модели данных таблицы
    */
   public synchronized ObservableList<PlaylistInfo> getPlayListData()
   {
      return FXCollections.unmodifiableObservableList(playListData);
   }

   /**
    * Установка/добавление данных для плей-листа.
    *
    * @param dataModel Набор объектов PlaylistInfo
    */
   public synchronized void setPlayListData(PlaylistInfo... dataModel)
   {
      if (playListData == null)
      {
         playListData = FXCollections.<PlaylistInfo>observableArrayList(dataModel);
         playTableView.setItems(playListData);
      }
      else
      {
         playListData.addAll(dataModel);
      }
   }   

   /**
    * Очистка плей-листа.
    */
   public synchronized void clearPlayList()
   {
      if (playListData != null && !playListData.isEmpty())
      {
         playListData.clear();
      }
   }

   /**
    * Установка предпочтительных размеров столбцов в соответствии с размерами графа сцены.
    *
    * @param tableView Табличный вид.
    */
   public static void columnWidthToScene(final TableView<?> tableView)
   {
      if (tableView.getScene() == null)
      {
         throw new AssertionError();
      }

      final int columnCount = tableView.getColumns().size();
      double startSceneWidth = tableView.getScene().getWidth();

      for (TableColumn<?, ?> tableColumn : tableView.getColumns())
      {
         tableColumn.setPrefWidth(startSceneWidth / columnCount);
      }

      tableView.getScene().widthProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            for (TableColumn<?, ?> tableColumn : tableView.getColumns())
            {
               tableColumn.setPrefWidth(newValue.doubleValue() / columnCount);
            }
         }
      });
   }
}
