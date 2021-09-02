package javafxapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;


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
        
        final Group root = new Group();            
        Scene scene = new Scene(root, 500, 500, Color.BEIGE);        
        primaryStage.setScene(scene);
        primaryStage.show();
       
        Media video = new Media(getClass().getResource("video.flv").toString());
        final MediaPlayer playerVideo = new MediaPlayer(video);
        playerVideo.setAutoPlay(true);
        
        final MediaView media = new MediaView(playerVideo);
        media.setFitHeight(300);
        media.setFitWidth(300);
        media.setPreserveRatio(true); 
        
        Media audio = new Media(this.getClass().getResource("audio.mp3").toString());
        final MediaPlayer playerAudio = new MediaPlayer(audio);
        playerAudio.setCycleCount(MediaPlayer.INDEFINITE);
        playerAudio.setAutoPlay(true);
        
        Button btnPlay = new Button();
        btnPlay.setTooltip(new Tooltip("Play"));
        Image imPlay = new Image(getClass().getResource("player_play.png").toString());
        ImageView imvPlay = new ImageView(imPlay);
        imvPlay.setFitHeight(20);
        imvPlay.setFitWidth(20);
        btnPlay.setGraphic(imvPlay);
        btnPlay.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                playerVideo.play();
                playerAudio.play();
            }
        });
        
        Button btnPause = new Button();
        btnPause.setTooltip(new Tooltip("Pause"));
        Image imPause = new Image(getClass().getResource("player_pause.png").toString());
        ImageView imvPause = new ImageView(imPause);
        imvPause.setFitHeight(20);
        imvPause.setFitWidth(20);
        btnPause.setGraphic(imvPause);
        btnPause.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                playerVideo.pause();
                playerAudio.pause();        
            }
        });
        
        Button btnStop = new Button();
        btnStop.setTooltip(new Tooltip("Stop"));
        Image imStop=new Image(this.getClass().getResource("player_stop.png").toString());
        ImageView imvStop = new ImageView(imStop);
        imvStop.setFitHeight(20);
        imvStop.setFitWidth(20);
        btnStop.setGraphic(imvStop);
        btnStop.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                playerVideo.stop();
                playerAudio.stop();
            }
        });
        
        final Slider sliderT = new Slider();
        sliderT.setCursor(Cursor.CROSSHAIR);
        sliderT.setMax(31200);
        sliderT.setMin(0);
        sliderT.setOrientation(Orientation.HORIZONTAL);
        sliderT.setShowTickLabels(false);
        sliderT.setShowTickMarks(false);
        sliderT.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                playerVideo.pause(); 
                playerAudio.pause();
            }
        });
        sliderT.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                playerVideo.seek(new Duration(sliderT.getValue()));
                playerAudio.seek(new Duration(sliderT.getValue()));
            }
        });
        
        playerVideo.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {
            public void changed(ObservableValue<? extends Duration> ov,
                                Duration old_value,
                                Duration new_value)
            {
                if(new_value.toMillis()>31200)
                {
                    playerVideo.stop();
                    playerAudio.stop();
                    playerVideo.play(); 
                    playerAudio.play();
                }       
                sliderT.setValue(new_value.toMillis());
            }
        });
        
        final Slider sliderV = new Slider();
        sliderV.setCursor(Cursor.CROSSHAIR);
        sliderV.setTooltip(new Tooltip("Volume"));
        sliderV.setMaxWidth(70);
        sliderV.setMax(1.0);
        sliderV.setMin(0.0); 
        sliderV.setValue(0.5);
        sliderV.setOrientation(Orientation.HORIZONTAL);
        sliderV.setShowTickLabels(false);
        sliderV.setShowTickMarks(false); 
        sliderV.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val,
                                Number new_val)
            {
                playerAudio.setVolume(sliderV.getValue());
            }
        });
        
        Label volume = new Label();
        Image imVolume = new Image(this.getClass().getResource("volume.jpeg").toString());
        ImageView imvVolume = new ImageView(imVolume);
        imvVolume.setFitHeight(20);
        imvVolume.setFitWidth(20);
        volume.setGraphic(imvVolume);        
        
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnPlay, btnPause, btnStop, sliderV, volume);
        VBox vbox=new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(sliderT, new Insets(0,30,0,30));
        HBox.setMargin(sliderV, new Insets(0,0,0,30));
        vbox.getChildren().addAll(media, sliderT, hbox);
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setCursor(Cursor.HAND);
        pane.setLayoutX(30);
        pane.setLayoutY(30);
        Rectangle rec = new Rectangle(350,350);
        rec.setFill(Color.BLACK);
        rec.setStroke(Color.NAVY);
        rec.setStrokeWidth(5);
        rec.setStrokeType(StrokeType.OUTSIDE);
        rec.setArcHeight(10);
        rec.setArcWidth(20);
        pane.getChildren().addAll(rec,vbox);
        
        root.getChildren().addAll(pane);
    }
}
