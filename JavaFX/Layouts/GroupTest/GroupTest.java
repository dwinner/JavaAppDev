package pkg15.grouptest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class GroupTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Группы компонентов");

        Group root = new Group();
        root.setAutoSizeChildren(false);
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();

        Button btn = new Button("Кнопка Button");
        btn.setLayoutX(30);
        btn.setLayoutY(100);
        btn.setPrefSize(200, 40);

        Text text = new Text("Группа Group");
        text.setLayoutX(30);
        text.setLayoutY(200);

        root.getChildren().addAll(btn, text);
    }
}
