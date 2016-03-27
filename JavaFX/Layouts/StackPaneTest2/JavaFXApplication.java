/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {     
  
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();        
        Scene scene = new Scene(root, 300, 200, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();        
        
        CheckBox cb=new CheckBox();
        cb.setLayoutX(20);
        cb.setLayoutY(50);
        
       // cb.setText("Получать рассылку");
       // cb.setStyle("-fx-font: bold 14pt Arial;");
       
        Rectangle rec= new Rectangle(200,40,Color.OLIVE);  
        rec.setArcWidth(20);
        rec.setArcHeight(10);
        Text text=new Text("Получать рассылку");
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);  
        text.setStyle("-fx-font: bold 14pt Arial; -fx-fill:white;");
        text.setEffect(effect);
        
        StackPane sp=new StackPane();
        sp.getChildren().addAll(rec, text);
        sp.setLayoutX(60);
        sp.setLayoutY(40);
        
        root.getChildren().add(cb); 
        root.getChildren().add(sp);
    }
}
