package javafxapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class JavaFXApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(JavaFXApplication.class, args);
    }
    
    @Override
    public void start(Stage stage)
    {
        final Stage primaryStage = stage;
        primaryStage.setTitle("Тестирование GUI-компонентов");
        final Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new java.io.File("C:/"));
        fileChooser.setTitle("Выбрать изображение для загрузки");       
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                "jpg, png, bmp, gif", "*.jpg", "*.png", "*.bmp", "*.gif"
            )
        );
        
        Button btn = new Button();        
        btn.setLayoutX(20);
        btn.setLayoutY(50);
        btn.setText("Загрузить изображение");
        btn.setStyle("-fx-font: bold 16pt Georgia;" );       
        btn.setPrefSize(250,30);
        btn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                java.io.File file = fileChooser.showOpenDialog(primaryStage);
                try
                {
                    Image im = new Image(file.toURI().toString());
                    ImageView imv = new ImageView(im);
                    imv.setFitHeight(200);
                    imv.setFitWidth(200);
                    imv.setPreserveRatio(true);
                    imv.setLayoutX(20);
                    imv.setLayoutY(100);
                    root.getChildren().add(imv);       
                }
                catch (Exception ex)
                {
                }
            }
        });
       
        root.getChildren().add(btn); 
    }
}
