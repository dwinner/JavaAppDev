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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        Scene scene = new Scene(root, 600, 400, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();        
             
        Label labelH = new Label("Расширенный поиск"); 
        labelH.setPrefSize(400,30); 
        labelH.setAlignment(Pos.CENTER);
        
        TextField textFieldH= new TextField();        
        textFieldH.setPrefSize(400, 30);        
        textFieldH.setEditable(true);  
        
        Label labelF = new Label("Найти страницы"); 
        labelF.setPrefSize(200,20); 
        labelF.setAlignment(Pos.CENTER_LEFT);
        
        Label labelW = new Label("со словами:"); 
        labelW.setPrefSize(100,40); 
        labelW.setAlignment(Pos.CENTER_LEFT);
        labelW.setWrapText(true);
        
        TextField textFieldW= new TextField();        
        textFieldW.setPrefSize(300, 30);        
        textFieldW.setEditable(true);  
        
        Label labelS = new Label("со словосочетанием:"); 
        labelS.setPrefSize(150,40); 
        labelS.setAlignment(Pos.CENTER_LEFT);
        labelS.setWrapText(true);
        
        TextField textFieldS= new TextField();        
        textFieldS.setPrefSize(300, 30);        
        textFieldS.setEditable(true);  
        
        Label labelNF = new Label("Не показывать страницы"); 
        labelNF.setPrefSize(200,20); 
        labelNF.setAlignment(Pos.CENTER_LEFT);
        
        Label labelNW = new Label("c любым из слов:"); 
        labelNW.setPrefSize(100,40); 
        labelNW.setAlignment(Pos.CENTER_LEFT);
        labelNW.setWrapText(true);
        
        TextField textFieldNW= new TextField();        
        textFieldNW.setPrefSize(300, 30);        
        textFieldNW.setEditable(true);  
          
        AnchorPane ap=new AnchorPane();
        ap.setCursor(Cursor.TEXT);        
        ap.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");
        ap.getChildren().addAll(labelH, textFieldH, labelF, labelW, textFieldW, labelS, textFieldS, labelNF, labelNW, textFieldNW );
        AnchorPane.setTopAnchor(labelH, 10.0);
        AnchorPane.setTopAnchor(textFieldH, 50.0);
        AnchorPane.setLeftAnchor(textFieldH, 10.0);
        AnchorPane.setTopAnchor(labelF, 100.0);
        AnchorPane.setLeftAnchor(labelF, 10.0);
        AnchorPane.setTopAnchor(labelW, 130.0);
        AnchorPane.setLeftAnchor(labelW, 10.0);
        AnchorPane.setTopAnchor(textFieldW, 140.0);
        AnchorPane.setLeftAnchor(textFieldW, 130.0);
        AnchorPane.setTopAnchor(labelS, 180.0);
        AnchorPane.setLeftAnchor(labelS, 10.0);
        AnchorPane.setTopAnchor(textFieldS, 190.0);
        AnchorPane.setLeftAnchor(textFieldS, 180.0);
        AnchorPane.setTopAnchor(labelNF, 230.0);
        AnchorPane.setLeftAnchor(labelNF, 10.0);
        AnchorPane.setTopAnchor(labelNW, 260.0);
        AnchorPane.setLeftAnchor(labelNW, 10.0);
        AnchorPane.setTopAnchor(textFieldNW, 270.0);
        AnchorPane.setLeftAnchor(textFieldNW, 130.0);        
        
        root.getChildren().add(ap);        
    }
}
