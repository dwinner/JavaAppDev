/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
  
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();      
              
        Text text = new Text();
        text.setLayoutX(0);
        text.setLayoutY(150);
        text.setCursor(Cursor.TEXT);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        text.setEffect(effect);
        text.setText("JavaFX 2.0");
        text.setFill(Color.KHAKI);
        text.setStroke(Color.OLIVE);
        text.setStrokeWidth(3);        
        text.setStrokeLineJoin(StrokeLineJoin.ROUND);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 40));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(300);
        text.setTextOrigin(VPos.BOTTOM);        
        
        root.getChildren().add(text);
    }
}
