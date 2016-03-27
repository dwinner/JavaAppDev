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
import javafx.scene.chart.BubbleChart;
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
        
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Год");
        xAxis.setTickLabelFill(Color.BROWN);
        xAxis.setTickLabelGap(10);        
        xAxis.setTickLength(20);       
        xAxis.setAutoRanging(false);
        xAxis.setUpperBound(2011);
        xAxis.setLowerBound(2007);
        xAxis.setMinorTickCount(0);
        xAxis.setTickUnit(1);        
      
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Миллионов абонентов");
        yAxis.setTickLabelFill(Color.BROWN);   
        yAxis.setTickLabelGap(10); 
        yAxis.setSide(Side.LEFT);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(80.0);
        yAxis.setMinorTickCount(0);
        yAxis.setTickUnit(10.0);
        yAxis.setLowerBound(30.0);       
                
        BubbleChart<Number,Number> chart = new BubbleChart<Number,Number>(xAxis,yAxis);
        chart.setLayoutX(50);
        chart.setLayoutY(10);
        chart.setCursor(Cursor.CROSSHAIR);        
        chart.setStyle("-fx-font:bold 14 Arial; -fx-text-fill:brown;");
        chart.setPrefSize(400, 400);        
        chart.setTitle("Рынок сотовой связи России");
        chart.setTitleSide(Side.TOP);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        chart.setAlternativeColumnFillVisible(false);
        chart.setAlternativeRowFillVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(true);          
        
        XYChart.Series seriesMTC= new XYChart.Series();
        seriesMTC.setName("MTC");
        XYChart.Data dataMTC1=new XYChart.Data(2008, 62, 0.62);
        XYChart.Data dataMTC2=new XYChart.Data(2009, 65, 0.65);
        XYChart.Data dataMTC3=new XYChart.Data(2010,71, 0.71);        
        seriesMTC.getData().addAll(dataMTC1,dataMTC2,dataMTC3);
        
        XYChart.Series seriesB= new XYChart.Series();
        seriesB.setName("Билайн");
        XYChart.Data dataB1=new XYChart.Data(2008, 45, 0.45);
        XYChart.Data dataB2=new XYChart.Data(2009, 49, 0.49);
        XYChart.Data dataB3=new XYChart.Data(2010, 52, 0.52);        
        seriesB.getData().addAll(dataB1, dataB2, dataB3);        
        
        XYChart.Series seriesM= new XYChart.Series();
        seriesM.setName("Мегафон");
        XYChart.Data dataM1=new XYChart.Data(2008, 42, 0.42);
        XYChart.Data dataM2=new XYChart.Data(2009, 44, 0.44);
        XYChart.Data dataM3=new XYChart.Data(2010, 56, 0.56);        
        seriesM.getData().addAll(dataM1, dataM2, dataM3);        
        
        chart.getData().addAll(seriesMTC, seriesB, seriesM);
        dataMTC1.getNode().setScaleY(5);
        dataMTC2.getNode().setScaleY(5);
        dataMTC3.getNode().setScaleY(5);
        dataB1.getNode().setScaleY(5);
        dataB2.getNode().setScaleY(5);
        dataB3.getNode().setScaleY(5);
        dataM1.getNode().setScaleY(5);
        dataM2.getNode().setScaleY(5);
        dataM3.getNode().setScaleY(5);
        
        root.getChildren().addAll(chart);        
    }
}
