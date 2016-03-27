package displacementmapeffect;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Волновой эффект.
 * @author oracle_pr1
 */
public class DisplacementMapEffect extends Application
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
        content.add(displacementMap());
        stage.setScene(scene);
    }

    static Node displacementMap()
    {
        int w = 220;
        int h = 100;
        FloatMap map = new FloatMap();
        map.setWidth(w);
        map.setHeight(h);

        for (int i = 0; i < w; i++)
        {
            double v = (Math.sin(i / 50.0 * Math.PI) - 0.5) / 40.0;
            for (int j = 0; j < h; j++)
            {
                map.setSamples(i, j, 0.0f, (float) v);
            }
        }

        Group g = new Group();
        DisplacementMap dm = new DisplacementMap();
        dm.setMapData(map);

        Rectangle r = new Rectangle();
        r.setX(20.0f);
        r.setY(20.0f);
        r.setWidth(w);
        r.setHeight(h);
        r.setFill(Color.BLUE);

        g.getChildren().add(r);

        Text t = new Text();
        t.setX(40.0f);
        t.setY(80.0f);
        t.setText("Wavy Text");
        t.setFill(Color.YELLOW);
        t.setFont(Font.font("null", FontWeight.BOLD, 36));

        g.getChildren().add(t);

        g.setEffect(dm);
        g.setCache(true);

        g.setTranslateX(400);
        g.setTranslateY(200);

        return g;
    }
}
