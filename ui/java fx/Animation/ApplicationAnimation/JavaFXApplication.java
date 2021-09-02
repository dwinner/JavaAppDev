/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFXApplication extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);  
        
        final Light.Point lightPoint = new Light.Point(); 
        lightPoint.setColor(Color.WHITE);
        lightPoint.setX(0.0);
        lightPoint.setY(0.0);
        lightPoint.setZ(100.0);
        final Lighting effect = new Lighting();
        effect.setLight(lightPoint); 
        effect.setDiffuseConstant(1.5);
        effect.setSpecularConstant(1.5);
        effect.setSurfaceScale(8);        
        
        final Text tJ = new Text();
        tJ.setEffect(effect);
        tJ.setX(80);
        tJ.setY(250);
        tJ.setText("J");
        tJ.setFill(Color.RED);
        tJ.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final Text ta1 = new Text();
        ta1.setEffect(effect);
        ta1.setX(120);
        ta1.setY(250);
        ta1.setText("a");
        ta1.setFill(Color.RED);
        ta1.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final Text tv = new Text();
        tv.setEffect(effect);
        tv.setX(170);
        tv.setY(250);
        tv.setText("v");
        tv.setFill(Color.RED);
        tv.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final Text ta2 = new Text();
        ta2.setEffect(effect);
        ta2.setX(220);
        ta2.setY(250);
        ta2.setText("a");
        ta2.setFill(Color.RED);
        ta2.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final Text tF = new Text();
        tF.setEffect(effect);
        tF.setX(270);
        tF.setY(250);
        tF.setText("F");
        tF.setFill(Color.RED);
        tF.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final Text tX = new Text();
        tX.setEffect(effect);
        tX.setX(320);
        tX.setY(250);
        tX.setText("X");
        tX.setFill(Color.RED);
        tX.setFont(Font.font(null, FontWeight.BOLD, 80));
        
        final TranslateTransition ttJ = new TranslateTransition(Duration.millis(1000),tJ);        
        ttJ.setCycleCount(2);
        ttJ.setByX(-200.0);
        ttJ.setToY(-270.0);
        ttJ.setAutoReverse(true);       
        
        final RotateTransition rtJ = new RotateTransition(Duration.millis(500),tJ);
        rtJ.setByAngle(360);
        rtJ.setAxis(new Point3D(10.0, 10.0, 10.0));
        rtJ.setCycleCount(4);        
        
        final ScaleTransition stJ = new ScaleTransition(Duration.millis(1000),tJ);
        stJ.setByX(-1.5);
        stJ.setByY(-1.5);
        stJ.setCycleCount(2);
        stJ.setAutoReverse(true);          
        
        final TranslateTransition tta1 = new TranslateTransition(Duration.millis(1000),ta1);
        tta1.setCycleCount(2);
        tta1.setByX(-100.0);
        tta1.setToY(-270.0);
        tta1.setAutoReverse(true);  
        
        final RotateTransition rta1 = new RotateTransition(Duration.millis(500),ta1);
        rta1.setByAngle(360);
        rta1.setAxis(new Point3D(10.0, 10.0, 10.0));
        rta1.setCycleCount(4);        
        
        final ScaleTransition sta1 = new ScaleTransition(Duration.millis(1000),ta1);
        sta1.setByX(-1.5);
        sta1.setByY(-1.5);
        sta1.setCycleCount(2);
        sta1.setAutoReverse(true);  
        
        final TranslateTransition ttv = new TranslateTransition(Duration.millis(1000),tv);
        ttv.setCycleCount(2);
        ttv.setByX(0.0);
        ttv.setToY(-270.0);
        ttv.setAutoReverse(true);  
        
        final RotateTransition rtv = new RotateTransition(Duration.millis(500),tv);
        rtv.setByAngle(360);
        rtv.setAxis(new Point3D(10.0, 10.0, 10.0));
        rtv.setCycleCount(4);        
        
        final ScaleTransition stv = new ScaleTransition(Duration.millis(1000),tv);
        stv.setByX(-1.5);
        stv.setByY(-1.5);
        stv.setCycleCount(2);
        stv.setAutoReverse(true);  
        
        final TranslateTransition tta2 = new TranslateTransition(Duration.millis(1000),ta2);
        tta2.setCycleCount(2);
        tta2.setByX(100.0);
        tta2.setToY(-270.0);
        tta2.setAutoReverse(true);   
        
        final RotateTransition rta2 = new RotateTransition(Duration.millis(500),ta2);
        rta2.setByAngle(360);
        rta2.setAxis(new Point3D(10.0, 10.0, 10.0));
        rta2.setCycleCount(4);        
        
        final ScaleTransition sta2 = new ScaleTransition(Duration.millis(1000),ta2);
        sta2.setByX(-1.5);
        sta2.setByY(-1.5);
        sta2.setCycleCount(2);
        sta2.setAutoReverse(true);  
        
        final TranslateTransition ttF = new TranslateTransition(Duration.millis(1000),tF);
        ttF.setCycleCount(2);
        ttF.setByX(200.0);
        ttF.setToY(-270.0);
        ttF.setAutoReverse(true);  
        
        final RotateTransition rtF = new RotateTransition(Duration.millis(500),tF);
        rtF.setByAngle(360);
        rtF.setAxis(new Point3D(10.0, 10.0, 10.0));
        rtF.setCycleCount(4);        
        
        final ScaleTransition stF = new ScaleTransition(Duration.millis(1000),tF);
        stF.setByX(-1.5);
        stF.setByY(-1.5);
        stF.setCycleCount(2);
        stF.setAutoReverse(true);  
        
        final TranslateTransition ttX = new TranslateTransition(Duration.millis(1000),tX);
        ttX.setCycleCount(2);
        ttX.setByX(300.0);
        ttX.setToY(-270.0);
        ttX.setAutoReverse(true); 
        
        final RotateTransition rtX = new RotateTransition(Duration.millis(500),tX);
        rtX.setByAngle(360);
        rtX.setAxis(new Point3D(10.0, 10.0, 10.0));
        rtX.setCycleCount(4);        
        
        final ScaleTransition stX = new ScaleTransition(Duration.millis(1000),tX);
        stX.setByX(-1.5);
        stX.setByY(-1.5);
        stX.setCycleCount(2);
        stX.setAutoReverse(true);  
        
        final ParallelTransition ptJ = new ParallelTransition(tJ,ttJ,rtJ,stJ);
        final ParallelTransition pta1 = new ParallelTransition(ta1,tta1,rta1,sta1);
        final ParallelTransition ptv = new ParallelTransition(tv,ttv,rtv,stv);
        final ParallelTransition pta2 = new ParallelTransition(ta2,tta2,rta2,sta2);
        final ParallelTransition ptF = new ParallelTransition(tF,ttF,rtF,stF);
        final ParallelTransition ptX = new ParallelTransition(tX,ttX,rtX,stX);
        
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        KeyValue kv = new KeyValue(lightPoint.xProperty(),500.0, Interpolator.EASE_BOTH);         
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);       
        timeline.getKeyFrames().addAll(kf);
        timeline.setDelay(Duration.millis(2000));
        
        Button btn = new Button();
        btn.setLayoutX(10);
        btn.setLayoutY(450);
        btn.setText("Анимация");       
        btn.setStyle("-fx-font: bold italic 14pt Georgia;-fx-text-fill: white;-fx-background-color: black;-fx-border-width: 1px; -fx-border-color:white");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {  
                if (!timeline.getStatus().equals(Animation.Status.RUNNING)){
        ptJ.play();
        pta1.play();
        ptv.play();
        pta2.play();
        ptF.play();
        ptX.play();        
        timeline.play();  }        
            }
        });
        root.getChildren().addAll(btn, tJ,ta1,tv,ta2,tF,tX);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
