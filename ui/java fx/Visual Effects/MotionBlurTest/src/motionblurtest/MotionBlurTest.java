package motionblurtest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class MotionBlurTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Motion Blur Effect");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        final MotionBlur mb = new MotionBlur();
        mb.setRadius(15.0);
        mb.setAngle(90.0);

        TitledPane tp = new TitledPane();
        tp.setLayoutX(10);
        tp.setLayoutY(10);
        tp.setCursor(Cursor.CROSSHAIR);
        tp.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");
        tp.setTooltip(new Tooltip("My pictures"));
        tp.setExpanded(true);

        Label label = new Label("Images");
        label.setCursor(Cursor.CLOSED_HAND);
        label.setPrefSize(300, 30);
        label.setStyle("-fx-font: bold italic 16pt Georgia;"
           + "-fx-text-fill:black;"
           + "-fx-background-color:#e6e6fa;");
        label.setAlignment(Pos.CENTER);
        tp.setGraphic(label);

        Image im = new Image(getClass().getResource("image.JPG").toString());
        final ImageView imv = new ImageView(im);
        imv.setPreserveRatio(true);
        imv.setFitHeight(250);
        imv.setFitWidth(250);
        tp.setContent(imv);

        tp.expandedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean oldValue,
                                Boolean newValue)
            {
                imv.setEffect(mb);
            }
        });
        tp.heightProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldValue,
                                Number newValue)
            {
                if (newValue.doubleValue() == 225.0)
                {
                    imv.setEffect(null);
                }
            }
        });

        root.getChildren().add(tp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
