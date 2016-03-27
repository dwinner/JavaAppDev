package pkg5.additemsondemandtest;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Добаление новых узлов к дереву.
 * <p/>
 * @author JavaFx
 */
public class AddItemsOnDemandTest extends Application
{
    private final Node rootIcon =
        new ImageView(new Image(getClass().getResourceAsStream("Folder.bmp")));
    private final Image depIcon =
        new Image(getClass().getResourceAsStream("List.bmp"));
    private List<Employee> employees = Arrays.asList(
        new Employee("Ethan Williams", "Sales Department"),
        new Employee("Emma Jones", "Sales Department"),
        new Employee("Michael Brown", "Sales Department"),
        new Employee("Anna Black", "Sales Department"),
        new Employee("Rodger York", "Sales Department"),
        new Employee("Susan Collins", "Sales Department"),
        new Employee("Mike Graham", "IT Support"),
        new Employee("Judy Mayer", "IT Support"),
        new Employee("Gregory Smith", "IT Support"),
        new Employee("Jacob Smith", "Accounts Department"),
        new Employee("Isabella Johnson", "Accounts Department"));
    private TreeItem<String> rootNode =
        new TreeItem<>("My Company Human Resources", rootIcon);
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        rootNode.setExpanded(true);
        for (Employee employee : employees)
        {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren())
            {
                if (depNode.getValue().contentEquals(employee.getDepartment()))
                {
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                TreeItem<String> depNode = new TreeItem<>(
                    employee.getDepartment(),
                    new ImageView(depIcon));
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }
        
        primaryStage.setTitle("Tree View Sample");
        VBox box = new VBox();
        final Scene scene = new Scene(box, 640, 480);
        scene.setFill(Color.LIGHTGRAY);
        
        TreeView<String> treeView = new TreeView<>(rootNode);
        treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>()
        {
            @Override
            public TreeCell<String> call(TreeView<String> p)
            {
                return new TextFieldTreeCellImpl();
            }
        });
        
        box.getChildren().add(treeView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
