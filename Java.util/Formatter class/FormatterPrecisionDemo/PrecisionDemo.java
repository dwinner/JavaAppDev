// Демонстрация применения модификатора точности.
import java.util.*;

class PrecisionDemo
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
        
        // Формат с 4 десятичными разрядами.
        fmt.format("%.4f", 123.1234567);
        System.out.println(fmt);
        
        // Формат с 2 десятичными разрядами в 16-символьном поле
        fmt = new Formatter();
        fmt.format("%16.2e", 123.1234567);
        System.out.println(fmt);
        
        // Отобразить максимум 15 символов строки.
        fmt = new Formatter();
        fmt.format("%.15s", "Formatting with Java is now easy.");
        System.out.println(fmt);
    }
}