package javafxapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFXApplication extends Application
{   
    public static void main(String[] args)
    {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Тестирование GUI-компонентов");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final TextArea textArea = new TextArea();
        textArea.setLayoutX(10);
        textArea.setLayoutY(10); 
        textArea.setCursor(Cursor.TEXT);
        DropShadow effect=new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        textArea.setEffect(effect);
        textArea.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-background-color:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-font-weight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-font-style:italic");
        textArea.setPrefSize(200, 250);
        textArea.setTooltip(new Tooltip("Об издательстве"));
        textArea.setText("Издательство «БХВ-Петербург», одно из старейших на рынке компьютерной литературы, основано в 1993 году. В настоящее время специализируется на выпуске книг не только компьютерной, но и технической и естественно-научной тематики.");
        textArea.setEditable(true);
        textArea.setWrapText(true);          
    
        Button btn = new Button();        
        btn.setLayoutX(20);
        btn.setLayoutY(300);
        btn.setText("Отправить");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold italic 14pt Georgia;-fx-text-fill: white;-fx-background-color: #a0522d;-fx-border-width: 3px; -fx-border-color:#f4a460 #800000 #800000 #f4a460;" );       
        btn.setPrefSize(180,30);       
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                System.out.println( textArea.getText());
            }
        });
        root.getChildren().add(textArea);
        root.getChildren().add(btn);  
    }
}
