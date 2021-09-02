package sepiatonetest;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class SepiaToneTest extends Application
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
        Scene scene = new Scene(root, 650, 250, Color.BEIGE);

        HBox hbox = new HBox();
        hbox.setSpacing(40);
        hbox.setPrefHeight(150);
        hbox.setAlignment(Pos.CENTER);

        final PerspectiveTransform pt = new PerspectiveTransform();
        SepiaTone st = new SepiaTone();
        st.setLevel(1.0);
        pt.setInput(st);

        final ImageView[] imv = new ImageView[5];
        for (int i = 0; i <= 4; i++)
        {
            Image im = new Image(
               getClass().getResource("image" + i + ".jpg").toExternalForm());
            imv[i] = new ImageView(im);
            imv[i].setFitHeight(150);
            imv[i].setFitWidth(150);
            imv[i].setPreserveRatio(true);
            pt.setLlx(imv[i].getX());
            pt.setUlx(imv[i].getX());
            pt.setLly(imv[i].getY() + 150.0);
            pt.setUly(imv[i].getY());
            pt.setLrx(imv[i].getX() + 180.0);
            pt.setUrx(imv[i].getX() + 180.0);
            pt.setLry(imv[i].getY() + 130.0);
            pt.setUry(imv[i].getY() + 20.0);
            imv[i].setEffect(pt);
            final int l = i;
            imv[i].setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    imv[l].setEffect(null);
                }
            });
            imv[i].setOnMouseExited(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    imv[l].setEffect(pt);
                }
            });
            hbox.getChildren().add(imv[i]);
        }

        ScrollPane sp = new ScrollPane();
        sp.setLayoutX(20);
        sp.setLayoutY(20);
        sp.setCursor(Cursor.CLOSED_HAND);
        sp.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");
        sp.setPrefSize(600, 200);
        sp.setContent(hbox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setPannable(true);

        root.getChildren().add(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
