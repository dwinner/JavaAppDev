package pkg3.vboxtest;

import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Демонстрация расположения VBox Pane
 * <p/>
 * @author JavaFX Development Group.
 */
public class VBoxTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        Text title = new Text("Data");
        title.setFont(Font.font("Amble CN", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Text options[] = new Text[]
        {
            new Text("  Sales"),
            new Text("  Marketing"),
            new Text("  Distribution"),
            new Text("  Costs")
        };
        vbox.getChildren().addAll(Arrays.asList(options));

        BorderPane root = new BorderPane();
        root.setLeft(vbox);

        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
