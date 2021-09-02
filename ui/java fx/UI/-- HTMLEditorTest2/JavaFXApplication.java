/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Тестирование GUI-компонентов");  
        
        Group root = new Group();            
        Scene scene = new Scene(root, 600, 600, Color.BEIGE);        
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        VBox vbox=new VBox();
        vbox.setLayoutX(10);
        vbox.setLayoutY(10);
        vbox.setSpacing(20);
        
        final WebView webView=new WebView();
        webView.setPrefSize(300, 200);
        webView.setCursor(Cursor.TEXT);        
        webView.getEngine().loadContent("<html><body bgcolor=#A0BEC4></body></html>");
        
        final HTMLEditor editor=new HTMLEditor();
        editor.setPrefSize(500, 300);
        editor.setCursor(Cursor.TEXT); 
        editor.setHtmlText("<html><body bgcolor=#A0BEC4></body></html>");
        
        Button btn = new Button();       
        btn.setText("Submit");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold 16pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btn.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        webView.getEngine().loadContent(editor.getHtmlText());
        System.out.println(editor.getHtmlText());
    }
});
        vbox.getChildren().addAll(webView, editor, btn);
        root.getChildren().addAll(vbox);
    }
}
