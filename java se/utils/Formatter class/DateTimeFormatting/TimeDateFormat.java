// ‘орматирование времени и даты.
import java.util.*;

public class TimeDateFormat
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();

        // ќтобразить стандартный 12-часовой формат.
        fmt.format("%tr", cal);
        System.out.println(fmt);
 
        // ќтобразить полную информацию о дате и времени.
        fmt = new Formatter();
        fmt.format("%tc", cal);
        System.out.println(fmt);

        // ќтобразить только часы и минуты.
        fmt = new Formatter();
        fmt.format("%tl:%tM", cal, cal);
        System.out.println(fmt);

        // ќтобразить название и номер мес€ца.
        fmt = new Formatter();
        fmt.format("%tB %tb %tm", cal, cal, cal);
        System.out.println(fmt);
    }
}