// GetChannel.java : ��������� ������� �� �������
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class GetChannel
{
    private static final int BSIZE = 1024;
  
    public static void main(String[] args)
        throws Exception
    {
        // ������ �����:
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        // ���������� � ����� �����:
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size()); // ��������� � �����
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        // ������ �����:
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        // ��� ����� ������� ������� ByteBuffer buff = ByteBuffer.allocateDirect(BSIZE);
        fc.read(buff);
        buff.flip();    // ��������� ������� �� ������ ������
        while (buff.hasRemaining())
            System.out.print((char)buff.get());
  }
} /* Output:
Some text Some more
*///:~
