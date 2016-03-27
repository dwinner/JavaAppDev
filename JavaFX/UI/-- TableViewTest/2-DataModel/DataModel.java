package pkg2.datamodel;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Создание модели данных для FX-TableView
 * <p/>
 * @author oracle_pr1
 */
public class DataModel extends Application
{
    public static final int DEFAULT_STAGE_WIDTH = 400;
    public static final int DEFAULT_STAGE_HEIGHT = 500;
    
    /**
     * Сущностный класс для модели данных таблицы.
     */    
    public static class Person
    {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email)
        {
            firstName = new SimpleStringProperty(fName);
            lastName = new SimpleStringProperty(lName);
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
        new Person("Michael", "Brown", "michael.brown@example.com")
    );

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(DEFAULT_STAGE_WIDTH);
        primaryStage.setHeight(DEFAULT_STAGE_HEIGHT);
        
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        
        // Задание моделей поведения для столбцов таблицы
        
        TableColumn<Person,String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person,String>("firstName")
        );
        
        TableColumn<Person,String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(
            new PropertyValueFactory<Person,String>("lastName")
        );
        
        TableColumn<Person,String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(
            new PropertyValueFactory<Person,String>("email")
        );
               
        // Задание модели данных таблицы
        
        table.setItems(data);
        table.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        
        // Расположение таблицы в боксе
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(label, table);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
