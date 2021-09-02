package pkg3.addingnewrows;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Добавление новых данных к таблице
 * <p/>
 * @author oracle_pr1
 */
public class AddingNewRows extends Application
{
    public static class Person
    {
        private final StringProperty firstName;
        private final StringProperty lastName;
        private final StringProperty email;
        
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
        
        public void setLastName(String lName)
        {
            lastName.set(lName);
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
    private TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data = FXCollections.observableArrayList(
        new Person("Jacob", "Smith", "jacob.smith@example.com"),
        new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
        new Person("Ethan", "Williams", "ethan.williams@example.com"),
        new Person("Emma", "Jones", "emma.jones@example.com"),
        new Person("Michael", "Brown", "michael.brown@example.com"));
    private HBox hb = new HBox();
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Add columns to TableView");
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        
        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First");
        firstNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("firstName"));
        
        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last");
        lastNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("lastName"));
        
        TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(
            new PropertyValueFactory<Person, String>("email"));
        
        table.setItems(data);
        table.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        
        // UI-компоненты для добавления данных в таблицу.
        
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
                // TODO: Обработка ввода
                data.add(
                    new Person(addFirstName.getText(),
                        addLastName.getText(),
                        addEmail.getText()
                    )
                );
                addFirstName.setText("");
                addLastName.setText("");
                addEmail.setText("");
            }
        });
        
        // TODO: Удаление выбранного/выбранных столбца/столбцов
        
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
        
        final VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, table, hb);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        
        ((Group) scene.getRoot()).getChildren().addAll(vBox);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
