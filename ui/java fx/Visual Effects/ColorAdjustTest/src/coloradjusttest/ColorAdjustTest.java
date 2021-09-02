package coloradjusttest;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class ColorAdjustTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Color Adjust");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        final ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(0.3);
        effect.setContrast(0.5);
        effect.setHue(0.5);
        effect.setSaturation(0.5);

        final ToggleButton btnOn = new ToggleButton("JavaFX");
        btnOn.setLayoutX(30);
        btnOn.setLayoutY(50);
        btnOn.setCursor(Cursor.CLOSED_HAND);
        btnOn.setTextAlignment(TextAlignment.CENTER);
        btnOn.setPrefSize(200, 50);
        btnOn.setStyle(
           "-fx-base:#9900ff;-fx-font: bold italic 18pt Georgia;-fx-text-fill:white;");
        btnOn.selectedProperty().
           addListener(new javafx.beans.value.ChangeListener<Boolean>()
        {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends Boolean> ov,
                                Boolean oldVal,
                                Boolean newVal)
            {
                if (newVal.equals(Boolean.TRUE))
                {
                    btnOn.setEffect(effect);
                }
                if (newVal.equals(Boolean.FALSE))
                {
                    btnOn.setEffect(null);
                }
            }
        });

        root.getChildren().add(btnOn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
