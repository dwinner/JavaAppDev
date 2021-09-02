package pkg1.conveniencemethods;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Простейшие способы обработки событий.
 *
 * @author oracle_pr1
 */
public class ConvenienceMethods extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Simple event handling");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Test");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Test Me");
            }
        });

        final Circle circle = new Circle(15, Color.RED);
        circle.setLayoutX(200);
        circle.setLayoutY(100);

        circle.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse entered the circle");
                circle.setFill(Color.BLANCHEDALMOND);
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse exited the circle");
                circle.setFill(Color.RED);
            }
        });
        circle.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse pressed in the circle");
                circle.setEffect(new BoxBlur(circle.getRadius() - 10,
                                             circle.getRadius() - 10, 3));
            }
        });
        circle.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("Mouse released in the circle");
                circle.setEffect(null);
            }
        });

        final TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        textBox.setLayoutX(10);
        textBox.setLayoutY(10);

        textBox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                System.out.println("Key Pressed: " + event.getText());
            }
        });
        textBox.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                System.out.println("Key Released: " + event.getText());
            }
        });

        root.getChildren().addAll(btn, circle, textBox);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
