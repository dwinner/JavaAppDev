
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * @version 1.4 2007-04-07
 * @author Cay Horstmann
 */
public class CalendarTest
{
    public static void main(String[] args)
    {
        // Создание объекта d, представляющего текущую дату.
        GregorianCalendar d = new GregorianCalendar();

        int today = d.get(Calendar.DAY_OF_MONTH);
        int month = d.get(Calendar.MONTH);

        // Установка объекта d на первое число месяца
        d.set(Calendar.DAY_OF_MONTH, 1);

        int weekday = d.get(Calendar.DAY_OF_WEEK);

        // Получить первый день недели (Воскресенье в США)
        int firstDayOfWeek = d.getFirstDayOfWeek();

        // Определить необходимый отступ для первой строки
        int indent = 0;
        while (weekday != firstDayOfWeek)
        {
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }

        // Напечатать названия дней недели.
        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
        do
        {
            System.out.printf("%4s", weekdayNames[weekday]);
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }
        while (weekday != firstDayOfWeek);
        System.out.println();

        for (int i = 1; i <= indent; i++)
        {
            System.out.print("    ");
        }

        d.set(Calendar.DAY_OF_MONTH, 1);
        do
        {
            // Вывод дня месяца
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);

            // Символом * помечаем текущий день
            if (day == today)
            {
                System.out.print("*");
            }
            else
            {
                System.out.print(" ");
            }

            // Перевод d на следующий день
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);

            // Перевод строки в начале недели.
            if (weekday == firstDayOfWeek)
            {
                System.out.println();
            }
        }
        while (d.get(Calendar.MONTH) == month);
        // Цикл завершается, когда объект d установлен на первый день следующего месяца.

        // При необходимости переводится строка
        if (weekday != firstDayOfWeek)
        {
            System.out.println();
        }
    }
}