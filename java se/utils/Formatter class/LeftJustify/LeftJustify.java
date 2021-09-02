// Демонстрация выравнивания влево.
import java.util.*;

public class LeftJustify
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
    
        // По умолчанию выравнивается вправо
        fmt.format("|%10.2f|", 123.123);
        System.out.println(fmt);
        
        // А теперь влево
        fmt = new Formatter();
        fmt.format("|%-10.2f|", 123.123);
        System.out.println(fmt);
    }
}