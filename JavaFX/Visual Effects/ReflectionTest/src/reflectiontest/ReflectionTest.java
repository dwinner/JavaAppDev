package reflectiontest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class ReflectionTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Reflection Effect");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 400, Color.WHITESMOKE);

        Stop[] stops = new Stop[]
        {
            new Stop(0, Color.LIGHTGRAY),
            new Stop(0.8, Color.WHITE)
        };
        LinearGradient lg =
           new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        Rectangle r = new Rectangle(400, 150);
        r.setLayoutX(20);
        r.setLayoutY(100);
        r.setFill(lg);
        PerspectiveTransform pt = new PerspectiveTransform();
        pt.setLlx(r.getX() + 35);
        pt.setLrx(r.getX() + r.getWidth() + 35);
        pt.setLly(r.getY() + r.getHeight());
        pt.setLry(r.getY() + r.getHeight());
        pt.setUlx(r.getX());
        pt.setUly(r.getY() + 30);
        pt.setUrx(r.getX() + r.getWidth());
        pt.setUry(r.getY() + 30);
        r.setEffect(pt);

        Text text = new Text();
        text.setLayoutX(60);
        text.setLayoutY(150);
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        Reflection effect = new Reflection();
        effect.setBottomOpacity(0.0);
        effect.setFraction(0.7);
        effect.setTopOffset(-65.0);
        effect.setTopOpacity(0.7);
        effect.setInput(is);
        text.setEffect(effect);
        text.setText("Reflection");
        text.setFill(Color.LIGHTBLUE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 60));
        text.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().addAll(r, text);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
