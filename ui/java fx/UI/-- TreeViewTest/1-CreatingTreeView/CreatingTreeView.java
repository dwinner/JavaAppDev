package pkg1.creatingtreeview;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Создание TreeView в FX
 * <p/>
 * @author oracle_pr1
 */
public class CreatingTreeView extends Application
{
    private final Node rootIcon =
        new ImageView(new Image(getClass().getResourceAsStream("root_folder.jpg")));
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Tree View Sample");
        
        TreeItem<String> rootItem = new TreeItem<>("Inbox", rootIcon);
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++)
        {
            TreeItem<String> item = new TreeItem<>("Message " + i);
            rootItem.getChildren().add(item);
        }
        TreeView<String> tree = new TreeView<>(rootItem);
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
