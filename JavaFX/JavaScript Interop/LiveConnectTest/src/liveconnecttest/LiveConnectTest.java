package liveconnecttest;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class LiveConnectTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private SimpleStringProperty sp = new SimpleStringProperty();

    public void setImage(String input)
    {
        sp.setValue(input);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Images");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();

        BorderPane borderPane = new BorderPane();
        borderPane.setLayoutX(20);
        borderPane.setLayoutY(30);
        Image im = new Image(getClass().getResource("image0.jpeg").toString());
        final ImageView imv = new ImageView(im);
        imv.setPreserveRatio(true);
        imv.setFitHeight(300);
        imv.setFitWidth(300);
        imv.setSmooth(true);
        imv.setCursor(Cursor.TEXT);
        DropShadow effect = new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);
        imv.setEffect(effect);
        borderPane.setCenter(imv);

        sp.addListener(new ChangeListener<java.lang.String>()
        {
            @Override
            public void changed(ObservableValue<? extends java.lang.String> o,
                                String oldVal,
                                String newVal)
            {
                Image im = new Image(getClass().getResource(sp.getValue()).toString());
                imv.setImage(im);
            }
        });

        root.getChildren().addAll(borderPane);
    }
}
