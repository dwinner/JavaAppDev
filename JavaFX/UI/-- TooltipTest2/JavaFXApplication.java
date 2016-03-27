/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
 
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Tooltip tooltip=new Tooltip();
        tooltip.setText("Кнопка\nотправки данных");        
        tooltip.setPrefSize(250,100);      
        tooltip.setStyle("-fx-font: bold italic 14pt Georgia;-fx-text-fill: black;-fx-background-color:bisque;");
        Image im=new Image(this.getClass().getResource("type.gif").toString());
        ImageView imv=new ImageView(im);
        imv.setFitHeight(70);
        imv.setFitWidth(70); 
        imv.setPreserveRatio(true);
        tooltip.setGraphic(imv);
        tooltip.setContentDisplay(ContentDisplay.RIGHT);
        tooltip.setTextAlignment(TextAlignment.LEFT);         
                      
        Button btnON = new Button();        
        btnON.setLayoutX(20);
        btnON.setLayoutY(50);
        btnON.setText("Отправить");
        btnON.setStyle("-fx-font: bold 18pt Georgia;" );       
        btnON.setPrefSize(200,30);
        btnON.setTooltip(tooltip);
        //Tooltip.install(btnON, tooltip);      
        
        root.getChildren().add(btnON);        
    }
}
