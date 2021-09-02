// TransferTo.java : Использование метода transferTo() для соединения каналов
// {Параметры: TransferTo.java TransferTo.txt}
import java.nio.channels.*;
import java.io.*;

public class TransferTo
{
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
        in.transferTo(0, in.size(), out);
        // Или: out.transferFrom(in, 0, in.size());
    }
}