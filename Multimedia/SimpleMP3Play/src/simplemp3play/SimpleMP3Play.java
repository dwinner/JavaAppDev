package simplemp3play;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * Простое проигрывание mp3-файла
 * @author oracle_pr1
 */
public class SimpleMP3Play
{
    public static void main(String[] args)
        throws  FileNotFoundException,
                JavaLayerException
    {
        InputStream mpegStream = new FileInputStream(new File("sound/sound1.mp3"));
        AdvancedPlayer mpegDefaultPlayer = new AdvancedPlayer(mpegStream);
        mpegDefaultPlayer.play();
        mpegDefaultPlayer.close();
    }
}
