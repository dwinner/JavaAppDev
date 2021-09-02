package pkg1.textsample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

/**
 * Узлы текста в FX
 * <p/>
 * @author JavaFx
 */
public class TextSample extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Text text = TextBuilder.create().
            text("Stroke and Fill").font(Font.font("Tahoma", 100)).build();
        text.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT,
            new Stop[]
            {
                new Stop(0, Color.AQUA),
                new Stop(0.5f, Color.RED)
            })
        );
        text.setStrokeWidth(1);

        StackPane root = new StackPane();
        root.getChildren().add(text);
        primaryStage.setTitle("Sample Text Node");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
