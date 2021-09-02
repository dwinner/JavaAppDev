package scattercharttest;

import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Диаграмма группы связанных точек.
 * <p/>
 * @author oracle_pr1
 */
public class ScatterChartTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Scatter Chart Sample");
        
        // Оси
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);        
        xAxis.setLabel("Age (years)");
        yAxis.setLabel("Returns to date");
        sc.setTitle("Investment Overview");
        
        // Создание и применение визуального эффекта Drop Shadow
        final DropShadow shadow = new DropShadow();
        shadow.setOffsetX(2);
        shadow.setColor(Color.GRAY);
        sc.setEffect(shadow);

        // Первая серия точек
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Option 1");
        series1.getData().add(new XYChart.Data<Number, Number>(4.2, 193.2));
        series1.getData().add(new XYChart.Data<Number, Number>(2.8, 33.6));
        series1.getData().add(new XYChart.Data<Number, Number>(6.2, 24.8));
        series1.getData().add(new XYChart.Data<Number, Number>(1, 14));
        series1.getData().add(new XYChart.Data<Number, Number>(1.2, 26.4));
        series1.getData().add(new XYChart.Data<Number, Number>(4.4, 114.4));
        series1.getData().add(new XYChart.Data<Number, Number>(8.5, 323));
        series1.getData().add(new XYChart.Data<Number, Number>(6.9, 289.8));
        series1.getData().add(new XYChart.Data<Number, Number>(9.9, 287.1));
        series1.getData().add(new XYChart.Data<Number, Number>(0.9, -9));
        series1.getData().add(new XYChart.Data<Number, Number>(3.2, 150.8));
        series1.getData().add(new XYChart.Data<Number, Number>(4.8, 20.8));
        series1.getData().add(new XYChart.Data<Number, Number>(7.3, -42.3));
        series1.getData().add(new XYChart.Data<Number, Number>(1.8, 81.4));
        series1.getData().add(new XYChart.Data<Number, Number>(7.3, 110.3));
        series1.getData().add(new XYChart.Data<Number, Number>(2.7, 41.2));

        // Вторая серия точек
        /*
         * XYChart.Series<Number, Number> series2 = new XYChart.Series<>(); series2.setName("Mutual
         * funds"); series2.getData().add(new XYChart.Data<Number, Number>(5.2, 229.2));
         * series2.getData().add(new XYChart.Data<Number, Number>(2.4, 37.6));
         * series2.getData().add(new XYChart.Data<Number, Number>(3.2, 49.8));
         * series2.getData().add(new XYChart.Data<Number, Number>(1.8, 134));
         * series2.getData().add(new XYChart.Data<Number, Number>(3.2, 236.2));
         * series2.getData().add(new XYChart.Data<Number, Number>(7.4, 114.1));
         * series2.getData().add(new XYChart.Data<Number, Number>(3.5, 323));
         * series2.getData().add(new XYChart.Data<Number, Number>(9.3, 29.9));
         * series2.getData().add(new XYChart.Data<Number, Number>(8.1, 287.4));
         */

        sc.getData().addAll(Arrays.asList(series1));
        Scene scene = new Scene(new Group());
        scene.getStylesheets().add("scattercharttest/Chart.css");
        final VBox vBox = new VBox();
        final HBox hBox = new HBox();

        final Button add = new Button("Add Series");

        // Генерирование новой серии значений
        add.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (sc.getData() == null || sc.getData().isEmpty())
                {
                    sc.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
                    ScatterChart.Series<Number, Number> series = new ScatterChart.Series<>();
                    series.setName("Option " + (sc.getData().size() + 1));
                    for (int i = 0; i < 100; i++)
                    {
                        series.getData().add(
                           new ScatterChart.Data<Number, Number>(Math.random() * 100,
                           Math.random() * 500));
                    }
                    sc.getData().add(series);
                }
            }
        });

        final Button remove = new Button("Remove Series");

        // Удаление серии значений
        remove.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (sc.getData() != null && !sc.getData().isEmpty())
                {
                    sc.getData().remove((int) (Math.random() * (sc.getData().size() - 1)));
                    // sc.setData(null);
                }
            }
        });

        hBox.setSpacing(10);
        hBox.getChildren().addAll(add, remove);

        vBox.getChildren().addAll(sc, hBox);
        hBox.setPadding(new Insets(10, 10, 10, 50));
        ((Group) scene.getRoot()).getChildren().add(vBox);

        stage.setScene(scene);
        stage.show();
    }
}
