package trayiconsupporttest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static trayiconsupporttest.AudioSystemTray.AddRemoveSwither.ADD;
import static trayiconsupporttest.AudioSystemTray.AddRemoveSwither.REMOVE;

/**
 * Тестирование сворачивания аудио-плейера в системный лоток.
 *
 * @author oracle_pr1
 */
public class TrayIconSupportTest extends Application
{
   @Override
   public void start(final Stage primaryStage)
   {
      BorderPane root = new BorderPane();
      final AudioSystemTray audioTray = AudioSystemTray.getInstance();

      primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>()
      {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable,
                             Boolean oldValue,
                             Boolean newValue)
         {
            audioTray.audioTrayCommand(newValue.booleanValue() ? ADD : REMOVE);
         }
      });

      Scene scene = new Scene(root, 640, 480);

      primaryStage.setTitle("Tray Support");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
    * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in
    * case the application can not be launched through deployment artifacts, e.g., in IDEs with limited FX
    * support. NetBeans ignores main().
    *
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      launch(args);
   }
}
