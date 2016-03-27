package preloaderemulationtest;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Эмуляция предварительной загрузки.
 *
 * @author oracle_pr1
 */
public class PreloaderEmulationTest extends Application
{
    private static final double START_VALUE = 0.0;
    private static final double INCREMENT_VALUE = 0.01;
    private static final double FINISH_VALUE = 1.0;
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void init() throws Exception
    {
        Preloader.ProgressNotification progressNotification =
           new Preloader.ProgressNotification(START_VALUE);
        while (progressNotification.getProgress() < FINISH_VALUE)
        {
            Thread.sleep(100);
            progressNotification = new Preloader.ProgressNotification(
               progressNotification.getProgress() + INCREMENT_VALUE);
        }
        /*
         notifyPreloader(new Preloader.ProgressNotification(0.0));
         Thread.sleep(5000);
         notifyPreloader(new Preloader.ProgressNotification(1.0));
         */
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Preload emulation");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("After preloading");
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
