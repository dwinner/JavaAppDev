package innershadowtest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class InnerShadowTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Inner shadow effect");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 400);

        InnerShadow effect = new InnerShadow();
        effect.setColor(Color.OLIVE);

        Text text = new Text();
        text.setLayoutX(20);
        text.setLayoutY(100);
        text.setEffect(effect);
        text.setText("JavaFX 2.0");
        text.setFill(Color.KHAKI);
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 80));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setCache(true);

        TilePane pane = new TilePane();
        pane.setLayoutX(20);
        pane.setLayoutY(200);
        pane.setHgap(5);
        pane.setVgap(10);
        pane.setPrefColumns(2);

        Label labelRadius = new Label("Radius");
        Slider sliderRadius = new Slider();
        sliderRadius.setPrefWidth(100);
        sliderRadius.setMin(0.0);
        sliderRadius.setMax(127.0);
        effect.radiusProperty().bind(sliderRadius.valueProperty());

        Label labelChoke = new Label("Choke");
        Slider sliderChoke = new Slider();
        sliderChoke.setPrefWidth(100);
        sliderChoke.setMin(0.0);
        sliderChoke.setMax(1.0);
        effect.chokeProperty().bind(sliderChoke.valueProperty());

        Label labelOffsetX = new Label("OffsetX");
        Slider sliderOffsetX = new Slider();
        sliderOffsetX.setPrefWidth(100);
        sliderOffsetX.setMin(0.0);
        sliderOffsetX.setMax(50.0);
        effect.offsetXProperty().bind(sliderOffsetX.valueProperty());

        Label labelOffsetY = new Label("OffsetY");
        Slider sliderOffsetY = new Slider();
        sliderOffsetY.setPrefWidth(100);
        sliderOffsetY.setMin(0.0);
        sliderOffsetY.setMax(50.0);
        effect.offsetYProperty().bind(sliderOffsetY.valueProperty());

        pane.getChildren().addAll(labelRadius, sliderRadius, labelChoke, sliderChoke,
                                  labelOffsetX, sliderOffsetX, labelOffsetY, sliderOffsetY);

        root.getChildren().addAll(text, pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
