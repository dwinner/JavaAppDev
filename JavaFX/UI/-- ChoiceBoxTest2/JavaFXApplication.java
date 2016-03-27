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
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {   
    
    private ImageView imvR;
    private ImageView imvU;
    private ImageView imvG;
  
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
        ObservableList<String> country = FXCollections.observableArrayList(
        "Россия","США","Великобритания");
        ChoiceBox<String> choice = new ChoiceBox<String>(country);        
        choice.setLayoutX(10);
        choice.setLayoutY(10); 
        choice.setBlendMode(BlendMode.HARD_LIGHT);        
        choice.setCursor(Cursor.CLOSED_HAND);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        choice.setEffect(effect);           
        choice.setStyle("-fx-text-fill:#000000;-fx-border-width:5pt;-fx-border-color:#d2691e;-fx-font:bold italic 14pt Georgia;");
        choice.setPrefSize(200, 50);
        choice.setTooltip(new Tooltip("Выберите страну"));          
        choice.getSelectionModel().selectFirst();    
        choice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    public void changed (ObservableValue<? extends String> ov, String old_val, String new_val) {
       if(new_val.equals("Россия")){
           imvR.setVisible(true);
           imvU.setVisible(false);
           imvG.setVisible(false);
       } 
       if(new_val.equals("США")){
           imvU.setVisible(true);
           imvR.setVisible(false);
           imvG.setVisible(false);
       } 
       if(new_val.equals("Великобритания")){
           imvG.setVisible(true);
           imvR.setVisible(false);
           imvU.setVisible(false);
       } 
       } });
        
        Image imR=new Image(this.getClass().getResource("imageR.jpeg").toString());
        imvR=new ImageView(imR);
        imvR.setPreserveRatio(true);
        imvR.setFitHeight(200);
        imvR.setFitWidth(200); 
        imvR.setLayoutX(10);
        imvR.setLayoutY(100);
        
        Image imU=new Image(this.getClass().getResource("imageU.jpeg").toString());
        imvU=new ImageView(imU);
        imvU.setPreserveRatio(true);
        imvU.setFitHeight(200);
        imvU.setFitWidth(200); 
        imvU.setLayoutX(10);
        imvU.setLayoutY(100);
        imvU.setVisible(false);
        
        Image imG=new Image(this.getClass().getResource("imageG.jpeg").toString());
        imvG=new ImageView(imG);
        imvG.setPreserveRatio(true);
        imvG.setFitHeight(200);
        imvG.setFitWidth(200); 
        imvG.setLayoutX(10);
        imvG.setLayoutY(100);
        imvG.setVisible(false);
        
        root.getChildren().add(choice);
        root.getChildren().add(imvR);
        root.getChildren().add(imvU);
        root.getChildren().add(imvG);     
    }
}
