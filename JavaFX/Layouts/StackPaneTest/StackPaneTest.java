package pkg4.stackpanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Демонстрация расположения Stack Pane
 * <p/>
 * @author JavaFX Development Group
 */
public class StackPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Создание стековой панели

        StackPane stack = new StackPane();

        Rectangle helpIcon = new Rectangle(35.0, 25.0);
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop[]
            {
                new Stop(0, Color.web("#4977A3")),
                new Stop(0.5, Color.web("#B0C6DA")),
                new Stop(1, Color.web("9CB6CF"))
            }));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(9.5);
        helpIcon.setArcWidth(9.5);

        Text helpText = new Text("?   ");
        helpText.setFont(Font.font("Amble Cn", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

        stack.getChildren().addAll(helpIcon, helpText);

        // Выравнивание узлов стека по правому краю

        stack.setAlignment(Pos.CENTER_RIGHT);

        // Добавим панель стека в горизонтальную панель.

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        BorderPane border = new BorderPane();
        border.setTop(hbox);
        
        // Выдать стеку любое необходимое горизонтальное пространство
        
        HBox.setHgrow(stack, Priority.ALWAYS);
        hbox.getChildren().add(stack);

        primaryStage.setScene(new Scene(border, 640, 480));
        primaryStage.show();
    }
}
