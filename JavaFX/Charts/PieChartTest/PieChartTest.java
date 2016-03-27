package piecharttest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Круговая диаграмма.
 * <p/>
 * @author oracle_pr1
 */
public class PieChartTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);

        // Данные для диаграммы
        ObservableList<PieChart.Data> pieChartData =
           FXCollections.observableArrayList(
           new PieChart.Data("Grapefruit", 13),
           new PieChart.Data("Oranges", 25),
           new PieChart.Data("Plums", 10),
           new PieChart.Data("Pears", 22),
           new PieChart.Data("Apples", 30));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        // Изменение позиции меток и надписи
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);

        // Изменение точки отсчета частей диаграммы со 180 градусов
        chart.setStartAngle(180);
        chart.setClockwise(false);

        // Обработка событий мыши для диаграммы
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData())
        {
            /*data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    caption.setTranslateX(mouseEvent.getSceneX());
                    caption.setTranslateY(mouseEvent.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });*/

            data.getNode().addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED_TARGET)
                    {
                        caption.setVisible(true);
                    }
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED_TARGET)
                    {
                        caption.setVisible(false);
                    }
                    caption.setTranslateX(mouseEvent.getSceneX());
                    caption.setTranslateY(mouseEvent.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }

        ((Group) scene.getRoot()).getChildren().addAll(chart, caption);
        stage.setScene(scene);
        stage.show();
    }
}
