package pkg02.stylingwithcsstest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * Оформление компонентов средствами CSS
 * @author 
 */
public class StylingWithCSSTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        HBox root = new HBox(10.0);
        root.setPadding(new Insets(20, 20, 20, 20));
        
        ToggleButton tb1 = new ToggleButton("First");
        ToggleButton tb2 = new ToggleButton("Second");
        ToggleButton tb3 = new ToggleButton("I don't know");
        // Для tb3 переопределим стиль
        tb3.setStyle("-fx-base: #ed1c24");
        root.getChildren().addAll(tb1, tb2, tb3);
        
        // Создадим скин и свяжем с ним таблицу стилей
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add("controlStyle.css");
        
        primaryStage.setTitle("CSS Applying");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
