package shadowtest;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author oracle_pr1
 */
public class ShadowTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Shadow Effect");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        Shadow effect = new Shadow();
        effect.setColor(Color.web("#a0522d"));

        Button btnS = new Button();
        btnS.setPrefSize(110, 40);
        btnS.setEffect(effect);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue kv = new KeyValue(btnS.opacityProperty(), 0.0);
        KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        Button btn = new Button();
        btn.setText("Next");
        btn.setPrefSize(100, 30);
        btn.setStyle(
           "-fx-font: bold italic 14pt Georgia;"
           + "-fx-text-fill: white;"
           + "-fx-background-color: #a0522d;"
           + "-fx-border-width: 3px; "
           + "-fx-border-color:#f4a460 #800000 #800000 #f4a460;");

        StackPane pane = new StackPane();
        pane.getChildren().addAll(btnS, btn);
        pane.setLayoutX(100);
        pane.setLayoutY(100);

        root.getChildren().add(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
