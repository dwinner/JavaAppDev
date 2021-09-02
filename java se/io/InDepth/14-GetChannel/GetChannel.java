// GetChannel.java : Получение каналов из потоков
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class GetChannel
{
    private static final int BSIZE = 1024;
  
    public static void main(String[] args)
        throws Exception
    {
        // Запись файла:
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        // Добавление в конец файла:
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size()); // Переходим в конец
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        // Чтение файла:
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        // Или буфер прямого доступа ByteBuffer buff = ByteBuffer.allocateDirect(BSIZE);
        fc.read(buff);
        buff.flip();    // Позволить извлечь из буфера данные
        while (buff.hasRemaining())
            System.out.print((char)buff.get());
  }
} /* Output:
Some text Some more
*///:~
