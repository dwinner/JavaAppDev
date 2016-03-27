//: BufferedInputFile.java
import java.io.*;

public class BufferedInputFile
{
    // Исключения направляются на консоль:
    public static String read(String filename)
        throws IOException
    {
        // Чтение входных данных по строкам:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            sb.append(s + "\n");
        in.close();
        return sb.toString();
    }
  
    public static void main(String[] args)
        throws IOException
    {
        System.out.print(read("BufferedInputFile.java"));
    }
}
/* (Execute to see output) *///:~
