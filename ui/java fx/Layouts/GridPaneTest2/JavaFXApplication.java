/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.util.*;
import javafx.scene.*;
import javafx.stage.*;


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
          
        GridPane gpH=new GridPane();
        gpH.setLayoutX(20);
        gpH.setLayoutY(20);
        gpH.setCursor(Cursor.TEXT);        
        gpH.setStyle("-fx-font:bold 14pt Arial;-fx-text-fill:#a0522d;");
        gpH.setVgap(10);
        //gpH.setGridLinesVisible(true);
        
        GridPane gpF=new GridPane();
        gpF.addColumn(1, labelW, labelS);
        gpF.addColumn(2, textFieldW, textFieldS);
        gpF.setVgap(10);
        
        GridPane gpNF=new GridPane();
        gpNF.addRow(1, labelNW, textFieldNW);
        gpNF.setHgap(20);
        
        gpH.addColumn(1,labelH, textFieldH, labelF, gpF, labelNF, gpNF);
        
        root.getChildren().addAll(gpH);        
    }
}
