package barcharttest;

import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Столбчатая диаграмма.
 * <p/>
 * @author oracle_pr1
 */
public class BarChartTest extends Application
{
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Bar Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Country");
        yAxis.setAnimated(false);
        /*
         * final CategoryAxis xAxis = new CategoryAxis(); final NumberAxis yAxis = new NumberAxis();
         * final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis); bc.setBarGap(3);
         * bc.setCategoryGap(20); bc.setTitle("Country Summary"); xAxis.setLabel("Country");
         * yAxis.setLabel("Value");
         */

        XYChart.Series<Number, String> series1 = new XYChart.Series<>();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<Number, String>(25601.34, austria));
        series1.getData().add(new XYChart.Data<Number, String>(20148.82, brazil));
        series1.getData().add(new XYChart.Data<Number, String>(10000, france));
        series1.getData().add(new XYChart.Data<Number, String>(35407.15, italy));
        series1.getData().add(new XYChart.Data<Number, String>(12000, usa));

        XYChart.Series<Number, String> series2 = new XYChart.Series<>();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<Number, String>(57401.85, austria));
        series2.getData().add(new XYChart.Data<Number, String>(41941.19, brazil));
        series2.getData().add(new XYChart.Data<Number, String>(45263.37, france));
        series2.getData().add(new XYChart.Data<Number, String>(117320.16, italy));
        series2.getData().add(new XYChart.Data<Number, String>(14845.27, usa));

        XYChart.Series<Number, String> series3 = new XYChart.Series<>();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<Number, String>(45000.65, austria));
        series3.getData().add(new XYChart.Data<Number, String>(44835.76, brazil));
        series3.getData().add(new XYChart.Data<Number, String>(18722.18, france));
        series3.getData().add(new XYChart.Data<Number, String>(17557.31, italy));
        series3.getData().add(new XYChart.Data<Number, String>(92633.68, usa));

        // Анимация данных в диаграмме
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                for (XYChart.Series<Number, String> series : bc.getData())
                {
                    for (XYChart.Data<Number, String> data : series.getData())
                    {
                        data.setXValue(Math.random() * 1000);
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(Arrays.asList(series1, series2, series3));
        stage.setScene(scene);
        stage.show();
    }
}
