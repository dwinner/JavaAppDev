package displacementmaptest;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class DisplacementMapTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 300, Color.BLACK);

        int w = 200;
        int h = 200;
        final FloatMap map1 = new FloatMap();
        map1.setWidth(w);
        map1.setHeight(h);
        for (int i = 0; i < w; i++)
        {
            double v = (Math.sin(i / 50.0 * Math.PI)) / 40.0;
            for (int j = 0; j < h; j++)
            {
                map1.setSamples(i, j, 0.0f, (float) v);
            }
        }
        final FloatMap map2 = new FloatMap();
        map2.setWidth(w);
        map2.setHeight(h);
        for (int i = 0; i < w; i++)
        {
            double v = -(Math.sin(i / 50.0 * Math.PI)) / 40.0;
            for (int j = 0; j < h; j++)
            {
                map2.setSamples(i, j, 0.0f, (float) v);
            }
        }
        final DisplacementMap dm = new DisplacementMap();
        dm.setMapData(map1);

        Image im = new Image(getClass().getResource("image.gif").toExternalForm());
        ImageView imv = new ImageView(im);
        imv.setLayoutX(50);
        imv.setLayoutY(50);
        imv.setPreserveRatio(true);
        imv.setFitHeight(h);
        imv.setFitWidth(w);
        imv.setEffect(dm);

        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if ((now / 200000000) % 2 == 0)
                {
                    dm.setMapData(map2);
                }
                else
                {
                    dm.setMapData(map1);
                }
            }
        };
        timer.start();

        root.getChildren().addAll(imv);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
