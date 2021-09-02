package pkg13.fxlistviewtest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FX ListView
 * <p/>
 * @author oracle_pr1
 */
public class FxListViewTest extends Application
{
    private ListView<String> list = new ListView<>();
    private ObservableList<String> data = FXCollections.observableArrayList(
        "CHOCOLATE",
        "SALMON",
        "GOLD",
        "CORAL",
        "DARKORCHID",
        "DARKGOLDENROD",
        "LIGHTSALMON",
        "BLACK",
        "ROSYBROWN",
        "BLUE",
        "BLUEVIOLET",
        "BROWN");
    private final Label label = new Label();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        VBox box = new VBox();
        Scene scene = new Scene(box, 320, 240);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ListView Sample");
        box.getChildren().addAll(list, label);
        VBox.setVgrow(list, Priority.ALWAYS);   // Рост списка по высоте

        label.setLayoutX(10);
        label.setLayoutY(115);
        label.setFont(Font.font("Verdana", 20));

        list.setItems(data);    // Задание модели списка

        // Задаем поведение объекта отображения ячейки

        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListCell<String>()
                {
                    @Override
                    protected void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        Rectangle rect = new Rectangle(100, 20);
                        if (item != null)
                        {
                            rect.setFill(Color.web(item));
                            setGraphic(rect);
                        }
                    }
                };
            }
        });

        // Поведение метки при событии выделения ячейки списка
        
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> ov,
                String oldValue,
                String newValue)
            {
                label.setText(newValue);
                label.setTextFill(Color.web(newValue));
            }
        });

        primaryStage.show();
    }
}
