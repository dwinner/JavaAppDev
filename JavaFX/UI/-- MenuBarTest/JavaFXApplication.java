package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXApplication extends Application
{       
    public static void main(String[] args)
    {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        MenuBar menuBar = new MenuBar();        
        menuBar.setLayoutX(10);
        menuBar.setLayoutY(10); 
        menuBar.setBlendMode(BlendMode.HARD_LIGHT);        
        menuBar.setCursor(Cursor.CLOSED_HAND);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(5);
        effect.setOffsetY(5);
        menuBar.setEffect(effect); 
        menuBar.setStyle("-fx-base:skyblue;-fx-border-width:4pt;-fx-border-color:navy;-fx-font:bold 16pt Georgia;");
        menuBar.setPrefSize(250, 50);
        
        Menu menuF = new Menu("Файл");        
        MenuItem menuItemP = new MenuItem("Печать");        
        menuItemP.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        menuItemP.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        menuItemP.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                System.out.println("Идет печать...");
            }
        });
        
        SeparatorMenuItem sep = new SeparatorMenuItem();        
        RadioMenuItem radioItemY = new RadioMenuItem("С номерами страниц");
        radioItemY.setStyle("-fx-text-fill:navy;-fx-font:bold italic 12pt Georgia;");
        ToggleGroup tgroup=new ToggleGroup();
        radioItemY.setToggleGroup(tgroup);
        radioItemY.setSelected(true);
        RadioMenuItem radioItemN = new RadioMenuItem("Без номеров страниц");
        radioItemN.setStyle("-fx-text-fill:navy;-fx-font:bold italic 12pt Georgia;");
        radioItemN.setToggleGroup(tgroup);
        CheckMenuItem checkMenuItem=new CheckMenuItem("Улучшенное качество");
        checkMenuItem.setSelected(true);
        checkMenuItem.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        menuF.getItems().addAll(menuItemP, radioItemY, radioItemN, sep, checkMenuItem);
        
        Menu menuE = new Menu("Правка");
        MenuItem menuItemCut = new MenuItem("Вырезать");
        menuItemCut.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        MenuItem menuItemCopy = new MenuItem("Копировать");
        menuItemCopy.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        MenuItem menuItemPaste = new MenuItem("Вставить");
        menuItemPaste.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        menuE.getItems().addAll(menuItemCut, menuItemCopy, menuItemPaste);
        
        Menu menuV = new Menu("Вид");
        MenuItem menuItemS = new MenuItem("Масштаб");
        menuItemS.setStyle("-fx-text-fill:navy;-fx-font:bold italic 14pt Georgia;");
        menuV.getItems().addAll(menuItemS);
        
        menuBar.getMenus().addAll(menuF, menuE, menuV);
        root.getChildren().add(menuBar);
    }
}
