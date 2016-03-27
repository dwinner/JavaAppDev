package pkg12.fxscrollpanetest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FX ScrollPane sample
 * <p/>
 * @author oracle_pr1
 */
public class FxScrollPaneTest extends Application
{
    final ScrollPane sp = new ScrollPane();
    final Image[] images = new Image[5];
    final ImageView[] pics = new ImageView[5];
    final VBox vb = new VBox();
    final Label fileName = new Label();
    final String[] imageNames = new String[]
    {
        "fw1.png",
        "fw2.png",
        "fw3.png",
        "fw4.png",
        "fw5.png"
    };

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        /*
         * Image tux = new Image(getClass().getResourceAsStream("Tux.jpg")); ScrollPane sp = new ScrollPane();
         * sp.setContent(new ImageView(tux)); sp.setPannable(true); // Способность перемещать содержимое узла мышью.
         * sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
         * sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Для прокрутки это не даст эффекта:
         * sp.setFitToWidth(true); // Для прокрутки это не даст эффекта: sp.setFitToHeight(true); BorderPane root = new
         * BorderPane(); root.setCenter(sp); primaryStage.setTitle("ScrollPane Sample"); primaryStage.setScene(new
         * Scene(root, 300, 250)); primaryStage.show();
         *
         */

        VBox box = new VBox();
        Scene scene = new Scene(box, 180, 180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scroll Pane");
        box.getChildren().addAll(sp, fileName);
        VBox.setVgrow(sp, Priority.ALWAYS);

        fileName.setLayoutX(30);
        fileName.setLayoutY(160);

        for (int i = 0; i < 5; i++)
        {
            images[i] = new Image(getClass().getResourceAsStream(imageNames[i]));
            pics[i] = new ImageView(images[i]);
            pics[i].setFitWidth(100);
            pics[i].setPreserveRatio(true); // Сохранение пропорций исходного изображения
            vb.getChildren().add(pics[i]);
        }

        sp.setVmax(440);
        sp.setPrefSize(115, 150);
        sp.setContent(vb);
        sp.vvalueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number oldValue,
                Number newValue)
            {
                fileName.setText(imageNames[(newValue.intValue() - 1)/100]);
            }
        });
        primaryStage.show();
    }
}
