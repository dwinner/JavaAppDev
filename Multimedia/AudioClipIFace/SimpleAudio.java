// AudioClip-класс.
import java.applet.*;
import java.net.*;

public class SimpleAudio
{
    public SimpleAudio()
    {
        try
        {
            AudioClip ac = Applet.newAudioClip(new URL("file:pattern.mid"));
            ac.loop();
        }
        catch (Exception e) {}
    }

    public static void main(String[] args)
    {
        new SimpleAudio();
    }

}