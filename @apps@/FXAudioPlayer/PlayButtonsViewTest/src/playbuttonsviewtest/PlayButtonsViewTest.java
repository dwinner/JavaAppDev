package playbuttonsviewtest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Denis
 */
public class PlayButtonsViewTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      BorderPane root = new BorderPane();
      root.setBottom(PlayButtonView.getInstance().layoutComponents());
      Scene scene = new Scene(root, 640, 480);

      primaryStage.setTitle("Play buttons test");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
