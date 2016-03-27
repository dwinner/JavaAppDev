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

public class MainFrame extends javax.swing.JFrame
{
    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
    }

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
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        final MainFrame mainFrame = new MainFrame();
        final javafx.embed.swing.JFXPanel fxPanel = new javafx.embed.swing.JFXPanel();
        mainFrame.add(fxPanel, null);
        fxPanel.setBounds(50, 30, 400, 400);
        javafx.application.Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                mainFrame.initFX(fxPanel);
            }
        });
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                mainFrame.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
