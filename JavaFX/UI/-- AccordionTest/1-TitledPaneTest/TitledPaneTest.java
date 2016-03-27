package pkg1.titledpanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TitledPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Titled Pane test");
        StackPane root = new StackPane();
        
        TitledPane gridTitledPane = new TitledPane();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("First Name: "), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Last Name: "), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Email: "), 0, 2);
        grid.add(new TextField(), 1, 2);
        gridTitledPane.setText("Grid");
        gridTitledPane.setContent(grid);
        root.getChildren().add(gridTitledPane);
        
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
