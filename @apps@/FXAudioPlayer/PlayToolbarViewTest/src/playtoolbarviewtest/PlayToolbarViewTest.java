package playtoolbarviewtest;

import fxaudio.view.playtoolbar.PlayToolbarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Тестирование компонента PlayToolbarView
 *
 * @author Denis
 */
public class PlayToolbarViewTest extends Application
{
   public static final int DEFAULT_SCENE_WIDTH = 800;
   public static final int DEFAULT_SCENE_HEIGHT = 600;

   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      BorderPane root = new BorderPane();
      root.setTop(PlayToolbarView.getInstance().layoutComponents());

      Scene scene = new Scene(root, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);

      primaryStage.setTitle("FXAudio player");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
