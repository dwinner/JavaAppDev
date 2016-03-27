package audiofileinfo;

import static java.lang.System.out;

import java.io.IOException;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.filename.FilenameTag;

public class AudioFileInfo
{
    public static void main(String[] args)
        throws  IOException,
                TagException
    {
        MP3File mpegFile = new MP3File("sounds/sound3.mp3");
        
        out.println(mpegFile.getID3v1Tag());
        out.println(mpegFile.getID3v2Tag());
        out.println(mpegFile.getFilenameTag());    // 0?!
        out.println(mpegFile.getBitRate());    // 0?!
        out.println(mpegFile.getFrequency());
        out.println(mpegFile.getEmphasis());
        // out.println(mpegFile.toString());
    }
}
