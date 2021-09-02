package glowtest;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author oracle_pr1
 */
public class GlowTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Glow Effect");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);  
        
        Button btn = new Button();        
        btn.setText("Next");
        btn.setPrefSize(100,30);
        btn.setStyle("-fx-font: bold italic 14pt Georgia;"
           + "-fx-text-fill: white;"
           + "-fx-background-color: #a0522d;"
           + "-fx-border-width: 3px; "
           + "-fx-border-color:#f4a460 #800000 #800000 #f4a460;");
        
        Glow glow = new Glow();
        glow.setLevel(0.0);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue kv = new KeyValue(glow.levelProperty(), 1.0);
        KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
      
        Rectangle r = new Rectangle();
        r.setWidth(115);
        r.setHeight(45);
        r.setArcHeight(20);
        r.setArcWidth(20);
        r.setFill(Color.web("#f4a460"));
        r.setStroke(Color.WHITE);
        r.setStrokeWidth(5);            
        r.setStrokeType(StrokeType.OUTSIDE);
        r.setEffect(glow);
        
        StackPane pane = new StackPane();
        pane.getChildren().addAll(r,btn);
        pane.setLayoutX(100);
        pane.setLayoutY(100);        
        
        root.getChildren().add(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
