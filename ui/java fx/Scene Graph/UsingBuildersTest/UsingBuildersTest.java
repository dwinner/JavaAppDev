package usingbuilderstest;

import javafx.animation.FillTransitionBuilder;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Использование builder'ов
 *
 * @author oracle_pr1
 */
public class UsingBuildersTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        Rectangle r = new Rectangle(0, 0, 250, 250);
        r.setFill(Color.BLUE);
        root.getChildren().add(r);

        ParallelTransition transition = new ParallelTransition(r,
                                                               TranslateTransitionBuilder.
           create().duration(Duration.millis(750)).toX(390).toY(390).build(),
                                                               FillTransitionBuilder.
           create().duration(Duration.millis(750)).toValue(Color.RED).build(),
                                                               RotateTransitionBuilder.
           create().duration(Duration.millis(750)).toAngle(360).build(),
                                                               ScaleTransitionBuilder.
           create().duration(Duration.millis(750)).toX(0.1).toY(0.1).build());
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(scene);
        stage.show();
    }
}
