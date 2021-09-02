/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
        Label limg = new Label();
        limg.setLayoutX(10);
        limg.setLayoutY(10); 
        limg.setCursor(Cursor.CROSSHAIR);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);
        limg.setEffect(effect);
        limg.setPrefSize(200, 200);
        limg.setTooltip(new Tooltip("Это горы там, где я не бывал"));
        Image im=new Image(this.getClass().getResource("image.JPG").toString());
        ImageView imv=new ImageView(im);
        imv.setPreserveRatio(true);
        imv.setFitHeight(200);
        imv.setFitWidth(200);  
        limg.setGraphic(imv);            
        limg.setAlignment(Pos.TOP_LEFT);
        
        Label label = new Label("Горы");
        label.setLayoutX(45);
        label.setLayoutY(155); 
        label.setScaleX(1.5);
        label.setCursor(Cursor.TEXT);  
        label.setPrefSize(133,30); 
        label.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fx-background-color:lightgrey;");       
        label.setGraphic(null);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);         
        
        root.getChildren().add(label);
        root.getChildren().add(limg);
    }
}
