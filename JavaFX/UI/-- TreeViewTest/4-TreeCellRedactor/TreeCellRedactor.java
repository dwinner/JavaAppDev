package pkg4.treecellredactor;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Редактирование ячеек дерева
 * <p/>
 * @author oracle_pr1
 */
public class TreeCellRedactor extends Application
{
    private final Node rootIcon =
        new ImageView(new Image(getClass().getResourceAsStream("root.jpg")));
    private final Image depIcon =
        new Image(getClass().getResourceAsStream("Apply.png"));
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
    private TreeItem<String> rootNode = // Корневой узел дерева
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
        final Scene scene = new Scene(box, 400, 300);
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
    
    private final class TextFieldTreeCellImpl extends TreeCell<String>
    {
        private TextField textField;
        
        TextFieldTreeCellImpl()
        {
        }
        
        @Override
        public void startEdit()
        {
            super.startEdit();
            
            if (textField == null)
            {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
        
        @Override
        public void cancelEdit()
        {
            super.cancelEdit();
            setText(getItem());
            setGraphic(getTreeItem().getGraphic());
        }
        
        @Override
        public void updateItem(String item, boolean empty)
        {
            super.updateItem(item, empty);
            
            if (empty)
            {
                setText(null);
                setGraphic(null);
            }
            else
            {
                if (isEditing())
                {
                    if (textField != null)
                    {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                }
                else
                {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }
        
        private void createTextField()
        {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent keyEvent)
                {
                    if (keyEvent.getCode() == KeyCode.ENTER)
                    {
                        commitEdit(textField.getText());
                    }
                    else if (keyEvent.getCode() == KeyCode.ESCAPE)
                    {
                        cancelEdit();
                    }
                }
            });
        }
        
        private String getString()
        {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    /**
     * Entity-класс Employee
     */
    private static class Employee
    {
        private final SimpleStringProperty name;
        private final SimpleStringProperty department;
        
        private Employee(String name, String department)
        {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }
        
        public String getName()
        {
            return name.get();
        }
        
        public void setName(String fName)
        {
            name.set(fName);
        }
        
        public String getDepartment()
        {
            return department.get();
        }
        
        public void setDepartment(String dep)
        {
            department.set(dep);
        }
    }
}
