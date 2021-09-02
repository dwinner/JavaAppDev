//: ViewBuffers.java
import java.nio.*;

public class ViewBuffers
{
    public static void main(String[] args)
    {
        ByteBuffer bb = ByteBuffer.wrap(new byte[]{ 0, 0, 0, 0, 0, 0, 0, 'a' });
        bb.rewind();
        System.out.println("Byte Buffer ");
        while(bb.hasRemaining())
            System.out.print(bb.position() + " -> " + bb.get() + ", ");
        System.out.println();
        CharBuffer cb = ((ByteBuffer)bb.rewind()).asCharBuffer();
        System.out.println("Char Buffer ");
        while (cb.hasRemaining())
            System.out.print(cb.position() + " -> " + cb.get() + ", ");
        System.out.println();
        FloatBuffer fb = ((ByteBuffer)bb.rewind()).asFloatBuffer();
        System.out.println("Float Buffer ");
        while (fb.hasRemaining())
            System.out.print(fb.position() + " -> " + fb.get() + ", ");
        System.out.println();
        IntBuffer ib = ((ByteBuffer)bb.rewind()).asIntBuffer();
        System.out.println("Int Buffer ");
        while (ib.hasRemaining())
            System.out.print(ib.position() + " -> " + ib.get() + ", ");
        System.out.println();
        LongBuffer lb = ((ByteBuffer)bb.rewind()).asLongBuffer();
        System.out.println("Long Buffer ");
        while (lb.hasRemaining())
            System.out.print(lb.position() + " -> " + lb.get() + ", ");
        System.out.println();
        ShortBuffer sb = ((ByteBuffer)bb.rewind()).asShortBuffer();
        System.out.println("Short Buffer ");
        while (sb.hasRemaining())
            System.out.print(sb.position() + " -> " + sb.get() + ", ");
        System.out.println();
        DoubleBuffer db = ((ByteBuffer)bb.rewind()).asDoubleBuffer();
        System.out.println("Double Buffer ");
        while (db.hasRemaining())
            System.out.print(db.position()+ " -> " + db.get() + ", ");
    }
}
/* Output:
Byte Buffer 0 -> 0, 1 -> 0, 2 -> 0, 3 -> 0, 4 -> 0, 5 -> 0, 6 -> 0, 7 -> 97,
Char Buffer 0 ->  , 1 ->  , 2 ->  , 3 -> a,
Float Buffer 0 -> 0.0, 1 -> 1.36E-43,
Int Buffer 0 -> 0, 1 -> 97,
Long Buffer 0 -> 97,
Short Buffer 0 -> 0, 1 -> 0, 2 -> 0, 3 -> 97,
Double Buffer 0 -> 4.8E-322,
*///:~
