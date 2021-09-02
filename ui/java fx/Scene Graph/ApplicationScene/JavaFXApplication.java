/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
    
    
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Тестирование GUI-компонентов");  
        
        Group root = new Group(); 
        final Color color=Color.LIGHTGREEN;
        final Scene scene = new Scene(root, 300, 300, color);
        scene.setCursor(Cursor.CROSSHAIR);         
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
        double angle=10;   
        double shift=0;
        public void handle(MouseEvent event) {  
        PerspectiveCamera camera=new PerspectiveCamera();          
        angle=angle+10;   
        shift=shift+10;
        camera.setFieldOfView(angle);    
        scene.setCamera(camera); 
        scene.setFill(color.deriveColor(shift, 1.0, 1.0, 1.0));        
        }});        
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        StackPane logo=new StackPane();
        logo.setLayoutX(50);
        logo.setLayoutY(100);        
        DropShadow effect=new DropShadow();
        effect.setOffsetX(5);
        effect.setOffsetY(5);
        Text textF=new Text("JavaFX 2.0");        
        textF.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        textF.setFill(Color.web("#0000ff"));
        textF.setEffect(effect);
        Text textB=new Text("JavaFX 2.0");           
        textB.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        textB.setFill(Color.web("#000099"));
        textB.setEffect(effect);
        textB.setTranslateZ(100);
        
        logo.getChildren().addAll(textB,textF);
        
        root.getChildren().addAll(logo);
        
    }
}
