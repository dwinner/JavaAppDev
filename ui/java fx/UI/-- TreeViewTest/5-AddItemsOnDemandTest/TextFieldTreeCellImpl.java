package pkg5.additemsondemandtest;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Тип для инкапсуляции поведения ячеек дерева для TreeView FX при редактировании.
 * <p/>
 * @author JavaFx
 */
public final class TextFieldTreeCellImpl extends TreeCell<String>
{
    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();

    public TextFieldTreeCellImpl()
    {
        MenuItem addMenuItem = new MenuItem("Add Employee");
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                TreeItem<String> newEmployee = new TreeItem<>("New Employee");
                getTreeItem().getChildren().add(newEmployee);
            }
        });
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
                if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null)
                {
                    setContextMenu(addMenu);
                }
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
