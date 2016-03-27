package pkg8.anchorpanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Демонстрация расположения Anchor Pane.
 * <p/>
 * @author JavaFx Development Group
 */
public class AnchorPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Создаем Border Pane
        BorderPane border = new BorderPane();

        // Создаем AnchorPane
        AnchorPane anchorPane = new AnchorPane();

        // Создаем HBox из кнопок
        Button buttonSave = new Button("Save");
        Button buttonCancel = new Button("Cancel");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(buttonSave, buttonCancel);

        // Создаем Grid Pane
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true); // Для отладки
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 10));

        Text category = new Text("Sales:");
        category.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        ImageView imageHouse = new ImageView(new Image(AnchorPaneTest.class.getResourceAsStream(
            "graphics/house.jpeg")));
        grid.add(imageHouse, 0, 0, 1, 2);

        Text goodPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodPercent, VPos.BOTTOM);
        grid.add(goodPercent, 0, 2);

        ImageView imageChart = new ImageView(new Image(AnchorPaneTest.class.getResourceAsStream(
            "graphics/piechart.jpeg")));
        grid.add(imageChart, 1, 2, 2, 1);

        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);

        // Создаем HBox из 2-х кнопок
        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15, 12, 15, 12));
        hbox2.setSpacing(10);
        hbox2.setStyle("-fx-background-color: #336699");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);

        hbox2.getChildren().addAll(buttonCurrent, buttonProjected);

        border.setTop(hbox2);

        // Собираем все в Anchor Pane
        anchorPane.getChildren().addAll(grid, hbox);
        AnchorPane.setBottomAnchor(hbox, 8.0);
        AnchorPane.setRightAnchor(hbox, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);

        border.setCenter(anchorPane);
        
        primaryStage.setTitle("Anchor Pane Sample");
        primaryStage.setScene(new Scene(border, 640, 480));
        primaryStage.show();
    }
}
