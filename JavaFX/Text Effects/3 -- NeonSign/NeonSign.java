package pkg3.neonsign;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Комбинирование эффектов текста
 * @author JavaFx
 */
public class NeonSign extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Text Effects: Neon Sign");
        double width = 640;
        double height = 480;
        Group root = new Group();
        Scene scene = new Scene(root, width, height);

        final Image image = new Image("file:brick.jpg");
        final ImageView imageView = new ImageView(image);

        Rectangle rect = new Rectangle(width, height);
        Stop[] stops =
        {
            new Stop(0.4, Color.rgb(0, 0, 0, 0.1)),
            new Stop(1, Color.rgb(0, 0, 0, 0.8))
        };
        RadialGradient rg = new RadialGradient(0, 0, 0.5, 0.5, 0.7, true,
            CycleMethod.NO_CYCLE, stops);
        rect.setFill(rg);
        Text text = new Text();
        
        text.setFill(Color.WHITE);
        text.setX(20);
        text.setY(150);
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        
        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);
        
        blend.setBottomInput(ds);
        
        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds.setSpread(0.2);
        
        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);
        
        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);
        
        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);
        
        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);
        
        blend.setTopInput(blend1);
        
        text.setEffect(blend);
        text.setFont(Font.font("Harlow", 120));
        
        TextField textField = new TextField();
        textField.setText("Neon Sign");
        text.textProperty().bind(textField.textProperty());
        textField.setPrefColumnCount(40);
        textField.setLayoutX(50);
        textField.setLayoutY(260);
        
        root.getChildren().addAll(imageView, rect, text, textField);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
