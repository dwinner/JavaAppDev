package javafxapplication;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
              
        Image im = new Image(this.getClass().getResource("image.JPG").toString());
        System.out.println("Height: "+im.getHeight()+"Width: "+im.getWidth());
        ImageView imv = new ImageView(im);
        //imv.setViewport(new Rectangle2D(100,100,500,500));
        imv.setPreserveRatio(true);
        imv.setFitHeight(200);
        imv.setFitWidth(200);
        imv.setX(10);
        imv.setY(50);
        imv.setLayoutX(50);
        imv.setLayoutY(50);
        imv.setSmooth(true);
        imv.setBlendMode(BlendMode.DARKEN);
        imv.setCursor(Cursor.TEXT);
        //imv.setClip(new Rectangle(100,100));
        DropShadow effect = new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);  
        imv.setEffect(effect);
        imv.setOpacity(0.5);
        
        root.getChildren().add(imv);
    }
}
