package pkg5.gridpanetest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Демонстрация расположения Grid Pane
 * @author JavaFX Development Group
 */
public class GridPaneTest extends Application
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
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 10));
        
        grid.setGridLinesVisible(true); // Полезно при отладке размещения
        
        // Категория в колонке 2, строке 1
        
        Text category = new Text("Sales:");
        category.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);
        
        // Заголовок в колонке 3, строке 1
        
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);
        
        // Подзаголовок в колонках 2-3, строке 2
        
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);
        
        // Изображение иконки в колонке 1, строках 1-2
        
        ImageView imageHouse =
            new ImageView(new Image(GridPaneTest.class.getResourceAsStream("graphics/house.jpg")));
        grid.add(imageHouse, 0, 0, 1, 2);
        
        // Левая метка в колонке 1 (внизу), строка 3
        
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2);
        
        // Диаграмма в колонках 2-3, строка 1
        
        ImageView imageChart =
            new ImageView(new Image(GridPaneTest.class.getResourceAsStream("graphics/piechart.jpg")));
        grid.add(imageChart, 1, 2, 2, 1);
        
        // Правая метка в колонке 4 (сверху), строка 3
        
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);
        
        // Добавляем Grid Pane по центру панели Border Pane
        
        primaryStage.setTitle("Grid Pane Sample");
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699");
        
        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);
        
        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
        
        BorderPane root = new BorderPane();
        root.setTop(hbox);
        root.setCenter(grid);
        
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
