package pkg1.desiredsizes;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ”становка желаемых размеров узлов
 * <p/>
 * @author JavaFX Development Group
 */
public class DesiredSizes extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20, 0, 20, 20));

        Button btnAdd = new Button("Add");
        Button btnDelete = new Button("Delete");
        Button btnMoveUp = new Button("Move Up");
        Button btnMoveDown = new Button("Move Down");

        // —оздание вертикального бокса дл€ кнопок
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 30, 10, 25));
        vbButtons.getChildren().addAll(btnAdd, btnDelete, btnMoveUp, btnMoveDown);

        // 1) «адание одинаковой ширины дл€ кнопок в вертикальном боксе
        for (Node aNode : vbButtons.getChildren())
        {
            if (aNode instanceof Button)
            {
                Button currentButton = (Button) aNode;
                currentButton.setMaxWidth(Double.MAX_VALUE);
            }
        }
        
        root.setRight(vbButtons);
        
        // 2) «адание одинакового размера дл€ кнопок в плиточной панели
        Button btnApply = new Button("Apply");
        Button btnContinue = new Button("Continue");
        Button btnExit = new Button("Exit");
        
        // —оздание плиточного расположени€ с горизонтальной ориентацией
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10.0);
        tileButtons.getChildren().addAll(btnApply, btnContinue, btnExit);
        
        // «адаем один размер дл€ всех кнопок в плиточной панели
        for (Node aNode : tileButtons.getChildren())
        {
            if (aNode instanceof Button)
            {
                Button tileButton = (Button) aNode;
                tileButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            }
        }
        btnExit.setStyle("-fx-font-size: 14pt;");
        
        root.setBottom(tileButtons);
        
        // 3) ”становка максимальной высоты в предпочтительную
        ListView<String> lvList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(
            "Hot dog",
            "Hamburger",
            "French fries",
            "Carrot sticks",
            "Chicken salad"
        );
        lvList.setItems(items);
        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        
        root.setCenter(lvList);
        
        // 4) «адание минимальной ширины в предпочтительную
        btnMoveDown.setMinWidth(Control.USE_PREF_SIZE);
        
        // 5) ѕредотвращение изменени€ размеров списка
        lvList.setMinSize(150.0, Control.USE_PREF_SIZE);
        lvList.setMaxSize(150.0, Control.USE_PREF_SIZE);

        primaryStage.setTitle("Desired UI dimensions");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
