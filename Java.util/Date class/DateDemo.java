// Показывает дату и время, используя только Date-методы.
import java.util.Date;

public class DateDemo
{
    public static void main(String args[])
    {
        // Создать объект типа Date.
        Date date = new Date();

        // Показать дату и время с помощью toString().
        System.out.println(date);

        // Показать число миллисекунд с полуночи 1 января 1970 г. по GMT
        long msec = date.getTime();
        System.out.println("Milliseconds since Jan. 1, 1970 GMT = " + msec);
    }
}
