package playvolumeviewtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Демонстрация панели управления звуком.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class PlayVolumeViewTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      BorderPane root = new BorderPane();
      root.setRight(PlayVolumeView.getInstance().layoutComponents());
      Scene scene = new Scene(root, 300, 250);

      primaryStage.setTitle("Hello World!");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
