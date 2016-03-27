package pkg08.fxchoiceboxtest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FX Choise boxes
 * <p/>
 * @author oracle_pr1
 */
public class FxChoiceBoxTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // 1) Создание списков ChoiceBox

        final String[] greetings = new String[]
        {
            "Hello", // Английский
            "Рola", // Испанский
            "Ciao", // Итальянский
            "Hallo", // Немецкий
            "Привет"    // Русский
        };
        final ChoiceBox<String> cb = new ChoiceBox<>(
            FXCollections.observableArrayList(
            "English",
            "Spanish",
            "Italian",
            "German",
            "Russian"));

        // 2) Обработка событий в ChoiceBox
        final Label aLabel = new Label("Demo");

        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue,
                Number oldValue,
                Number newValue)
            {
                aLabel.setText(greetings[newValue.intValue()]);
            }
        });

        BorderPane root = new BorderPane();
        HBox hBox = new HBox(20.0);
        hBox.getChildren().addAll(cb, aLabel);
        root.setCenter(hBox);
        primaryStage.setTitle("ChoiceBox Sample");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
