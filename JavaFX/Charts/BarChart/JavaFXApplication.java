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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
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
        
        ObservableList<String> category =FXCollections.observableArrayList("2008","2009","2010");
        CategoryAxis xAxis = new CategoryAxis(category);  
        xAxis.setLabel("Год");
        xAxis.setTickLabelFill(Color.BROWN);
        xAxis.setTickLabelGap(10);        
        xAxis.setTickLength(20);       
        xAxis.setGapStartAndEnd(true);
      
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Миллиардов долларов США");
        yAxis.setTickLabelFill(Color.BROWN);   
        yAxis.setTickLabelGap(10); 
        yAxis.setSide(Side.LEFT);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(600.0);
        yAxis.setMinorTickCount(0);
        yAxis.setTickUnit(100.0);
        
        BarChart<String,Number> chart = new BarChart<String,Number>(xAxis,yAxis);
        chart.setLayoutX(50);
        chart.setLayoutY(10);
        chart.setCursor(Cursor.CROSSHAIR);        
        chart.setStyle("-fx-font:bold 14 Arial; -fx-text-fill:brown;");
        chart.setPrefSize(500, 400);        
        chart.setTitle("Внешний долг и резервы России");
        chart.setTitleSide(Side.TOP);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        chart.setAlternativeColumnFillVisible(false);
        chart.setAlternativeRowFillVisible(false);
        chart.setHorizontalGridLinesVisible(true);
        chart.setVerticalGridLinesVisible(false); 
        chart.setBarGap(5);
        chart.setCategoryGap(50);
        
        XYChart.Series seriesD= new XYChart.Series();
        seriesD.setName("Консолидированный внешний долг России");
        seriesD.getData().addAll(new XYChart.Data(category.get(0),463.9), new XYChart.Data(category.get(1),480.5), new XYChart.Data(category.get(2),467.2));
        
        XYChart.Series seriesC= new XYChart.Series();
        seriesC.setName("Международные резервы России");
        seriesC.getData().addAll(new XYChart.Data(category.get(0),478.8), new XYChart.Data(category.get(1),426.3), new XYChart.Data(category.get(2),439.5));        
        
        chart.getData().addAll(seriesD, seriesC);
        
        root.getChildren().addAll(chart);        
    }
}
