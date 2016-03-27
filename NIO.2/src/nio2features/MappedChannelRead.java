package nio2features;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Отображение в память при чтении файла.
 *
 * @author Denis
 */
public class MappedChannelRead
{
   public static void main(String[] args)
   {
      try (FileChannel fileChannel =
        (FileChannel) Files.newByteChannel(Paths.get("test.txt")))
      {
         long fileSize = fileChannel.size();
         MappedByteBuffer mappedByteBuffer =
           fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
         for (int i = 0; i < fileSize; i++)
         {
            System.out.print((char) mappedByteBuffer.get());
         }
         System.out.println();
      }
      catch (InvalidPathException | IOException e)
      {
         e.printStackTrace();
      }
   }

}
