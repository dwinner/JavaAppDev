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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
    RadioButton btnOn;
    RadioButton btnOf;
    Scene scene;
    
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        scene = new Scene(root, 430, 300, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
        ToggleGroup tgroup=new ToggleGroup();
        btnOn = new RadioButton("Mozilla Firefox");        
        btnOn.setLayoutX(20);
        btnOn.setLayoutY(20);         
        btnOn.setBlendMode(BlendMode.MULTIPLY);
        btnOn.setCursor(Cursor.CLOSED_HAND);          
        btnOn.setPrefSize(200,80); 
        btnOn.setTooltip(new Tooltip("Это кнопка выбора Mozilla Firefox"));
        Image imOn=new Image(this.getClass().getResource("imageOn.jpg").toString());
        ImageView imvOn=new ImageView(imOn);
        imvOn.setFitHeight(50);
        imvOn.setFitWidth(50);         
        btnOn.setGraphic(imvOn);
        btnOn.setStyle("-fx-font: bold italic 16pt Georgia;");          
        btnOn.setAlignment(Pos.CENTER);
        btnOn.setContentDisplay(ContentDisplay.RIGHT);
        btnOn.setTextAlignment(TextAlignment.CENTER);
        btnOn.setGraphicTextGap(10);
        btnOn.setWrapText(true);        
        btnOn.setToggleGroup(tgroup);
        btnOn.requestFocus();
        btnOn.selectedProperty().addListener(new javafx.beans.value.ChangeListener<Boolean>() {
        public void changed(javafx.beans.value.ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
        if (new_val.equals(Boolean.TRUE))               
        scene.setFill(Color.web("#fff8dc"));
        }
    });
        
        btnOf = new RadioButton("Internet Explorer");        
        btnOf.setLayoutX(20);
        btnOf.setLayoutY(100); 
        btnOf.setBlendMode(BlendMode.MULTIPLY);
        btnOf.setCursor(Cursor.CLOSED_HAND);        
        btnOf.setPrefSize(200,80); 
        btnOf.setTooltip(new Tooltip("Это кнопка выбора Internet Explorer"));
        Image imOf=new Image(this.getClass().getResource("imageOf.jpg").toString());
        ImageView imvOf=new ImageView(imOf);
        imvOf.setFitHeight(50);
        imvOf.setFitWidth(50);         
        btnOf.setGraphic(imvOf);
        btnOf.setStyle("-fx-font: bold italic 18pt Georgia;" );           
        btnOf.setAlignment(Pos.CENTER);
        btnOf.setContentDisplay(ContentDisplay.RIGHT);
        btnOf.setTextAlignment(TextAlignment.CENTER);
        btnOf.setGraphicTextGap(10);
        btnOf.setWrapText(true); 
        btnOf.setToggleGroup(tgroup); 
        btnOf.selectedProperty().addListener(new javafx.beans.value.ChangeListener<Boolean>() {
        public void changed(javafx.beans.value.ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
        if (new_val.equals(Boolean.TRUE))             
        scene.setFill(Color.web("#99ffff"));
        }      
    });      
        
        root.getChildren().add(btnOn);
        root.getChildren().add(btnOf);
    }
}
