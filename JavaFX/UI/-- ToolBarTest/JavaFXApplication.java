package javafxapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class JavaFXApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage stage)
    {
        final Stage primaryStage = stage;
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        Label label=new Label("Google:");
        label.setStyle("-fx-font:bold 14pt Arial;");
        label.setPrefSize(100,20);
        label.setAlignment(Pos.CENTER);
        
        TextField textField=new TextField();
        textField.setCursor(Cursor.TEXT);
        textField.setPrefSize(200,20);
        
        Button btnS=new Button("Поиск");
        btnS.setStyle("-fx-font:bold 12pt Arial");
        
        Separator sep=new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setPrefHeight(20);
        
        Button btnA=new Button("Расширенный поиск");
        btnA.setStyle("-fx-font:bold 12pt Arial");
        btnA.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            { 
                Popup pop=new Popup();
                pop.setAutoHide(true);
                pop.getScene().setFill(Color.BEIGE);
                
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
                ap.setPrefSize(500, 350);
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
                pop.getContent().addAll(ap);
                pop.setX(event.getScreenX()-400);
                pop.setY(event.getScreenY()+50);
                pop.show(primaryStage);    
            }
        });
        ToolBar toolBar=new ToolBar();
        toolBar.setLayoutX(20);
        toolBar.setLayoutY(20); 
        toolBar.setBlendMode(BlendMode.HARD_LIGHT);
        toolBar.setCursor(Cursor.HAND);
        toolBar.setOrientation(Orientation.HORIZONTAL);
        toolBar.getItems().addAll(label, textField, btnS, sep, btnA);
        
        root.getChildren().add(toolBar);
    }
}
