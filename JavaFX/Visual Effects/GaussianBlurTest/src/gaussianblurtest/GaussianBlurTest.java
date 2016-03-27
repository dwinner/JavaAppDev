package gaussianblurtest;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author oracle_pr1
 */
public class GaussianBlurTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Gaussian Blur Effect");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(0.0);        
        
        Button btn = new Button();
        btn.setLayoutX(50);
        btn.setLayoutY(100);
        btn.setPrefSize(200, 20);
        btn.setText("Hello World!");
        btn.setStyle("-fx-font:bold 18pt Arial");
        btn.setEffect(blur);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue kvO = new KeyValue(btn.opacityProperty(), 0.0);
        KeyFrame kfO = new KeyFrame(Duration.millis(5000), kvO);
        KeyValue kvB = new KeyValue(blur.radiusProperty(), 10.0);
        KeyFrame kfB = new KeyFrame(Duration.millis(5000), kvB);
        timeline.getKeyFrames().addAll(kfO,kfB);
        timeline.play();
        
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
