package audioplayer.main;

import audioplayer.controller.MenuController;
import audioplayer.controller.PlayTableController;
import audioplayer.model.PlayListInfo;
import audioplayer.view.PlayTable;
import audioplayer.view.playpanel.PanelUniter;
import audioplayer.view.topmenu.BaseMenuBar;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Главный класс приложения с окном верхнего уровня.
 *
 * @author oracle_pr1
 */
public class FXAudioPlayer extends Application
{
   public static void main(String[] args)
   {
      FXAudioPlayer.launch(args);
   }
   public static final short DEFAULT_WIDTH = 640;
   public static final short DEFAULT_HEIGHT = 480;   

   @Override
   public void start(Stage primaryStage)
   {
      Scene scene = new Scene(createScene(), DEFAULT_WIDTH, DEFAULT_HEIGHT);

      if (playTable != null)
      {
         playTable.columnWidthToScene();
      }

      primaryStage.setTitle("FX-Audio player");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
    * Создание графа сцены.
    *
    * @return Узлы ветвей графа.
    */
   private Parent createScene()
   {
      BorderPane root = new BorderPane(); // Корневой узел сцены      

      PanelUniter panelUniter = new PanelUniter(); // Объединяющая панель      
      playTable = new PlayTable(new TableView<PlayListInfo>());  // Табличный вид плей-листа      

      // Контроллер загрузки файлов в модель таблицы

      MenuController loadController = new MenuController(playTable);
      PlayTableController playController = new PlayTableController(panelUniter, playTable);      

      root.setTop(BaseMenuBar.getInstance().getMenuBar());
      root.setCenter(playTable.getPlayTableView());
      root.setBottom(panelUniter);

      return root;
   }
   private PlayTable playTable;
}
