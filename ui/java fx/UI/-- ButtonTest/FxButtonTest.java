package pkg04.fxbuttontest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Кнопки JavaFX
 * <p/>
 * @author oracle_pr1
 */
public class FxButtonTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        GridPane centerGrid = new GridPane();

        // centerGrid.setGridLinesVisible(true);
        centerGrid.setHgap(15);
        centerGrid.setVgap(20);
        centerGrid.setPadding(Insets.EMPTY);

        // 1) Создание кнопок

        Button button1 = new Button();
        Image imageCheck = new Image(getClass().getResourceAsStream("check.jpg"));
        button1.setText("Accept");
        button1.setGraphic(new ImageView(imageCheck));

        Button button2 = new Button("Accept");
        final Button button3 = new Button("Decline");
        Button button4 = new Button("Declined");

        Button button5 = new Button();
        button5.setGraphic(new ImageView(imageCheck));

        Image imageDecline = new Image(getClass().getResourceAsStream("close.jpg"));
        Button button6 = new Button();
        button6.setGraphic(new ImageView(imageDecline));

        centerGrid.add(button1, 0, 0, 3, 1);
        centerGrid.add(button2, 0, 1);
        centerGrid.add(button3, 1, 1);
        centerGrid.add(button4, 2, 1);
        centerGrid.add(button5, 0, 2);
        centerGrid.add(button6, 1, 2);

        final Label aLabel = new Label("Wait");
        centerGrid.add(aLabel, 2, 2);

        // 2) Обработка событий в кнопке

        button2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                aLabel.setText("Accepted");
            }
        });

        // 3) Создание эффектов

        final DropShadow shadow = new DropShadow();
        // Добавим тень, когда курсор мыши находится над узлом
        button3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                button3.setEffect(shadow);
            }
        });
        // Удалим тень, когда курсор мыши покидает узел
        button3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                button3.setEffect(null);
            }
        });
        
        // 4) Применение CSS для декорирования
        button1.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9");

        BorderPane root = new BorderPane();
        root.setCenter(centerGrid);

        primaryStage.setTitle("FX Buttons");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
