/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
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
        
        NumberAxis xAxis = new NumberAxis();  
        xAxis.setLabel("Сигарет на человека в год");
        xAxis.setTickLabelFill(Color.BROWN);
        xAxis.setTickLabelGap(10);        
        xAxis.setTickLength(20);
        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(5000);
        xAxis.setLowerBound(1000);
        xAxis.setMinorTickCount(0); 
        xAxis.setTickUnit(500);       
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Смертей от рака легких\n на 100 тыс. человек");
        yAxis.setTickLabelFill(Color.BROWN);   
        yAxis.setTickLabelGap(10); 
        yAxis.setSide(Side.LEFT);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(300);
        yAxis.setMinorTickCount(0); 
        yAxis.setTickUnit(50);
        
        ScatterChart<Number,Number> chart = new ScatterChart<Number,Number>(xAxis,yAxis);
        chart.setLayoutX(50);
        chart.setLayoutY(10);
        chart.setCursor(Cursor.CROSSHAIR);        
        chart.setStyle("-fx-font:bold 14 Arial; -fx-text-fill:brown;");
        chart.setPrefSize(500, 400);        
        chart.setTitle("Зависимость смертности \n от интенсивности курения");
        chart.setTitleSide(Side.TOP);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        chart.setAlternativeColumnFillVisible(true);
        chart.setAlternativeRowFillVisible(false);
        chart.setHorizontalGridLinesVisible(true);
        chart.setVerticalGridLinesVisible(true);
        
        XYChart.Series seriesM= new XYChart.Series();
        seriesM.setName("Мужчины");
        seriesM.getData().addAll(new XYChart.Data(2000,75), new XYChart.Data(2300,60), new XYChart.Data(2500,100), new XYChart.Data(3000,155), new XYChart.Data(3500,140), new XYChart.Data(4000,175),new XYChart.Data(4200,180));        
        
        XYChart.Series seriesF= new XYChart.Series();
        seriesF.setName("Женщины");
        seriesF.getData().addAll(new XYChart.Data(2000,60), new XYChart.Data(2300,78), new XYChart.Data(2500,120), new XYChart.Data(3000,125), new XYChart.Data(3500,160), new XYChart.Data(4000,150),new XYChart.Data(4200,210));        
        
        chart.getData().addAll(seriesM, seriesF);        
        
        root.getChildren().addAll(chart);        
    }
}
