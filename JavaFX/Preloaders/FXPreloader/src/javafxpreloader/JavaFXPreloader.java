/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxpreloader;

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXPreloader extends Preloader {
    private Stage stage;
    private ProgressIndicator progress;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Group root = new Group();
        root.setTranslateX(200);
        root.setTranslateY(200);
        Scene scene = new Scene(root, 800, 600, Color.BEIGE);
        BorderPane pane = new BorderPane();
        pane.setPrefSize(200,200);
        progress=new ProgressIndicator();
        progress.setStyle("-fx-font:bold 20pt Arial;");
        pane.setCenter(progress);        
        root.getChildren().addAll(pane);
        stage.setScene(scene);        
        stage.show();
    }
    
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
       /*if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }  */
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        progress.setProgress(pn.getProgress());
    } 
    
    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info){
       Preloader.ProgressNotification ntf=(Preloader.ProgressNotification) info;
      if (ntf.getProgress()==1.0)  stage.hide();
      else progress.setProgress(-1);
    }
}
