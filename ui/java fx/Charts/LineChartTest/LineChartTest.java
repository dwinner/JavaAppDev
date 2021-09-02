package linecharttest;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Линейная диаграмма.
 * <p/>
 * @author oracle_pr1
 */
public class LineChartTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Line Chart Sample");
        
        // Определение осей
        // final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        // Расположение горизонтальной шкалы
        // xAxis.setSide(Side.TOP);
        yAxis.setLabel("Statistics values");
        
        // Создание диаграммы
        final LineChart<String,Number> lineChart =
           new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Stock Monitoring, 2010");
        // Управление видимостью контрольных точек
        // lineChart.setCreateSymbols(false);
        
        // Определение значений для линейной диаграммы
        
        XYChart.Series<String,Number> series1 = new XYChart.Series<>();
        series1.setName("Portfolio 1");
        
        // Заполнение первой серии значений
        series1.getData().add(new XYChart.Data<String,Number>("Jan", 23));
        series1.getData().add(new XYChart.Data<String,Number>("Feb", 14));
        series1.getData().add(new XYChart.Data<String,Number>("Mar", 15));
        series1.getData().add(new XYChart.Data<String,Number>("Apr", 24));
        series1.getData().add(new XYChart.Data<String,Number>("May", 34));
        series1.getData().add(new XYChart.Data<String,Number>("Jun", 36));
        series1.getData().add(new XYChart.Data<String,Number>("Jul", 22));
        series1.getData().add(new XYChart.Data<String,Number>("Aug", 45));
        series1.getData().add(new XYChart.Data<String,Number>("Sep", 43));
        series1.getData().add(new XYChart.Data<String,Number>("Oct", 17));
        series1.getData().add(new XYChart.Data<String,Number>("Nov", 29));
        series1.getData().add(new XYChart.Data<String,Number>("Dec", 25));
        
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series2.setName("Portfolio 2");
        
        // Заполнение второй серии значений
        series2.getData().add(new XYChart.Data<String,Number>("Jan", 33));
        series2.getData().add(new XYChart.Data<String,Number>("Feb", 34));
        series2.getData().add(new XYChart.Data<String,Number>("Mar", 25));
        series2.getData().add(new XYChart.Data<String,Number>("Apr", 44));
        series2.getData().add(new XYChart.Data<String,Number>("May", 39));
        series2.getData().add(new XYChart.Data<String,Number>("Jun", 16));
        series2.getData().add(new XYChart.Data<String,Number>("Jul", 55));
        series2.getData().add(new XYChart.Data<String,Number>("Aug", 54));
        series2.getData().add(new XYChart.Data<String,Number>("Sep", 48));
        series2.getData().add(new XYChart.Data<String,Number>("Oct", 27));
        series2.getData().add(new XYChart.Data<String,Number>("Nov", 37));
        series2.getData().add(new XYChart.Data<String,Number>("Dec", 29));
        
        XYChart.Series<String,Number> series3 = new XYChart.Series<>();
        series3.setName("Portfolio 3");
        
        // Заполнение третей серии значений
        series3.getData().add(new XYChart.Data<String,Number>("Jan", 44));
        series3.getData().add(new XYChart.Data<String,Number>("Feb", 35));
        series3.getData().add(new XYChart.Data<String,Number>("Mar", 36));
        series3.getData().add(new XYChart.Data<String,Number>("Apr", 33));
        series3.getData().add(new XYChart.Data<String,Number>("May", 31));
        series3.getData().add(new XYChart.Data<String,Number>("Jun", 26));
        series3.getData().add(new XYChart.Data<String,Number>("Jul", 22));
        series3.getData().add(new XYChart.Data<String,Number>("Aug", 25));
        series3.getData().add(new XYChart.Data<String,Number>("Sep", 43));
        series3.getData().add(new XYChart.Data<String,Number>("Oct", 44));
        series3.getData().add(new XYChart.Data<String,Number>("Nov", 45));
        series3.getData().add(new XYChart.Data<String,Number>("Dec", 44));
        
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().addAll(Arrays.asList(series1, series2, series3));
        
        stage.setScene(scene);
        stage.show();
    }
}
