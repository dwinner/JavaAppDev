package fxmlexample;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLExample extends Application
{
    private final static int DEFAULT_SCENE_WIDTH = 640;
    private final static int DEFAULT_SCENE_HEIGHT = 480;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("FXML Example");

        Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"),
            ResourceBundle.getBundle("fxmlexample.fxml_example"));

        Scene scene = new Scene(root, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        // Добавляем CSS
        scene.getStylesheets().add("fxmlexample/fxmlstylesheet.css");
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
