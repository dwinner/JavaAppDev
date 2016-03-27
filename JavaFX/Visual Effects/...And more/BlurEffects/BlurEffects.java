package blureffects;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Эффекты размытости.
 *
 * @author oracle_pr1
 */
public class BlurEffects extends Application
{
    private Stage stage;
    private Scene scene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.show();

        scene = new Scene(new Group(), 840, 680);
        ObservableList<Node> content = ((Group) scene.getRoot()).getChildren();
        content.add(boxBlur());
        content.add(motionBlur());
        content.add(gaussianBlur());
        stage.setScene(scene);
    }

    static Node boxBlur()
    {
        Text t = new Text();
        t.setText("Blurry Text!");
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 36));
        t.setX(10);
        t.setY(40);

        BoxBlur bb = new BoxBlur();
        bb.setWidth(5);
        bb.setHeight(5);
        bb.setIterations(3);

        t.setEffect(bb);
        t.setTranslateX(300);
        t.setTranslateY(100);

        return t;
    }

    static Node motionBlur()
    {
        Text t = new Text();
        t.setX(20.0f);
        t.setY(80.0f);
        t.setText("Motion Blur");
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 60));

        MotionBlur mb = new MotionBlur();
        mb.setRadius(15.0f);
        mb.setAngle(45.0f);

        t.setEffect(mb);

        t.setTranslateX(520);
        t.setTranslateY(100);

        return t;
    }

    static Node gaussianBlur()
    {
        Text t2 = new Text();
        t2.setX(10.0f);
        t2.setY(140.f);
        t2.setCache(true);
        t2.setText("Gaussian Blur");
        t2.setFill(Color.RED);
        t2.setFont(Font.font("null", FontWeight.BOLD, 36));
        t2.setEffect(new GaussianBlur());

        return t2;
    }
}
