/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {     
  
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage stage) {
        final Stage primaryStage=stage;
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();        
        Scene scene = new Scene(root, 600, 500, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ObservableList<String> category =FXCollections.observableArrayList("Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь");
        CategoryAxis xAxis = new CategoryAxis(category);  
        xAxis.setLabel("Месяцы");
        xAxis.setTickLabelFill(Color.BROWN);
        xAxis.setTickLabelGap(10);        
        xAxis.setTickLength(20);
        xAxis.setEndMargin(30);
        xAxis.setGapStartAndEnd(false);
        xAxis.setStartMargin(30);        
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Температура");
        yAxis.setTickLabelFill(Color.BROWN);   
        yAxis.setTickLabelGap(10); 
        yAxis.setSide(Side.LEFT);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(35);
        yAxis.setMinorTickCount(3);       
        
        LineChart<String,Number> chart = new LineChart<String,Number>(xAxis,yAxis);
        chart.setLayoutX(50);
        chart.setLayoutY(10);
        chart.setCursor(Cursor.CROSSHAIR);        
        chart.setStyle("-fx-font:bold 14 Arial; -fx-text-fill:brown;");
        chart.setPrefSize(500, 400);        
        chart.setTitle("Климат Хургады");
        chart.setTitleSide(Side.TOP);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        chart.setAlternativeColumnFillVisible(true);
        chart.setAlternativeRowFillVisible(false);
        chart.setHorizontalGridLinesVisible(true);
        chart.setVerticalGridLinesVisible(true);
        chart.setCreateSymbols(false);
        
        XYChart.Series seriesAirTem= new XYChart.Series();
        seriesAirTem.setName("Температура воздуха");
        XYChart.Data data1=new XYChart.Data(category.get(0),21);
        Text text1=new Text("21");
        text1.setFill(Color.BROWN);
        data1.setNode(text1);        
        
        XYChart.Data data7=new XYChart.Data(category.get(6),33);
        Text text7=new Text("33");
        text7.setFill(Color.BROWN);
        data7.setNode(text7);
        
        XYChart.Data data12=new XYChart.Data(category.get(11),23);
        Text text12=new Text("23");
        text12.setFill(Color.BROWN);
        data12.setNode(text12);
        
        seriesAirTem.getData().addAll(data1, new XYChart.Data(category.get(1),22), new XYChart.Data(category.get(2),24), new XYChart.Data(category.get(3),27), new XYChart.Data(category.get(4),30), new XYChart.Data(category.get(5),32), data7, new XYChart.Data(category.get(7),33), new XYChart.Data(category.get(8),31), new XYChart.Data(category.get(9),29), new XYChart.Data(category.get(10),26),data12);
        
        XYChart.Series seriesWaterTem= new XYChart.Series();
        seriesWaterTem.setName("Температура воды");
        seriesWaterTem.getData().addAll(new XYChart.Data(category.get(0),20), new XYChart.Data(category.get(1),18), new XYChart.Data(category.get(2),20), new XYChart.Data(category.get(3),23), new XYChart.Data(category.get(4),26), new XYChart.Data(category.get(5),29), new XYChart.Data(category.get(6),31), new XYChart.Data(category.get(7),30), new XYChart.Data(category.get(8),29), new XYChart.Data(category.get(9),26), new XYChart.Data(category.get(10),24), new XYChart.Data(category.get(11),22));        
        
        chart.getData().addAll(seriesWaterTem, seriesAirTem);        
        
        root.getChildren().addAll(chart);        
    }
}
