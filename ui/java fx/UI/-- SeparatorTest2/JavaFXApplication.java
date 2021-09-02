/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
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
        
        Image imF=new Image(this.getClass().getResource("imageF.gif").toString());
        ImageView imvF=new ImageView(imF);
        imvF.setFitHeight(100);
        imvF.setFitWidth(100); 
        imvF.setLayoutX(20);
        imvF.setLayoutY(20);
        
        Hyperlink hlinkF = new Hyperlink("Программирование в 1С\n для каждого на примерах");
        hlinkF.setLayoutX(150);
        hlinkF.setLayoutY(20);           
        hlinkF.setCursor(Cursor.CLOSED_HAND);                             
        hlinkF.setPrefSize(250,100); 
        hlinkF.setStyle("-fx-text-fill:blue;-fx-font: bold italic 14pt Georgia;");     
        hlinkF.setAlignment(Pos.CENTER);       
        hlinkF.setWrapText(true);
        
        Image imH=new Image(this.getClass().getResource("imageH.gif").toString());
        ImageView imvH=new ImageView(imH);
        imvH.setFitHeight(100);
        imvH.setFitWidth(100); 
        imvH.setLayoutX(20);
        imvH.setLayoutY(200);
        
        Hyperlink hlinkH = new Hyperlink("Все новшества стандарта HTML5\n в книге Марка Пилгрима 'Погружение в HTML5'");
        hlinkH.setLayoutX(150);
        hlinkH.setLayoutY(200);           
        hlinkH.setCursor(Cursor.CLOSED_HAND);                             
        hlinkH.setPrefSize(300,100); 
        hlinkH.setStyle("-fx-text-fill:blue;-fx-font: bold italic 14pt Georgia;");     
        hlinkH.setAlignment(Pos.CENTER);       
        hlinkH.setWrapText(true);
        
        Separator sepH=new Separator();
        sepH.setLayoutX(10);
        sepH.setLayoutY(160);
        sepH.setPrefWidth(400);        
        sepH.setOrientation(Orientation.HORIZONTAL);
        sepH.setStyle("-fx-background-color:blue;-fx-background-insets:1;");
        
        Separator sepV=new Separator();
        sepV.setLayoutX(10);
        sepV.setLayoutY(10);
        sepV.setPrefHeight(300);
        sepV.setOrientation(Orientation.VERTICAL);
        sepV.setHalignment(HPos.RIGHT);
        sepV.setStyle("-fx-background-color:blue;");
        
       root.getChildren().addAll(hlinkF,imvF,hlinkH,imvH,sepH,sepV); 
    }
}
