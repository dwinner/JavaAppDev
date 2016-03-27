package receivingvalues;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ѕолучение значений дл€ прогресс-контроллеров
 * <p/>
 * @author JavaFx
 */
public class ReceivingValues extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Progress Controls");

        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);

        final ProgressBar pb = new ProgressBar(0);
        final ProgressIndicator pi = new ProgressIndicator(0);

        slider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                Number oldValue,
                Number newValue)
            {
                if (slider.getMax() <= 0)
                {
                    return;
                }
                pb.setProgress(newValue.doubleValue() / slider.getMax());
                pi.setProgress(newValue.doubleValue() / slider.getMax());
            }
        });
        
        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(slider, pb, pi);
        scene.setRoot(hb);
        primaryStage.show();
    }
}
