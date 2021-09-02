package pkg14.scrollpanetest;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Панель с прокруткой.
 * @author oracle_pr1
 */
public class ScrollPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Панель с прокруткой");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();

        Button btn = new Button();
        btn.setTranslateY(20);
        btn.setText("Отправить");
        btn.setCursor(Cursor.CROSSHAIR);
        btn.setStyle(
           "-fx-font: bold italic 14pt Georgia;"
           + "-fx-text-fill: white;-fx-background-color: #a0522d;"
           + "-fx-border-width: 3px;"
           + "-fx-border-color:#f4a460 #800000 #800000 #f4a460;");
        btn.setPrefSize(200, 30);

        ScrollPane sp = new ScrollPane();
        sp.setLayoutX(10);
        sp.setLayoutY(10);
        sp.setCursor(Cursor.CLOSED_HAND);
        DropShadow effect = new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        sp.setEffect(effect);
        sp.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");
        sp.setPrefSize(150, 100);
        sp.setTooltip(new Tooltip("Отправка данных"));
        sp.setContent(btn);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //sp.setFitToWidth(true);        
        sp.setPannable(true);
        sp.setPrefViewportHeight(300);
        sp.setPrefViewportWidth(300);

        root.getChildren().add(sp);
    }
}
