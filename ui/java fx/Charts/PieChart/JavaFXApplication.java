/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
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
             
       final ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Доллар США",45.9),                
                new PieChart.Data("Евро",42.5),
                new PieChart.Data("Японская иена",1.6),
                new PieChart.Data("Фунт стерлингов",9.2),                
                new PieChart.Data("Канадский доллар",0.8));
        final PieChart chart = new PieChart(pieChartData);
        chart.setLayoutX(50);
        chart.setLayoutY(10);
        chart.setCursor(Cursor.CROSSHAIR);        
        chart.setStyle("-fx-font:bold 14 Arial; -fx-text-fill:brown;");
        chart.setPrefSize(500, 400);
        chart.setAnimated(true);
        chart.setTitle("Распределение валютных активов\n Банка России по валютам в 2010 г.");
        chart.setTitleSide(Side.TOP);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);
        chart.setClockwise(true);
        chart.setLabelsVisible(true);
        chart.setLabelLineLength(20);        
        chart.setStartAngle(150);   
        final Popup popup=new Popup();
        popup.setAutoHide(true);
        final Label label = new Label("");       
        label.setStyle("-fx-font: bold 20 Arial;-fx-text-fill:brown");
        popup.getContent().addAll(label);
for (final PieChart.Data data : chart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {                
                label.setText(String.valueOf(data.getPieValue()) + "%");  
                popup.setX(e.getScreenX());
                popup.setY(e.getScreenY());
                popup.show(primaryStage);                  
             }
        });}
    chart.addEventHandler(MouseEvent.DRAG_DETECTED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {                
                chart.setStartAngle(chart.getStartAngle()+2);                
             }
        });
                
        root.getChildren().addAll(chart);        
    }
}
