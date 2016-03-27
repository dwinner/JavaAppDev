// Использование относительных индексов для упрощения создания
// пользовательских форматов даты и времени.
import java.util.*;

public class FormatDemo6
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        fmt.format("Today is day %te of %<tB, %<tY", cal);
        System.out.println(fmt);
    }
}