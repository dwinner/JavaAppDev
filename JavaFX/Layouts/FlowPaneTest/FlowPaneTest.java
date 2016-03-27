package pkg6.flowpanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Демонстрация размещения Flow Pane.
 * <p/>
 * @author JavaFX Development Group
 */
public class FlowPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);

        // Предпочтительная ширина Flow Pane
        flow.setPrefWrapLength(190);

        flow.setStyle("-fx-background-color: DAE6F3");

        ImageView pages[] = new ImageView[8];
        Image currentImage;
        for (int i = 0; i < pages.length; i++)
        {
            currentImage = new Image(
                FlowPaneTest.class.getResourceAsStream("graphics/fx" + (i + 1) + ".jpg")
            );
            pages[i] = new ImageView(currentImage);
            pages[i].setFitWidth(80);
            pages[i].setFitHeight(50);
            flow.getChildren().add(pages[i]);
        }

        BorderPane root = new BorderPane();
        root.setLeft(flow);

        primaryStage.setTitle("Flow Pane sample");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
