package pkg11.titledpanetest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class TitledPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Панель с заголовком");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();

        TitledPane tp = new TitledPane();
        tp.setLayoutX(10);
        tp.setLayoutY(10);
        tp.setCursor(Cursor.CROSSHAIR);
        tp.setStyle("-fx-border-width: 4pt;-fx-border-color:olive;");
        tp.setTooltip(new Tooltip("Мои изображения"));

        Label label = new Label("Изображения");
        label.setCursor(Cursor.CLOSED_HAND);
        label.setPrefSize(300, 30);
        label.setStyle(
           "-fx-font: bold italic 16pt Georgia;-fx-text-fill:black;-fx-background-color:#e6e6fa;");
        label.setAlignment(Pos.CENTER);
        tp.setGraphic(label);
        
        Image image = new Image(getClass().getResource("image.JPG").toString());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        tp.setContent(imageView);
        
        root.getChildren().add(tp);
    }
}
