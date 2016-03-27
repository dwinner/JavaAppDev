package javafxapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class JavaFXApplication extends Application
{
    
    Popup popup;
    Stage primaryStage;
 
    public static void main(String[] args)
    {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage stage)
    {
        primaryStage = stage;
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        popup = new Popup();  
        popup.setAutoHide(true);
        Group rootPopup = new Group();       
        rootPopup.setCursor(Cursor.CROSSHAIR);
        DropShadow effect = new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        rootPopup.setEffect(effect);             
        Image im = new Image(this.getClass().getResource("image.jpeg").toString());
        ImageView imv=new ImageView(im);
        imv.setFitHeight(70);
        imv.setFitWidth(70); 
        imv.setPreserveRatio(true);
        Image imA = new Image(getClass().getResource("imageA.jpeg").toString());
        ImageView imvA = new ImageView(imA);
        imvA.setFitHeight(70);
        imvA.setFitWidth(70); 
        imvA.setPreserveRatio(true);
        imvA.setLayoutX(80);
        Image imS = new Image(getClass().getResource("imageS.jpeg").toString());
        ImageView imvS = new ImageView(imS);
        imvS.setFitHeight(70);
        imvS.setFitWidth(70); 
        imvS.setPreserveRatio(true);
        imvS.setLayoutX(160);
        rootPopup.getChildren().addAll(imv,imvA,imvS);
        popup.getContent().addAll(rootPopup);          
       
        Button btn = new Button();        
        btn.setLayoutX(20);
        btn.setLayoutY(50);
        btn.setText("Внимание!");
        btn.setStyle("-fx-font: bold 18pt Georgia;" );       
        btn.setPrefSize(200,30);
        btn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                popup.setX(event.getScreenX());
                popup.setY(event.getScreenY());
                popup.show(primaryStage);    
            }
        });
       
        root.getChildren().add(btn);        
    }
}
