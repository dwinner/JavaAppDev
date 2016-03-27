package bubblecharttest;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Пузырьковая диаграмма.
 * <p/>
 * @author oracle_pr1
 */
public class BubbleChartTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Bubble Chart Sample");
        final NumberAxis xAxis = new NumberAxis(1, 53, 4);
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        final NumberAxis yAxis = new NumberAxis(0, 80, 10);
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        // Форматирование меток делений
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "$ ", null));

        final BubbleChart<Number, Number> blc = new BubbleChart<>(xAxis, yAxis);
        xAxis.setLabel("Week");
        yAxis.setLabel("Product Budget");
        blc.setTitle("Budget Monitoring");

        // Первый набор значений.
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Product 1");
        /*
         * Третий параметр - дополнительное процентное значение.
         * Радиус в 7.5 означает, что продукт использует 75% бюджета.
         */
        series1.getData().add(new XYChart.Data<Number, Number>(3, 35, 2));
        series1.getData().add(new XYChart.Data<Number, Number>(12, 60, 1.8));
        series1.getData().add(new XYChart.Data<Number, Number>(15, 15, 7));
        series1.getData().add(new XYChart.Data<Number, Number>(22, 30, 2.5));
        series1.getData().add(new XYChart.Data<Number, Number>(28, 20, 1));
        series1.getData().add(new XYChart.Data<Number, Number>(35, 41, 5.5));
        series1.getData().add(new XYChart.Data<Number, Number>(42, 17, 9));
        series1.getData().add(new XYChart.Data<Number, Number>(49, 30, 1.8));

        // Второй набор значений.
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Product 2");
        series2.getData().add(new XYChart.Data<Number, Number>(8, 15, 2));
        series2.getData().add(new XYChart.Data<Number, Number>(13, 23, 1));
        series2.getData().add(new XYChart.Data<Number, Number>(15, 45, 3));
        series2.getData().add(new XYChart.Data<Number, Number>(24, 30, 4.5));
        series2.getData().add(new XYChart.Data<Number, Number>(38, 78, 1));
        series2.getData().add(new XYChart.Data<Number, Number>(40, 41, 7.5));
        series2.getData().add(new XYChart.Data<Number, Number>(45, 57, 2));
        series2.getData().add(new XYChart.Data<Number, Number>(47, 23, 3.8));

        Scene scene = new Scene(blc);
        blc.getData().addAll(Arrays.asList(series1, series2));
        // Отображение альтернативных столбцов
        blc.setAlternativeColumnFillVisible(true);
        blc.setAlternativeRowFillVisible(false);
        stage.setScene(scene);
        stage.show();
    }
}
