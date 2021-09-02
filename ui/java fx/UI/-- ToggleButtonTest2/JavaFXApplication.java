/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
    ToggleButton btnOn;
    ToggleButton btnOf;
    ImageView imvOn;
    ImageView imvOf;
    Scene scene;
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        scene = new Scene(root, 430, 300, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        ToggleGroup tgroup=new ToggleGroup();        
        btnOn = new ToggleButton("JavaFX");
        btnOn.setLayoutX(10);
        btnOn.setLayoutY(10);             
        btnOn.setCursor(Cursor.CLOSED_HAND);  
        DropShadow effect=new DropShadow();
        effect.setOffsetX(5);
        effect.setOffsetY(5);        
        btnOn.setEffect(effect);    
        btnOn.setPrefSize(200,80); 
        btnOn.setTooltip(new Tooltip("Это кнопка выбора платформы JavaFX"));
        Image imOn=new Image(this.getClass().getResource("imageOn.jpg").toString());
        imvOn=new ImageView(imOn);
        imvOn.setFitHeight(50);
        imvOn.setFitWidth(50);         
        btnOn.setGraphic(imvOn);
        btnOn.setStyle("-fx-base:#9900ff;-fx-font: bold italic 16pt Georgia;-fx-text-fill:white;");  
        btnOn.selectedProperty().addListener(new javafx.beans.value.ChangeListener<Boolean>() {
        public void changed(javafx.beans.value.ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
        if (new_val.equals(Boolean.TRUE)){  
        btnOn.setStyle("-fx-base:#9900ff;-fx-font: bold italic 16pt Georgia;-fx-text-fill:white;" );
        imvOn.setFitHeight(40);
        imvOn.setFitWidth(40); 
        scene.setFill(Color.web("#ffff00"));
        System.out.println("Использую JavaFX");
        }
        if (new_val.equals(Boolean.FALSE)){            
        btnOn.setStyle("-fx-base:#9900ff;-fx-font: bold italic 18pt Georgia;-fx-text-fill:white;" );
        imvOn.setFitHeight(50);
        imvOn.setFitWidth(50); 
        scene.setFill(Color.LIGHTGREEN);
        }      
        }
    });
        btnOn.setAlignment(Pos.CENTER);
        btnOn.setContentDisplay(ContentDisplay.LEFT);
        btnOn.setTextAlignment(TextAlignment.CENTER);
        btnOn.setGraphicTextGap(20);
        btnOn.setWrapText(true); 
        btnOn.setToggleGroup(tgroup);
        btnOn.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {        
        System.out.println("JavaFX:"+btnOn.selectedProperty().getValue());
        System.out.println("Silverlight:"+btnOf.selectedProperty().getValue());
    }
});
        
        btnOf = new ToggleButton("Silverlight");
        btnOf.setLayoutX(220);
        btnOf.setLayoutY(10);             
        btnOf.setCursor(Cursor.CLOSED_HAND);               
        btnOf.setEffect(effect);    
        btnOf.setPrefSize(200,80); 
        btnOf.setTooltip(new Tooltip("Это кнопка выбора платформы Silverlight"));
        Image imOf=new Image(this.getClass().getResource("imageOf.jpg").toString());
        imvOf=new ImageView(imOf);
        imvOf.setFitHeight(50);
        imvOf.setFitWidth(50);         
        btnOf.setGraphic(imvOf);
        btnOf.setStyle("-fx-base:#ff0000;-fx-font: bold italic 18pt Georgia;-fx-text-fill:white;" ); 
        btnOf.selectedProperty().addListener(new javafx.beans.value.ChangeListener<Boolean>() {
        public void changed(javafx.beans.value.ObservableValue<? extends Boolean> ov,
            Boolean old_val, Boolean new_val) {
        if (new_val.equals(Boolean.TRUE)) { 
        btnOf.setStyle("-fx-base:#ff0000;-fx-font: bold italic 16pt Georgia;-fx-text-fill:white;" );
        imvOf.setFitHeight(40);
        imvOf.setFitWidth(40); 
        scene.setFill(Color.web("#660000"));
        System.out.println("Использую Silverlight");
        }
       if (new_val.equals(Boolean.FALSE)) {  
        btnOf.setStyle("-fx-base:#ff0000;-fx-font: bold italic 18pt Georgia;-fx-text-fill:white;" );
        imvOf.setFitHeight(50);
        imvOf.setFitWidth(50);  
        scene.setFill(Color.LIGHTGREEN);
        }
        }      
    });
        
        btnOf.setAlignment(Pos.CENTER);
        btnOf.setContentDisplay(ContentDisplay.LEFT);
        btnOf.setTextAlignment(TextAlignment.CENTER);
        btnOf.setGraphicTextGap(20);
        btnOf.setWrapText(true); 
        btnOf.setToggleGroup(tgroup);
        btnOf.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {        
        System.out.println("JavaFX:"+btnOn.selectedProperty().getValue());
        System.out.println("Silverlight:"+btnOf.selectedProperty().getValue());
    }
});
        root.getChildren().add(btnOn);
        root.getChildren().add(btnOf);
    }
}
