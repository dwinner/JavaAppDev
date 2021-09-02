/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
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
        textFieldW.setPrefWidth(300);
        textFieldW.setMaxHeight(30);        
        textFieldW.setEditable(true);  
        
        Label labelS = new Label("со словосочетанием:"); 
        labelS.setPrefSize(150,40); 
        labelS.setAlignment(Pos.CENTER_LEFT);
        labelS.setWrapText(true);
        
        TextField textFieldS= new TextField();        
        textFieldS.setPrefWidth(300);
        textFieldS.setMaxHeight(30);            
        textFieldS.setEditable(true);  
        
        Label labelNF = new Label("Не показывать страницы"); 
        labelNF.setPrefSize(200,20); 
        labelNF.setAlignment(Pos.CENTER_LEFT);
        
        Label labelNW = new Label("c любым из слов:"); 
        labelNW.setPrefSize(100,40); 
        labelNW.setAlignment(Pos.CENTER_LEFT);
        labelNW.setWrapText(true);
        
        TextField textFieldNW= new TextField();        
        textFieldNW.setPrefWidth(300);
        textFieldNW.setMaxHeight(30);           
        textFieldNW.setEditable(true);  
          
        FlowPane fpH=new FlowPane();
        fpH.setLayoutX(20);
        fpH.setLayoutY(20);
        fpH.setCursor(Cursor.TEXT);
        fpH.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");
        fpH.setOrientation(Orientation.HORIZONTAL);
        fpH.setPrefWrapLength(410);
        fpH.setHgap(10);
        fpH.setVgap(10);
        
        FlowPane fpF=new FlowPane();
        fpF.setOrientation(Orientation.HORIZONTAL);
        fpF.setPrefWrapLength(500);
        fpF.setHgap(10);
        fpF.setVgap(10);
        fpF.getChildren().addAll(labelW, textFieldW, labelS, textFieldS, labelNF);
        
        fpH.getChildren().addAll(labelH, textFieldH, labelF, fpF, labelNW, textFieldNW );   
        
        root.getChildren().addAll(fpH);        
    }
}
