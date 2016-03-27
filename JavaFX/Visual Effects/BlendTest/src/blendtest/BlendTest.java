package blendtest;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class BlendTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Blend Test");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        final Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(100);
        btn.setText("Next");
        btn.setPrefSize(100, 50);
        btn.setStyle("-fx-font:italic bold 18pt Georgia; "
           + "-fx-text-fill: white;"
           + "-fx-background-color:black;");

        Stop[] stops = new Stop[]
        {
            new Stop(0, Color.RED),
            new Stop(1, Color.YELLOW)
        };
        LinearGradient lg = new LinearGradient(0, 0, 0.25, 0.25, true,
                                               CycleMethod.REFLECT, stops);
        ColorInput colorInput = new ColorInput();
        colorInput.setWidth(100);
        colorInput.setHeight(50);
        colorInput.setPaint(lg);
        final Blend blend = new Blend();
        blend.setMode(BlendMode.LIGHTEN);
        blend.setBottomInput(colorInput);
        btn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                btn.setEffect(blend);
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                btn.setEffect(null);
            }
        });
        
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
