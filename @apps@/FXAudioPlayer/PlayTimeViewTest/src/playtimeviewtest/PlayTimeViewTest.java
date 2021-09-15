package playtimeviewtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Демонстриционная панель времени.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class PlayTimeViewTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      BorderPane root = new BorderPane();
      root.setTop(PlayTimeView.getInstance().layoutComponents());

      Scene scene = new Scene(root, 640, 480);

      primaryStage.setTitle("Hello World!");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
