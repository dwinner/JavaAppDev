package javafxapplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
        Scene scene = new Scene(root, 300, 300, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();       
        
        final ProgressBar pb = new ProgressBar();
        pb.setLayoutX(20);
        pb.setLayoutY(50);
        pb.setCursor(Cursor.TEXT);   
        DropShadow effect = new DropShadow();
        effect.setOffsetX(8);
        effect.setOffsetY(8);
        pb.setEffect(effect);        
        pb.setTooltip(new Tooltip("Индикатор выполнения задачи"));
        pb.setPrefSize(200, 30); 
        pb.setProgress(0.0);       
     
        Button btnS = new Button("Start");
        btnS.setLayoutX(20);
        btnS.setLayoutY(100);
        btnS.setStyle("-fx-font: 16pt Arial;");
        btnS.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                final Task task = new Task<Void>()
                {
                    @Override
                    protected Void call() throws Exception
                    {
                        int max = 10;
                        for (int i = 1; i <= max; i++)
                        {
                            updateProgress(i, max);
                            Thread.sleep(200);
                        }
                        return null;
                    }            
                    @Override
                    protected void updateProgress(long workDone, long max)
                    {
                        pb.setProgress((double)workDone/(double)max);
                    }
                };
                //new Thread(task).start(); 
                ExecutorService es = Executors.newSingleThreadExecutor();
                // es.submit (task);
                //  es.shutdown ();
                Service service = new Service<Void>()
                {
                    @Override
                    protected Task createTask()
                    {
                        return task; 
                    }
                };
                service.start();
            }
        });
        Button btnR = new Button("Reset");
        btnR.setLayoutX(100);
        btnR.setLayoutY(100);
        btnR.setStyle("-fx-font: 16pt Arial;");
        btnR.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                pb.setProgress(0.0);         
            }
        });
        
        root.getChildren().add(pb); 
        root.getChildren().add(btnS); 
        root.getChildren().add(btnR); 
    }
}
