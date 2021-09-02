/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package main;

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
import javafx.util.Duration;

public class JAppletWithJavaFX extends javax.swing.JApplet
{
    private void initFX(javafx.embed.swing.JFXPanel fxPanel)
    {
        // This method is invoked on JavaFX thread           
        final Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.BLACK);
        fxPanel.setScene(scene);
        Media video = new Media(this.getClass().getResource("video.flv").toString());
        final MediaPlayer playerVideo = new MediaPlayer(video);
        final MediaView media = new MediaView(playerVideo);
        media.setFitHeight(300);
        media.setFitWidth(300);
        media.setPreserveRatio(true);
        Media audio = new Media(this.getClass().getResource("audio.mp3").toString());
        final MediaPlayer playerAudio = new MediaPlayer(audio);
        playerAudio.setCycleCount(MediaPlayer.INDEFINITE);
        Button btnPlay = new Button();
        btnPlay.setTooltip(new Tooltip("Play"));
        Image imPlay = new Image(this.getClass().getResource("player_play.png").toString());
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
        Image imPause = new Image(this.getClass().getResource("player_pause.png").
           toString());
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
        Image imStop = new Image(this.getClass().getResource("player_stop.png").toString());
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
            @Override
            public void handle(MouseEvent event)
            {
                playerVideo.pause();
                playerAudio.pause();
            }
        });
        sliderT.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                playerVideo.seek(new Duration(sliderT.getValue()));
            }
        });
        playerVideo.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {
            @Override
            public void changed(ObservableValue<? extends Duration> ov, Duration old_value,
                                Duration new_value)
            {
                if (new_value.toMillis() > 31200)
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
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old_val,
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
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(sliderT, new Insets(0, 30, 0, 30));
        HBox.setMargin(sliderV, new Insets(0, 0, 0, 30));
        vbox.getChildren().addAll(media, sliderT, hbox);
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.setCursor(Cursor.HAND);
        pane.setLayoutX(20);
        pane.setLayoutY(20);
        Rectangle rec = new Rectangle(350, 350);
        rec.setFill(Color.BLACK);
        rec.setStroke(Color.NAVY);
        rec.setStrokeWidth(5);
        rec.setStrokeType(StrokeType.OUTSIDE);
        rec.setArcHeight(10);
        rec.setArcWidth(20);
        pane.getChildren().addAll(rec, vbox);
        root.getChildren().addAll(pane);
    }

    /**
     * Initializes the applet JAppletWithJavaFX
     */
    @Override
    public void init()
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.
               getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JAppletWithJavaFX.class.getName()).
               log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JAppletWithJavaFX.class.getName()).
               log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JAppletWithJavaFX.class.getName()).
               log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JAppletWithJavaFX.class.getName()).
               log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the applet
         */
        final javafx.embed.swing.JFXPanel fxPanel = new javafx.embed.swing.JFXPanel();
        add(fxPanel, null);
        fxPanel.setBounds(50, 30, 400, 400);
        javafx.application.Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                initFX(fxPanel);
            }
        });

        try
        {
            java.awt.EventQueue.invokeAndWait(new Runnable()
            {
                public void run()
                {
                    initComponents();
                }
            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the init() method to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always regenerated
     * by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
