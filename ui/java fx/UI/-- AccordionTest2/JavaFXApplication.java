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
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
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
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);        
        primaryStage.setScene(scene);
        primaryStage.show();        
        
        TitledPane tpM =new TitledPane();             
        tpM.setTooltip(new Tooltip("Горы"));         
        Label labelM = new Label("Горы"); 
        labelM.setCursor(Cursor.CLOSED_HAND);
        labelM.setPrefSize(250,30); 
        labelM.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:black;-fx-background-color:#e6e6fa;");       
        labelM.setAlignment(Pos.CENTER);
        tpM.setGraphic(labelM);         
        Image imM=new Image(this.getClass().getResource("imageM.JPG").toString());
        ImageView imvM=new ImageView(imM);
        imvM.setPreserveRatio(true);
        imvM.setFitHeight(250);
        imvM.setFitWidth(250);         
        tpM.setContent(imvM);
        
        TitledPane tpC =new TitledPane();              
        tpC.setTooltip(new Tooltip("Космос"));        
        Label labelC = new Label("Космос"); 
        labelC.setCursor(Cursor.CLOSED_HAND);
        labelC.setPrefSize(250,30); 
        labelC.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:black;-fx-background-color:#e6e6fa;");       
        labelC.setAlignment(Pos.CENTER);
        tpC.setGraphic(labelC);        
        Image imC=new Image(this.getClass().getResource("imageC.jpg").toString());
        ImageView imvC=new ImageView(imC);
        imvC.setPreserveRatio(true);
        imvC.setFitHeight(250);
        imvC.setFitWidth(250);         
        tpC.setContent(imvC);
        
        Accordion acc=new Accordion(); 
        acc.setLayoutX(10);
        acc.setLayoutY(10);
        acc.setCursor(Cursor.CROSSHAIR);   
        acc.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");         
        acc.getPanes().addAll(tpM,tpC);
        //acc.setExpandedPane(tpM);        
        
        root.getChildren().add(acc);        
    }
}
