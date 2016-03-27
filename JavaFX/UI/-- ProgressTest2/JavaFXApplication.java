/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.scene.*;
import javafx.stage.*;


public class JavaFXApplication extends Application {
    
   
  
   
   
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();       
        
        final ProgressBar pb=new ProgressBar();
        pb.setLayoutX(20);
        pb.setLayoutY(50);
        pb.setCursor(Cursor.TEXT);   
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        pb.setEffect(effect);        
        pb.setTooltip(new Tooltip("Индикатор выполнения задачи"));
        pb.setPrefSize(200,30);
        
        final ProgressIndicator pi=new ProgressIndicator();
        pi.setLayoutX(250);
        pi.setLayoutY(50);
        pi.setCursor(Cursor.TEXT);     
        pi.setStyle("-fx-font:bold 14pt Arial;");
        pi.setTooltip(new Tooltip("Индикатор выполнения задачи"));
        pi.setPrefSize(70,70); 
        
        final DoubleProperty progress=new SimpleDoubleProperty(0.0); 
        pb.progressProperty().bind(progress);
        pi.progressProperty().bind(progress);
        
        Button btnS=new Button("Start");
        btnS.setLayoutX(20);
        btnS.setLayoutY(100);
        btnS.setStyle("-fx-font: 16pt Arial;");
        btnS.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        if(progress.getValue()<=1.0)
        progress.setValue(progress.getValue()+0.2);        
    }
});
        Button btnR=new Button("Reset");
        btnR.setLayoutX(100);
        btnR.setLayoutY(100);
        btnR.setStyle("-fx-font: 16pt Arial;");
        btnR.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        progress.setValue(0.0);         
    }
});
        
     /*   progress.addListener(new ChangeListener<Number>() {
        public void changed(ObservableValue<? extends Number> ov,  Number old_val, Number new_val) {
             pi.setProgress(new_val.doubleValue());  
             pb.setProgress(new_val.doubleValue());
        }
    });*/       
       
       root.getChildren().add(pi); 
       root.getChildren().add(pb); 
       root.getChildren().add(btnS); 
       root.getChildren().add(btnR); 
    }
}
