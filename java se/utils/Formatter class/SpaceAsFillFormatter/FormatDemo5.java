// Демонстрация применения пробела в качестве спецификатора формата.
import java.util.*;

public class FormatDemo5
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();

        fmt.format("% d", -100);
        System.out.println(fmt);

        fmt = new Formatter();
        fmt.format("% d", 100);
        System.out.println(fmt);

        fmt = new Formatter();
        fmt.format("% d", -200);
        System.out.println(fmt);

        fmt = new Formatter();
        fmt.format("% d", 200);
        System.out.println(fmt);
    }
}