package Runner;

import dpbuilder.AudioEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 29.12.12
 * Time: 0:41
 * To change this template use File | Settings | File Templates.
 */
public class AppLauncher
{
   public static void main(String[] args)
   {
      AudioEntity audioEntity = new AudioEntity.Builder("I'll be the One", 220, 192, 11)
        .buildAlbum("Back street")
        .buildGenre("Pop")
        .buildGroup("Back street boys")
        .buildRate(5)
        .buildRecordFormat("mp3")
        .buildUrl("http://www.zaicev.net")
        .buildYear(199)
        .build();
   }
}
