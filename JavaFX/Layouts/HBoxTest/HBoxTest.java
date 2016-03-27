package pkg2.hboxtest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ƒемонстраци€ итспользовани€ расположени€ HBox
 * @author JavaFX Development Group
 */
public class HBoxTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("HBox Pane Sample");
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699");
        
        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);
        
        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
        
        BorderPane border = new BorderPane();
        border.setTop(hbox);
        
        primaryStage.setScene(new Scene(border, 640, 480));
        primaryStage.show();
    }
}
