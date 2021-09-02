package scrollbartest;

import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Компонент прокрутки.
 *
 * @author oracle_pr1
 */
public class ScrollBarTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    public final ScrollBar sc = new ScrollBar();
    public final Image[] images = new Image[5];
    public final ImageView[] pics = new ImageView[5];
    public final VBox vb = new VBox();
    private DropShadow shadow = new DropShadow();

    @Override
    public void start(Stage primaryStage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 180, 180);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollbar sample");
        root.getChildren().addAll(vb, sc);

        shadow.setColor(Color.GREY);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);

        vb.setLayoutX(5);
        vb.setSpacing(10);

        sc.setLayoutX(scene.getWidth() - sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(180);
        sc.setMax(360);

        for (int i = 0; i < images.length; i++)
        {
            final Image image = images[i] =
               new Image(getClass().getResourceAsStream(
               "images/fw" + (i + 1) + ".png"));
            final ImageView pic = pics[i] = new ImageView(images[i]);
            pic.setFitWidth(120);
            pic.setFitHeight(sc.getMax() / (images.length - 1));
            pic.setEffect(shadow);
            vb.getChildren().add(pics[i]);
        }

        sc.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldValue,
                                Number newValue)
            {
                vb.setLayoutY(-newValue.doubleValue());
            }
        });

        primaryStage.show();
    }
}
