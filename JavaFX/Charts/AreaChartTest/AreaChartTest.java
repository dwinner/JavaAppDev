package areacharttest;

import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * Диаграмма площадей
 * <p/>
 * @author oracle_pr1
 */
public class AreaChartTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Area Chart Sample");
        
        // Горизонтальная шкала
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(30);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickCount(0); // xAxis.setMinorTickVisible(false);
        
        // Вертикальная шкала
        final NumberAxis yAxis = new NumberAxis(-5, 27, 5);        
        yAxis.setMinorTickLength(yAxis.getTickLength());
        yAxis.setForceZeroInRange(false);
        
        final AreaChart<Number,Number> ac = new AreaChart<>(xAxis, yAxis);
        ac.setTitle("Temperature Monitoring (in Degrees C)");
        
        // Данные для первой площади
        XYChart.Series<Number,Number> seriesApril= new XYChart.Series<>();
        seriesApril.setName("April");
        seriesApril.getData().add(new XYChart.Data<Number,Number>(1, 4));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(3, 10));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(6, 15));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(9, 8));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(12, 5));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(15, 18));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(18, 15));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(21, 13));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(24, 19));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(27, 21));
        seriesApril.getData().add(new XYChart.Data<Number,Number>(30, 21));
        
        // Данные для второй площади
        XYChart.Series<Number,Number> seriesMay = new XYChart.Series<>();
        seriesMay.setName("May");
        seriesMay.getData().add(new XYChart.Data<Number,Number>(1, 20));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(3, 15));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(6, 13));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(9, 12));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(12, 14));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(15, 18));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(18, 25));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(21, 25));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(24, 23));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(27, 26));
        seriesMay.getData().add(new XYChart.Data<Number,Number>(31, 26));
        
        // Данные для третьей площади
        XYChart.Series<Number,Number> seriesMarch = new XYChart.Series<>();
        seriesMarch.setName("March");
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(0, -2));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(3, -4));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(6, 0));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(9, 5));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(12, -4));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(15, 6));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(18, 8));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(21, 14));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(24, 4));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(27, 6));
        seriesMarch.getData().add(new XYChart.Data<Number,Number>(31, 6));   
        
        Scene scene  = new Scene(ac, 800, 600);
        scene.getStylesheets().add("areacharttest/Chart.css");
        ac.getData().addAll(Arrays.asList(seriesMarch, seriesApril, seriesMay));
        stage.setScene(scene);
        stage.show();
    }
}
