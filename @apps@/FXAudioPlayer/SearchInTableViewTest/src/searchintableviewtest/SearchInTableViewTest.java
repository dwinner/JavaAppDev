package searchintableviewtest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Поиск в таблице.
 *
 * @author Denis
 */
public class SearchInTableViewTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }
   private TextField textField = new TextField("");
   private Label bindLabel = new Label("");
   private TableView<PersonEntity> table = new TableView<>();
   private Set<Integer> foundIndices = new LinkedHashSet<>(0x8);
   private int currentFirstFound = -1;

   
   {
      bindLabel.textProperty().bind(textField.textProperty());

      TableColumn<PersonEntity, String> firstNameColumn = new TableColumn<>("First Name");
      firstNameColumn.setMinWidth(100);
      firstNameColumn.setCellValueFactory(new PropertyValueFactory<PersonEntity, String>("firstName"));

      TableColumn<PersonEntity, String> lastNameColumn = new TableColumn<>("Last Name");
      lastNameColumn.setMinWidth(100);
      lastNameColumn.setCellValueFactory(new PropertyValueFactory<PersonEntity, String>("lastName"));

      TableColumn<PersonEntity, String> emailColumn = new TableColumn<>("Email");
      emailColumn.setMinWidth(100);
      emailColumn.setMinWidth(200);
      emailColumn.setCellValueFactory(new PropertyValueFactory<PersonEntity, String>("email"));

      // Задание модели данных таблицы

      ObservableList<PersonEntity> data = FXCollections.<PersonEntity>observableArrayList(
        new PersonEntity("Jacob", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Isabella", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Ethan", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Emma", "Jones", "emma.jones@example.com"),
        new PersonEntity("Michael", "Brown", "michael.brown@example.com"),
        new PersonEntity("John", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Peter", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Lucy", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Luke", "Jones", "emma.jones@example.com"),
        new PersonEntity("Hans", "Brown", "michael.brown@example.com"),
        new PersonEntity("Jacob", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Pit", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Jason", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Freddy", "Jones", "emma.jones@example.com"),
        new PersonEntity("El", "Brown", "michael.brown@example.com"),
        new PersonEntity("Smith", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Budd", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Bred", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Tom", "Jones", "emma.jones@example.com"),
        new PersonEntity("Silver", "Brown", "michael.brown@example.com"),
        new PersonEntity("Arnold", "Smith", "jacob.smith@example.com"),
        new PersonEntity("John", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Ender", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Halen", "Jones", "emma.jones@example.com"),
        new PersonEntity("Demo", "Brown", "michael.brown@example.com"),
        new PersonEntity("Test", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Abraham", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Luke", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Shit", "Jones", "emma.jones@example.com"),
        new PersonEntity("Common", "Brown", "michael.brown@example.com"),
        new PersonEntity("Fixme", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Dennis", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Cay", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Horstmann", "Jones", "emma.jones@example.com"),
        new PersonEntity("Herb", "Brown", "michael.brown@example.com"),
        new PersonEntity("Gary", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Cornell", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("David", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Joshua", "Jones", "emma.jones@example.com"),
        new PersonEntity("Bloch", "Brown", "michael.brown@example.com"),
        new PersonEntity("Patrick", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Nauthon", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Bruce", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Ekkel", "Jones", "emma.jones@example.com"),
        new PersonEntity("Tom", "Brown", "michael.brown@example.com"),
        new PersonEntity("Kite", "Smith", "jacob.smith@example.com"),
        new PersonEntity("Andrew", "Johnson", "isabella.johnson@example.com"),
        new PersonEntity("Kolin", "Williams", "ethan.williams@example.com"),
        new PersonEntity("Muk", "Jones", "emma.jones@example.com"),
        new PersonEntity("Michael", "Brown", "michael.brown@example.com"));

      table.setItems(data);
      table.getColumns().addAll(Arrays.asList(firstNameColumn, lastNameColumn, emailColumn));
      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   }

   @Override
   public void start(Stage primaryStage)
   {
      BorderPane root = new BorderPane();

      root.setTop(textField);
      root.setCenter(table);
      root.setBottom(bindLabel);

      Scene scene = new Scene(root, 640, 480);
      primaryStage.setTitle("Search In TableView Test");
      primaryStage.setScene(scene);
      primaryStage.show();

      textField.textProperty().addListener(new ChangeListener<String>()
      {
         @Override
         public void changed(ObservableValue<? extends String> observable,
                             String oldValue,
                             String newValue)
         {
            if (newValue == null || newValue.trim().isEmpty())
            {
               table.getSelectionModel().clearSelection();
               return;
            }
            ObservableList<PersonEntity> data = table.getItems();
            if (data == null || data.isEmpty())
            {
               return;
            }
            foundIndices.clear();
            for (int i = 0; i < data.size(); i++)
            {
               PersonEntity personEntity = data.get(i);
               if (personEntity.getFirstName().startsWith(newValue.trim()))
               {
                  foundIndices.add(i);
               }
            }
            if (!foundIndices.isEmpty())
            {
               Iterator<Integer> linkedIterator = foundIndices.iterator();
               int firstFoundIndex = linkedIterator.next();
               currentFirstFound = firstFoundIndex;
               table.getSelectionModel().clearSelection();
               table.scrollTo(firstFoundIndex);
               table.getSelectionModel().select(firstFoundIndex);
               while (linkedIterator.hasNext())
               {
                  table.getSelectionModel().select(linkedIterator.next());
               }
            }
         }
      });
   }
}
