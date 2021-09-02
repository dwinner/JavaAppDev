package nio2features;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Более компактный способ открытия канала.
 *
 * @author Denis
 */
public class CompactExplicitChannelRead
{
   public static void main(String[] args)
   {
      int count;
      try (SeekableByteChannel channel = Files.newByteChannel(Paths.get("test.txt")))
      {
         ByteBuffer byteBuffer = ByteBuffer.allocate(128);
         do
         {
            count = channel.read(byteBuffer);
            if (count != -1)
            {
               byteBuffer.rewind();
               for (int i = 0; i < count; i++)
               {
                  System.out.print((char) byteBuffer.get());
               }
            }
         }
         while (count != -1);
      }
      catch (InvalidPathException | IOException e)
      {
         e.printStackTrace();
      }
   }

}
