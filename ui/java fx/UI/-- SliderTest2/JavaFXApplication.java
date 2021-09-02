/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
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
        Scene scene = new Scene(root, 500, 500, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
       final ImageView[] imv=new ImageView[5];
        for(int i=0;i<=4;i++){
        Image im=new Image(this.getClass().getResource("image"+i+".gif").toString());
        imv[i]=new ImageView(im);
        imv[i].setFitHeight(200);
        imv[i].setFitWidth(200); 
        imv[i].setPreserveRatio(true);
        imv[i].setLayoutX(70);
        imv[i].setLayoutY(60);         
        root.getChildren().add(imv[i]);        
        }  
        imv[0].toFront();
        
        Slider slider=new Slider();
        slider.setLayoutX(20);
        slider.setLayoutY(20);
        slider.setCursor(Cursor.CROSSHAIR);
        slider.setPrefHeight(300);        
        slider.setBlockIncrement(1);       
        slider.setMajorTickUnit(1);
        slider.setMax(4);
        slider.setMin(0);
        slider.setMinorTickCount(0);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);         
        slider.valueProperty().addListener(new ChangeListener<Number>() {
public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
        imv[new_val.intValue()].toFront();         
            }
        });
        
       root.getChildren().add(slider); 
    }
}
