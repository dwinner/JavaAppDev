package audioplayer.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author oracle_pr1
 */
public class MediaCreator
{
   private static MediaPlayer currentMediaPlayer;
   
   public static MediaPlayer getCurrentMediaPlayer()
   {
      return currentMediaPlayer;
   }
   
   public static MediaPlayer newInstance(String mediaSource, boolean autoPlay)
   {
      currentMediaPlayer = new MediaPlayer(new Media(mediaSource));
      currentMediaPlayer.setAutoPlay(autoPlay);
      return currentMediaPlayer;      
   }
   
   public static MediaPlayer newInstance(String mediaSource)
   {
      return newInstance(mediaSource, false);
   }

   private MediaCreator(String mediaSource)
   {
      assert false;
   }
}
