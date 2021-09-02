// BufferToText.java :  Получение текста из буфера ByteBuffers и обратно
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;

public class BufferToText
{
    private static final int BSIZE = 1024;

    public static void main(String[] args)
        throws Exception
    {
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        // Не работает:
        System.out.println(buff.asCharBuffer());
        // Декодирование с использованием кодировки по умолчанию:
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ": "
            + Charset.forName(encoding).decode(buff));
        // Кодирование в печатной форме:
        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        // Повторная попытка печати:
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        // Использование CharBuffer для записи:
        fc = new FileOutputStream("data2.txt").getChannel();
        buff = ByteBuffer.allocate(24); // Больше чем необходимо
        buff.asCharBuffer().put("Some text");
        fc.write(buff);
        fc.close();
        // Чтение и вывод:
        fc = new FileInputStream("data2.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
    }
}
/* Output:
????
Decoded using Cp1252: Some text
Some text
Some text
*///:~
