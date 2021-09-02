package pkg2.differenttreeitems;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Различные типы узлов дерева
 * <p/>
 * @author oracle_pr1
 */
public class DifferentTreeItems extends Application
{
    private final Node rootIcon =
        new ImageView(new Image(getClass().getResourceAsStream("root_folder.jpg")));
    private final ToggleGroup group = new ToggleGroup();
    private TreeItem[] securityItems = new TreeItem[10];
    private TreeItem[] serverItems = new TreeItem[6];
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Tree View Sample");
        
        TreeItem<String> rootItem = new TreeItem<>("Settings", rootIcon);
        rootItem.setExpanded(true);
        TreeItem<String> securityItem = new TreeItem<>("Security");
        securityItem.setExpanded(true);
        TreeItem<String> serverItem = new TreeItem<>("Server");
        
        for (int i = 1; i < 9; i++)
        {
            TreeItem<CheckBox> item = securityItems[i] = new TreeItem<>(new CheckBox("Option " + i));
        }
        securityItem.getChildren().addAll(securityItems);
        
        for (int i = 1; i < 5; i++)
        {
            RadioButton rb = new RadioButton("Option " + i);
            rb.setToggleGroup(group);
            TreeItem<RadioButton> item = serverItems[i] = new TreeItem<>(rb);
        }
        serverItem.getChildren().addAll(serverItems);
        rootItem.getChildren().addAll(securityItem, serverItem);
        
        TreeView tree = new TreeView(rootItem);
        
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
