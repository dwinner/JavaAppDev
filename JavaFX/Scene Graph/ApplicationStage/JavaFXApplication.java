/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JavaFXApplication extends Application {
    
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {        
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Тестирование GUI-компонентов");       
        Group root = new Group();           
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);        
        stage.setScene(scene);       
            
        //stage.setFullScreen(true);
        //stage.setIconified(true);
       //stage.setResizable(false);        
        
        Text text=new Text("JavaFX 2.0");
        text.setLayoutX(50);
        text.setLayoutY(100);
        text.setFill(Color.BLUE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
       
        stage.show();           
        root.getChildren().addAll(text);
        
    }
}
