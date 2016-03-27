package nio2features;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Запись в файл через NIO.2
 *
 * @author Denis
 */
public class ExplicitChannelWrite
{
   public static void main(String[] args)
   {
      try (FileChannel fileChannel =
        (FileChannel) Files.newByteChannel(Paths.get("test2.txt"),
                                           StandardOpenOption.WRITE,
                                           StandardOpenOption.CREATE))
      {
         ByteBuffer byteBuffer = ByteBuffer.allocate(26);
         for (int i = 0; i < 26; i++)
         {
            byteBuffer.put((byte) ('A' + i));
         }
         byteBuffer.rewind();
         fileChannel.write(byteBuffer);
      }
      catch (InvalidPathException | IOException e)
      {
         e.printStackTrace();
      }
   }

}
