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
import javafx.scene.layout.BorderPane;
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
          
        BorderPane bpH=new BorderPane();
        bpH.setLayoutX(10);
        bpH.setLayoutY(10);
        bpH.setCursor(Cursor.TEXT);        
        bpH.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");  
        bpH.setTop(labelH);  
        bpH.setLeft(textFieldH);         
        
        BorderPane bpW=new BorderPane();
        bpW.setLayoutX(10);
        bpW.setLayoutY(80);
        bpW.setCursor(Cursor.TEXT);        
        bpW.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");    
        bpW.setTop(labelF);
        bpW.setLeft(labelW);
        bpW.setRight(textFieldW);        
        
        BorderPane bpS=new BorderPane();
        bpS.setLayoutX(10);
        bpS.setLayoutY(150);
        bpS.setCursor(Cursor.TEXT);        
        bpS.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");       
        bpS.setLeft(labelS);
        bpS.setRight(textFieldS);
        
        BorderPane bpNF=new BorderPane();
        bpNF.setLayoutX(10);
        bpNF.setLayoutY(220);
        bpNF.setCursor(Cursor.TEXT);        
        bpNF.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");  
        bpNF.setTop(labelNF);
        bpNF.setLeft(labelNW);
        bpNF.setRight(textFieldNW);
        
        root.getChildren().addAll(bpH,bpW,bpS,bpNF);        
    }
}
