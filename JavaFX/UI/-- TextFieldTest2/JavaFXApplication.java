/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
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
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final TextField textField = new TextField();
        textField.setLayoutX(10);
        textField.setLayoutY(10); 
        textField.setCursor(Cursor.TEXT);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        textField.setEffect(effect);
        textField.setStyle("-fx-background-radius:10;-fx-border-radius:8;-fx-background-color:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-font-weight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-font-style:italic");
        textField.setPrefSize(230, 40);
        textField.setTooltip(new Tooltip("Введите ФИО"));       
        textField.setEditable(true);  
        textField.setPromptText("Фамилия Имя Отчество");        
        textField.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
       System.out.println( textField.getText());
    }
});
    
        Button btn = new Button();        
        btn.setLayoutX(20);
        btn.setLayoutY(80);
        btn.setText("Отправить");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold italic 14pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btn.setPrefSize(180,30);       
        btn.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
       System.out.println( textField.getText());
    }
});
        root.getChildren().add(textField);
        root.getChildren().add(btn);  
    }
}
