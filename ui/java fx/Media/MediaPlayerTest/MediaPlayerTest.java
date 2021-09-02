package mediaplayertest;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Внедрение media-содержимого.
 *
 * @author oracle_pr1
 */
public class MediaPlayerTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    /*
     Если хотите "пройти" через firewall, для медиа-потоков, приходящих через сеть
     установите опции JVM c подобным содержимым :
        -Dhttp.proxyHost=yourproxyhost.com 
        -Dhttp.proxyPort=somePort#,
     где
        yourproxyhost.com - адрес прокси-сервера;
        somePort - порт прокси-сервера
    */
    private static final URL MEDIA_URL =
       MediaPlayerTest.class.getResource("oow2010-2.flv");
    private static String arg1;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 540, 241);

        // Создаем медиа-плейер        
        Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
