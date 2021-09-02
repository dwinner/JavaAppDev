package pkg06.fxtogglebuttontest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Кнопки-переключатели в FX
 * <p/>
 * @author oracle_pr1
 */
public class FxToggleButtonTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Создание кнопок-переключателей

        final ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("Minor");
        tb1.setToggleGroup(group);
        tb1.setSelected(true);

        ToggleButton tb2 = new ToggleButton("Major");
        tb2.setToggleGroup(group);

        ToggleButton tb3 = new ToggleButton("Critical");
        tb3.setToggleGroup(group);

        // Расположение кнопок-переключателей

        HBox tbPane = new HBox();
        tbPane.getChildren().addAll(tb1, tb2, tb3);

        // Установка поведения

        tb1.setUserData(Color.LIGHTGREEN);
        tb2.setUserData(Color.LIGHTBLUE);
        tb3.setUserData(Color.SALMON);

        final Rectangle rect = new Rectangle(145, 50);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle oldToggle,
                Toggle newToggle)
            {
                if (newToggle == null)
                {
                    rect.setFill(Color.WHITE);
                }
                else
                {
                    rect.setFill(
                        (Color) group.getSelectedToggle().getUserData()
                    );
                }
            }
        });
        
        // Применяем CSS к кнопкам-переключателям
        
        tb1.setStyle("-fx-base: lightgreen");
        tb2.setStyle("-fx-base: lightblue");
        tb1.setStyle("-fx-base: salmon");
        
        VBox vert = new VBox();
        vert.getChildren().addAll(tbPane, rect);

        BorderPane root = new BorderPane();
        root.setCenter(vert);

        primaryStage.setTitle("Toggle buttons sample");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
