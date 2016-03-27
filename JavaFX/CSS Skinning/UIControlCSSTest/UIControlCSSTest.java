package uicontrolcsstest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UIControlCSSTest extends Application implements StyleStage.SceneCreator
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        StyleStage styleStage = new StyleStage(stage);
        styleStage.add("Style1", "DownloadButtonStyle1.css");
        styleStage.add("Style2", "DownloadButtonStyle2.css");
        stage.show();
        styleStage.setSceneCreator(this);
    }

    @Override
    public Scene createScene()
    {
        Button download = new Button("Download");
        download.getStyleClass().add("button1");
        
        Button go = new Button("Go");
        
        Button submit = new Button("Submit");
        submit.getStyleClass().add("button2");
        
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(download, go, submit);
        
        return new Scene(hBox, 400, 100);
    }
}
