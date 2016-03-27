package pkg09.fxtextfieldtest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FX Text Field sample
 * <p/>
 * @author oracle_pr1
 */
public class FxTextFieldTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Создаем контейнер GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Определяем тектовое поле Name
        final TextField name = new TextField();
        name.setPromptText("Enter your first name.");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);

        // Определляем тектовое поле Last Name
        final TextField lastName = new TextField();
        lastName.setPromptText("Enter your last name.");
        GridPane.setConstraints(lastName, 0, 1);
        grid.getChildren().add(lastName);

        // Определяем текстовое поле с комментарием
        final TextField comment = new TextField();
        comment.setPrefColumnCount(15);
        comment.setPromptText("Enter your comment");
        GridPane.setConstraints(comment, 0, 2);
        grid.getChildren().add(comment);

        // Определяем кнопку Submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        // Определяем кнопку Clear
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 1, 1);
        grid.getChildren().add(clear);

        // Добавление метки
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);

        // Установка действия для кнопки Submit
        submit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (comment.getText() != null && !comment.getText().trim().isEmpty())
                {
                    label.setText(name.getText() + " " + lastName.getText() + ", "
                        + "thank you for your comment!");
                }
                else
                {
                    label.setText("You have not left a comment.");
                }
            }
        });

        // Установка действия для кнопки Clear
        clear.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                name.setText("");
                lastName.setText("");
                comment.setText("");
                label.setText(null);
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        primaryStage.setTitle("TextField sample");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
