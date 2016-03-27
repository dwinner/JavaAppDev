package jsobjecttest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 *
 * @author oracle_pr1
 */
public class JSObjectTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 200, Color.BEIGE);
        Button btn = new Button();
        btn.setLayoutX(35);
        btn.setLayoutY(80);
        btn.setText("Открыть изображения");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold 16pt Georgia;"
           + "-fx-text-fill: white;"
           + "-fx-background-color: #a0522d;"
           + "-fx-border-width: 3px; "
           + "-fx-border-color:#f4a460 #800000 #800000 #f4a460;");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                JSObject js = getHostServices().getWebContext();
                String baseURI = getHostServices().getCodeBase();
                if (js != null)
                {
                    try
                    {
                        js.eval("window.location.replace('"
                            + baseURI
                            + "JavaFXApplicationLiveConnect.html');");
                    }
                    catch (Exception ex)
                    {
                    }
                }
            }
        });
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
