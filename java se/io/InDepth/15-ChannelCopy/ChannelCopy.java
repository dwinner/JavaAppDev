// ChannelCopy.javaCopying : Копирование файла с использованием каналов и буферов
// {Параметры: ChannelCopy.java test.txt}
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class ChannelCopy
{
    private static final int BSIZE = 1024;
  
    public static void main(String[] args)
        throws Exception
    {
        if (args.length != 2)
        {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel
            in = new FileInputStream(args[0]).getChannel(),
            out = new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1)
        {
            buffer.flip(); // Подготовка к записи
            out.write(buffer);
            buffer.clear();  // Подготовка к чтению
        }
    }
}