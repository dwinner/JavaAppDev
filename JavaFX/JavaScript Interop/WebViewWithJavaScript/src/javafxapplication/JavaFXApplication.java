package javafxapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class JavaFXApplication extends Application {
    private Double zoom=1.0;
   
    public static void main(String[] args) {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Тестирование GUI-компонентов");  
        
        Group root = new Group();            
        Scene scene = new Scene(root, 700, 700, Color.BEIGE);        
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        VBox vbox=new VBox();
        vbox.setLayoutX(10);
        vbox.setLayoutY(10);
        vbox.setSpacing(20);
        HBox hbox=new HBox();
        hbox.setSpacing(20); 
        
        final WebView webView=new WebView();
        webView.setPrefSize(600, 600);
        webView.setCursor(Cursor.TEXT);        
        webView.getEngine().loadContent("<html><body bgcolor=#A0BEC4></body></html>");
        
        final TextField textField = new TextField();        
        textField.setCursor(Cursor.TEXT);       
        textField.setStyle("-fx-background-radius:10;-fx-border-radius:8;-fx-background-color:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-font-weight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-font-style:italic");
        textField.setPrefWidth(400);
        textField.setTooltip(new Tooltip("Введите адрес"));       
        textField.setEditable(true);  
        textField.setPromptText("http://google.com/");        
        textField.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
       if(!textField.getText().isEmpty())
              webView.getEngine().load(textField.getText());
    }
});
        Button btn = new Button();       
        btn.setText("->");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold 16pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btn.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        if(!textField.getText().isEmpty())
               webView.getEngine().load(textField.getText());
    }
});
        webView.getEngine().locationProperty().addListener(new ChangeListener<String>(){
    public void changed(ObservableValue<? extends String> ov,String old_value, String new_value) {
           textField.setText(new_value);          
        }
});
        
        Button btnP = new Button();       
        btnP.setText("+");
        btnP.setCursor(Cursor.CLOSED_HAND);
        btnP.setStyle("-fx-font: bold 16pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btnP.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        zoom=zoom*1.1;
        webView.getEngine().executeScript("document.body.style.zoom="+zoom);
    }});
     Button btnM = new Button();       
        btnM.setText("-");
        btnM.setCursor(Cursor.CLOSED_HAND);
        btnM.setStyle("-fx-font: bold 16pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btnM.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        zoom=zoom/1.2;
        webView.getEngine().executeScript("document.body.style.zoom="+zoom);
    }});
        
        final ProgressIndicator pi;   
        pi=new ProgressIndicator();
        pi.setLayoutX(250);
        pi.setLayoutY(300);
        pi.setCursor(Cursor.TEXT);         
        pi.setTooltip(new Tooltip("Загрузка Web-страницы"));
        pi.setPrefSize(70,70);
        pi.setProgress(-1.0);
        pi.setVisible(false);
        webView.getEngine().getLoadWorker().progressProperty().addListener(new ChangeListener<Number>() {
        public void changed(ObservableValue<? extends Number> ov,  Number old_val, Number new_val) {
            if (new_val.doubleValue()!=1.0) 
                pi.setVisible(true);            
            else pi.setVisible(false);
        }
    });

        hbox.getChildren().addAll(textField, btn, btnP,btnM);
        vbox.getChildren().addAll(hbox,webView);
        root.getChildren().addAll(vbox, pi);
    }
}
