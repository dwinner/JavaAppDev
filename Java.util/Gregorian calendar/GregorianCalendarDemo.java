// ƒемонстрирует класс GregorianCalendar
import java.util.*;

public class GregorianCalendarDemo
{
    public static void main(String args[])
    {
        String months[] = 
        {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
        int year;
        
        // —оздать календарь, инициализированный текущей датой и временем,
        // в умалчиваемой €зыковой зоне и часовом по€се.
        GregorianCalendar gcalendar = new GregorianCalendar();

        // ќтобразить информацию текущего времени и даты.
        System.out.print("Date: ");
        System.out.print(months[gcalendar.get(Calendar.MONTH)]);
        System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
        System.out.println(year = gcalendar.get(Calendar.YEAR));

        System.out.print("Time: ");
        System.out.print(gcalendar.get(Calendar.HOUR) + ":");
        System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gcalendar.get(Calendar.SECOND));

        // ѕроверить текущий год, €вл€етс€ ли он високосным.
        System.out.println(gcalendar.isLeapYear(year)
            ? "Current year is leap"
            : "Current year is not leap"
        );
    }
}
