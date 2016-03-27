package pkg4.cellediting;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Редактирование ячеек FX-таблицы.
 * <p/>
 * @author oracle_pr1
 */
public class CellEditing extends Application
{
    public static final int DEFAULT_STAGE_WIDTH = 640;
    public static final int DEFAULT_STAGE_HEIGHT = 480;
    
    // Табличный вид для объектов класса Person
    private TableView<Person> table = new TableView<>();
    
    // Наблюдаемый список объектов Person
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
    final HBox hb = new HBox();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage)
    {
        // Создание сцены для окна верхнего уровня

        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(DEFAULT_STAGE_WIDTH);
        stage.setHeight(DEFAULT_STAGE_HEIGHT);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        // Поведение при редактировании ячеек таблицы

        table.setEditable(true);
        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory =
            new Callback<TableColumn<CellEditing.Person, String>, TableCell<CellEditing.Person, String>>()
            {
                @Override
                public TableCell<Person, String> call(TableColumn<Person, String> tableColumn)
                {
                    return new EditingCell();
                }
            };

        // Создание столбцов таблицы на основе свойств объектов Person

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("firstName")
        );
        firstNameColumn.setCellFactory(cellFactory);
        firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CellEditing.Person, String>>()
        {   // Поведение по завершении редактирования ячеек
            @Override
            public void handle(CellEditEvent<Person, String> t)
            {
                t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setFirstName(t.getNewValue());
            }
        });

        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("lastName"));
        lastNameColumn.setCellFactory(cellFactory);
        lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<CellEditing.Person, String>>()
        {   // Поведение по завершении редактирования ячеек
            @Override
            public void handle(CellEditEvent<Person, String> t)
            {
                t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setLastName(t.getNewValue());
            }
        });

        TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("email"));
        emailColumn.setCellFactory(cellFactory);
        emailColumn.setOnEditCommit(new EventHandler<CellEditEvent<CellEditing.Person, String>>()
        {   // Поведение по завершении редактирования ячеек
            @Override
            public void handle(CellEditEvent<Person, String> t)
            {
                t.getTableView().getItems().get(
                    t.getTablePosition().getRow()).setEmail(t.getNewValue());
            }
        });

        table.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        table.setItems(data);

        // Создание UI-контроллеров ввода для добавления данных в модель

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameColumn.getPrefWidth());
        
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameColumn.getPrefWidth());
        addLastName.setPromptText("Last Name");
        
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailColumn.getPrefWidth());
        addEmail.setPromptText("Email");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                data.add(new Person(addFirstName.getText(), addLastName.getText(), addEmail.getText()));
                addFirstName.setText("");
                addLastName.setText("");
                addEmail.setText("");
            }
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Entity-класс отражения табличных данных
     */
    public static class Person
    {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person()
        {
            throw new UnsupportedOperationException("Prevent default ctor");
        }

        private Person(String fName, String lName, String email)
        {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName()
        {
            return firstName.get();
        }

        public void setFirstName(String fName)
        {
            firstName.set(fName);
        }

        public String getLastName()
        {
            return lastName.get();
        }

        public void setLastName(String fName)
        {
            lastName.set(fName);
        }

        public String getEmail()
        {
            return email.get();
        }

        public void setEmail(String email)
        {
            this.email.set(email);
        }
    }

    /**
     * Тип для событий и поведения при редактировании ячеек таблицы
     */
    private static class EditingCell extends TableCell<Person, String>
    {
        private TextField textField;

        EditingCell()
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

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }

        @Override
        public void cancelEdit()
        {
            super.cancelEdit();

            setText(getItem());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void commitEdit(String editValue)
        {
            super.commitEdit(editValue);
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
                if (isEditable())
                {
                    if (textField != null)
                    {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
                else
                {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }

        private void createTextField()
        {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent t)
                {
                    if (t.getCode() == KeyCode.ENTER)
                    {
                        commitEdit(textField.getText());
                    }
                    else if (t.getCode() == KeyCode.ESCAPE)
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
}
