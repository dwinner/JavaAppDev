/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


public class JavaFXApplication extends Application {   
  
  private ListView<String> listViewH;
  private ListView<String> listViewU;
  private ListView<String> listViewW;   
  
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BEIGE);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        ObservableList<String> categories = FXCollections.observableArrayList(
        "Бытовая Техника", "Посуда", "Хозтовары");
        ListView<String> listViewC = new ListView<String>(categories);        
        listViewC.setLayoutX(10);
        listViewC.setLayoutY(10); 
        listViewC.setCursor(Cursor.OPEN_HAND);
        final DropShadow effect=new DropShadow();
        effect.setOffsetX(10);
        effect.setOffsetY(10);
        listViewC.setEffect(effect);
        listViewC.setStyle("-fx-border-width:3pt;-fx-border-color:navy;-fx-font:bold 16pt Georgia;");
        listViewC.setPrefSize(300, 170);
        listViewC.setTooltip(new Tooltip("Выберите категорию товара"));
        listViewC.setOrientation(Orientation.HORIZONTAL);        
        listViewC.setCellFactory(new Callback<ListView<String>,ListCell<String>>() {
                public ListCell<String> call(ListView<String> p) {
                    final Button btn = new Button();
                    btn.setEffect(effect);
                    btn.setStyle("-fx-background-color:#66ccff;");
                    btn.setPrefSize(130, 50);
                    btn.setCursor(Cursor.NONE);
                    btn.setWrapText(true);
                    final ListCell<String> cell = new ListCell<String>() {    
                    @Override public void updateItem(String item, 
                        boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                btn.setText(item);
                                this.setGraphic(btn);                  
                        } }};
                return cell;
            } });
    listViewC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    public void changed (ObservableValue<? extends String> ov, String old_val, String new_val) {
       if(new_val.equals("Бытовая Техника")){
           listViewH.setVisible(true);
           listViewU.setVisible(false);
           listViewW.setVisible(false);
       } 
       if(new_val.equals("Посуда")){          
           listViewU.setVisible(true);
           listViewH.setVisible(false);
           listViewW.setVisible(false);
       } 
       if(new_val.equals("Хозтовары")){          
           listViewW.setVisible(true);
           listViewH.setVisible(false);
           listViewU.setVisible(false);
       }} });
                
        ObservableList<String> goodsH = FXCollections.observableArrayList(
        "Вентилятор", "Мясорубка", "Насос");
        listViewH = new ListView<String>(goodsH);        
        listViewH.setLayoutX(10);
        listViewH.setLayoutY(220); 
        listViewH.setCursor(Cursor.CROSSHAIR);
        listViewH.setEffect(effect);
        listViewH.setStyle("-fx-border-width:3pt;-fx-border-color:navy;-fx-font:bold 16pt Georgia;");
        listViewH.setPrefSize(200, 150);
        listViewH.setOrientation(Orientation.VERTICAL);
        listViewH.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewH.setVisible(false);        
        
        ObservableList<String> goodsU = FXCollections.observableArrayList(
        "Чайник", "Кастрюля", "Сковородка");
        listViewU = new ListView<String>(goodsU);        
        listViewU.setLayoutX(10);
        listViewU.setLayoutY(220); 
        listViewU.setCursor(Cursor.CROSSHAIR);
        listViewU.setEffect(effect);
        listViewU.setStyle("-fx-border-width:3pt;-fx-border-color:navy;-fx-font:bold 16pt Georgia;");
        listViewU.setPrefSize(200, 150);
        listViewU.setOrientation(Orientation.VERTICAL); 
        listViewU.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewU.setVisible(false);        
        
        ObservableList<String> goodsW = FXCollections.observableArrayList(
        "Шланг", "Лопата", "Весы");
        listViewW = new ListView<String>(goodsW);        
        listViewW.setLayoutX(10);
        listViewW.setLayoutY(220); 
        listViewW.setCursor(Cursor.CROSSHAIR);
        listViewW.setEffect(effect);
        listViewW.setStyle("-fx-border-width:3pt;-fx-border-color:navy;-fx-font:bold 16pt Georgia;");
        listViewW.setPrefSize(200, 150);
        listViewW.setOrientation(Orientation.VERTICAL); 
        listViewW.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewW.setVisible(false);         
        
        root.getChildren().add(listViewC);
        root.getChildren().add(listViewH);
        root.getChildren().add(listViewU);
        root.getChildren().add(listViewW);         
    }
}
