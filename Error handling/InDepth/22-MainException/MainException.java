// Передача исключений на консоль.
import java.io.*;

public class MainException
{
    public static void main(String[] args) throws Exception
    {
        // Открываем файл
        FileInputStream file = new FileInputStream("MainException.java");
        // Используем файл
        // Закрываем файл
        file.close();
    }
}