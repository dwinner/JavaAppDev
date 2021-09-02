package pkg01.animatedbutton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Задание визуальных эффектов для UI-компонентов.
 * @author JavaFX Development Group
 */
public class AnimatedButton extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Animated Button");
        
        Button button = new Button();
        button.setText("OK");
        button.setFont(new Font("Tahoma", 24));
        button.setEffect(new Reflection());
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(button.opacityProperty(), 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(600), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
