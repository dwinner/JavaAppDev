/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


public class JavaFXApplication extends Application {    
 
 
 static class TextFieldCell extends TreeCell<java.lang.String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            TextField textField = new TextField();
            if (item != null) {
                textField.setPrefSize(200,20);
                if (item.equals("JavaFXApplicationTreeView"))textField.setText(item);
                if (item.equals("javafxapplication"))textField.setText(item);
                if (item.equals("Пакеты исходных файлов"))textField.setText(item);                               
                if (item.equals("JavaFXApplication.java"))textField.setText(item);
                setGraphic(textField);
            }
        }
    }
 
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
        
Image rootimg=new Image(this.getClass().getResource("rootimg.png").toString());
        final ImageView rootimv=new ImageView(rootimg);        
        rootimv.setFitHeight(20);
        rootimv.setFitWidth(20);  
        
Image packageimg=new Image(this.getClass().getResource("packageimg.png").toString());
        final ImageView packageimv=new ImageView(packageimg);        
        packageimv.setFitHeight(20);
        packageimv.setFitWidth(20);   
        
Image packagesimg=new Image(this.getClass().getResource("packagesimg.png").toString());
        final ImageView packagesimv=new ImageView(packagesimg);        
        packagesimv.setFitHeight(20);
        packagesimv.setFitWidth(20);   
        
Image sourceimg=new Image(this.getClass().getResource("sourceimg.png").toString());
        final ImageView sourceimv=new ImageView(sourceimg);        
        sourceimv.setFitHeight(20);
        sourceimv.setFitWidth(20);          
        
 TreeItem<String> rootTree = new TreeItem<String>("JavaFXApplicationTreeView");
 rootTree.setExpanded(true);
 rootTree.setGraphic(rootimv);
 
 TreeItem<String> itemPS=new TreeItem<String>("Пакеты исходных файлов");
 itemPS.setExpanded(true);
 itemPS.setGraphic(packagesimv);
 TreeItem<String> itemP=new TreeItem<String>("javafxapplication");
 itemP.setExpanded(true);
 itemP.setGraphic(packageimv);
 final TreeItem<String> itemS=new TreeItem<String>("JavaFXApplication.java"); 
 itemS.setGraphic(sourceimv);
 itemP.getChildren().addAll(itemS);
 itemPS.getChildren().addAll(itemP);
  
 rootTree.getChildren().addAll(itemPS);
 
        TreeView<String> treeView = new TreeView<String>(rootTree);        
        treeView.setLayoutX(10);
        treeView.setLayoutY(10); 
        treeView.setCursor(Cursor.CLOSED_HAND);        
        treeView.setStyle("-fx-border-width:3pt;-fx-border-color:#f0e68c;-fx-font: 14pt Georgia;");
        treeView.setPrefSize(300, 200);
        /*treeView.setCellFactory(new Callback<TreeView<java.lang.String>,TreeCell<java.lang.String>>() {
            @Override
                public TreeCell<java.lang.String> call(TreeView<java.lang.String> p) {
                    return new TextFieldCell();
            } });*/
   treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
    public void changed (ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> old_val, TreeItem<String> new_val) {
      if(new_val==itemS){       
    System.out.println("Редактирование исходного кода");                
       } 
       } });
   treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 
   
   root.getChildren().add(treeView);
    }
}
