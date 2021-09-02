// IntBufferDemo.java : ������ � ������ ������� � ������ ByteBuffer
import java.nio.*;

public class IntBufferDemo
{
    private static final int BSIZE = 1024;
  
    public static void main(String[] args)
    {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        IntBuffer ib = bb.asIntBuffer();
        // ���������� ������� int:
        ib.put(new int[]{ 11, 42, 47, 99, 143, 811, 1016 });
        // ������ � ������ �� ���������� ��������:
        System.out.println(ib.get(3));
        ib.put(3, 1811);
        // ���������� ������ ������� ����� ��������� ������.
        ib.flip();
        while (ib.hasRemaining())
        {
            int i = ib.get();
            System.out.println(i);
        }
    }
}
/* Output:
99
11
42
47
1811
143
811
1016
*///:~
