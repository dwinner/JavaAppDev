package nio2features;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Использование канала ввода/вывода для чтения файла.
 *
 * @author Denis
 */
public class ExplicitChannelRead
{
   public static void main(String[] args)
   {
      int count = 0;
      Path filePath = Paths.get("test.txt");
      try (SeekableByteChannel channel = Files.newByteChannel(filePath))
      {
         ByteBuffer byteBuffer = ByteBuffer.allocate((int) filePath.toFile().length());
         do
         {
            count = channel.read(byteBuffer);
            if (count != -1)
            {
               byteBuffer.rewind(); // Подготовить буфер для чтения
               for (int i = 0; i < count; i++)
               {
                  System.out.print((char) byteBuffer.get());
               }
            }
         }
         while (count != -1); // Остановиться по достижении конца файла
      }
      catch (IOException ex)
      {
         Logger.getLogger(ExplicitChannelRead.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
