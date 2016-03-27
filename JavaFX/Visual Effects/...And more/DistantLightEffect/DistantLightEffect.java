package distantlighteffect;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class DistantLightEffect extends Application
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
        content.add(distantLight());
        stage.setScene(scene);
    }

    static Node distantLight()
    {
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        light.setElevation(30.0f);

        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);

        final Text t = new Text();
        t.setText("Distant Light");
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 70));
        t.setX(10.0f);
        t.setY(50.0f);
        t.setTextOrigin(VPos.TOP);

        t.setEffect(l);

        final Rectangle r = new Rectangle();
        r.setFill(Color.BLACK);

        Group g = new Group();
        g.getChildren().add(r);
        g.getChildren().add(t);

        g.setTranslateY(460);

        return g;
    }
}
