package pkg2.contentalignment;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Выравнивание содержимого узлов.
 * <p/>
 * @author JavaFX Development Group
 */
public class ContentAlignment extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        
        // Выравнивание грида по центру
        grid.setAlignment(Pos.CENTER);
        
        // Выравнивание столбцов в Grid'е        
        ColumnConstraints column1 = new ColumnConstraints();    // Для первого столбца
        column1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(column1);
        
        ColumnConstraints column2 = new ColumnConstraints();    // Для второго столбца
        column2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(column2);
            
        /* Выравнивание кнопок по центру в HBox
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(10.0); */
        
        Button btnSubmit = new Button("Submit");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        btnSubmit.setStyle("-fx-font-size: 11pt;");
        
        // Выравнивание кнопок в HBox по центру и нижней границе
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit);
        
        GridPane innerGrid = new GridPane();
        innerGrid.setAlignment(Pos.CENTER);
        innerGrid.add(hbButtons, 0, 0);
        
        Label lblName = new Label("User name:");
        TextField tfName = new TextField();
        Label lblPwd = new Label("Password:");
        PasswordField pfPwd = new PasswordField();       
        
        grid.add(lblName, 0, 0);
        grid.add(tfName, 1, 0);
        grid.add(lblPwd, 0, 1);
        grid.add(pfPwd, 1, 1);
        // grid.add(hbButtons, 0, 2, 2, 1);
        grid.add(innerGrid, 0, 2, 2, 1);
        
        Scene scene = new Scene(grid, 250, 250);
        primaryStage.setTitle("Layout Positioning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
