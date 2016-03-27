package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
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
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        MenuButton btn = new MenuButton("Правка");
        btn.setLayoutX(20);
        btn.setLayoutY(20);             
        btn.setBlendMode(BlendMode.HARD_LIGHT);
        btn.setCursor(Cursor.CLOSED_HAND);  
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);        
        btn.setEffect(effect);    
        btn.setPrefSize(200,80); 
        btn.setTooltip(new Tooltip("Это кнопка контекстного меню"));
        Image im = new Image(this.getClass().getResource("image.jpg").toString());
        ImageView imv = new ImageView(im);
        imv.setFitHeight(50);
        imv.setFitWidth(50);         
        btn.setGraphic(imv);
        btn.setStyle("-fx-font: bold italic 18pt Georgia;");       
        btn.setAlignment(Pos.CENTER);
        btn.setContentDisplay(ContentDisplay.LEFT);
        btn.setTextAlignment(TextAlignment.CENTER);
        btn.setGraphicTextGap(10);
        btn.setWrapText(true); 
        btn.setPopupSide(Side.RIGHT);
        MenuItem menuItemCut = new MenuItem("Вырезать");
        menuItemCut.setStyle("-fx-font:bold italic 14pt Times;" ); 
        menuItemCut.setAccelerator(KeyCombination.keyCombination("Ctrl+U"));        
        
        menuItemCut.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                System.out.println("Вырезаю");
            }
        });
        
        MenuItem menuItemCopy = new MenuItem("Копировать");
        menuItemCopy.setStyle("-fx-font:bold italic 14pt Times;" ); 
        menuItemCopy.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        menuItemCopy.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                System.out.println("Копирую");
            }
        });
        
        MenuItem menuItemPaste = new MenuItem("Вставить");
        menuItemPaste.setStyle("-fx-font:bold italic 14pt Times;" );
        menuItemPaste.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        menuItemPaste.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                System.out.println("Вставляю");
            }
        });
        
        btn.getItems().addAll(menuItemCut,menuItemCopy,menuItemPaste );
        root.getChildren().add(btn);  
    }
}
