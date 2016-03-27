//: TestEOF.java
// ѕроверка достижени€ конца файла одновременно
// с чтением из него по байту.
import java.io.*;

public class TestEOF
{
    public static void main(String[] args)
        throws IOException
    {
        DataInputStream in = new DataInputStream(
            new BufferedInputStream(
                new FileInputStream("TestEOF.java")
            )
        );
        while (in.available() != 0)
            System.out.print((char)in.readByte());
    }
}
/* (Execute to see output) *///:~
