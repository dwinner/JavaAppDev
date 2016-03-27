package nio2features;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Запись через отображение в память.
 *
 * @author Denis
 */
public class MappedChannelWrite
{
   public static void main(String[] args)
   {
      try (FileChannel fileChannel =
        (FileChannel) Files.newByteChannel(Paths.get("test2.txt"),
                                           StandardOpenOption.WRITE,
                                           StandardOpenOption.READ,
                                           StandardOpenOption.CREATE))
      {
         MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 26);
         for (int i = 0; i < 26; i++)
         {
            mappedByteBuffer.put((byte) ('A' + i));
         }
      }
      catch (InvalidPathException | IOException e)
      {
         e.printStackTrace();
      }
   }

}
