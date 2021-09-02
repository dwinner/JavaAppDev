package pkg7.tilepanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Демонстрация использования размещения Tile Pane.
 * <p/>
 * @author JavaFX Development Group
 */
public class TilePaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        TilePane tile = new TilePane();
        tile.setPadding(new Insets(5, 0, 5, 0));
        tile.setVgap(4);
        tile.setHgap(4);
        tile.setPrefColumns(2);
        tile.setStyle("-fx-background-color: DAE6F3");

        ImageView pages[] = new ImageView[8];
        for (int i = 0; i < 8; i++)
        {
            pages[i] = new ImageView(new Image(TilePaneTest.class.getResourceAsStream(
                "graphics/fx" + (i + 1) + ".jpg"))
            );
            pages[i].setFitWidth(80);
            pages[i].setFitHeight(50);
            tile.getChildren().add(pages[i]);
        }

        primaryStage.setTitle("Tile Pane sample");
        
        BorderPane root = new BorderPane();
        root.setCenter(tile);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
