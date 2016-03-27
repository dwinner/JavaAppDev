package pkg1.borderpanetest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * ƒемонстраци€ использовани€ расположени€ Border Pane
 * @author JavaFX Development Group.
 */
public class BorderPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Sample Border Pane");
        
        BorderPane root = new BorderPane();
        root.setTop(new Rectangle(200, 50, Color.DARKCYAN));
        root.setBottom(new Rectangle(200, 50, Color.DARKCYAN));
        root.setCenter(new Rectangle(100, 100, Color.MEDIUMAQUAMARINE));
        root.setLeft(new Rectangle(50, 100, Color.DARKTURQUOISE));
        root.setRight(new Rectangle(50, 100, Color.DARKTURQUOISE));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
