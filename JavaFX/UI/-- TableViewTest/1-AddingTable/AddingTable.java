package pkg1.addingtable;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * —оздание FX-таблицы дл€ TableView
 * <p/>
 * @author oracle_pr1
 */
public class AddingTable extends Application
{
    public static final int DEFAULT_STAGE_WIDTH = 400;
    public static final int DEFAULT_STAGE_HEIGHT = 500;
    
    private TableView<CharSequence> table = new TableView<>();
    
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
        
        // —оздание колонок дл€ UI-представител€ таблицы
        
        TableColumn<CharSequence,?> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<CharSequence,?> lastNameColumn = new TableColumn<>("Last Name");
        
        TableColumn<CharSequence,?> emailColumn = new TableColumn<>("Email");
        // —оздание вложенных колонок таблицы
        TableColumn<CharSequence,?> firstEmailColumn = new TableColumn<>("Primary");
        TableColumn<CharSequence,?> secondEmailColumn = new TableColumn<>("Secondary");
        emailColumn.getColumns().addAll(firstEmailColumn, secondEmailColumn);
        
        table.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        
        // «адание узла, который €вл€етс€ сигналом отсутстви€ данных
        Image nodataImage = new Image(getClass().getResourceAsStream("nodata.jpg"));
        ImageView nodataImageView = new ImageView(nodataImage);
        table.setPlaceholder(nodataImageView);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(label, table);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
