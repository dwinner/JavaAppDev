package pkg06.swtmenus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList<String>
{
    private static final long serialVersionUID = 1904992752806723339L;

    /**
     * Прочитать файл, разделенный любым регулярным выражением
     * <p/>
     * @param fileName Имя файла
     * @param splitter Регулярное выражение
     */
    public TextFile(String fileName, String splitter)
    {
        super(Arrays.asList(read(fileName).split(splitter)));
        if (get(0).isEmpty())
        {
            // Регулярное выражение по split() часто оставляет
            // пустую строку в первой позиции, удалим ее
            remove(0);
        }
    }

    /**
     * Чтение файла по переносу строки
     * <p/>
     * @param fileName Файл для чтения
     */
    public TextFile(String fileName)
    {
        this(fileName, "\n");
    }

    /**
     * Чтение файла в одну строку
     * <p/>
     * @param fileName Имя файла
     * <p/>
     * @return Прочитанную строку
     */
    public static String read(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            try (BufferedReader in = new BufferedReader(
                  new FileReader(new File(fileName).getAbsoluteFile())))
            {
                String s;
                while ((s = in.readLine()) != null)
                {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        }
        catch (SecurityException secEx)
        {
            secEx.initCause(new IOException());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * Запись файла за один вызов метода
     * <p/>
     * @param fileName Имя файла для записи
     * @param text     Строка для записи
     */
    public static void write(String fileName, String text)
    {
        try
        {
            try (PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile()))
            {
                out.print(text);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Запись файла из инициализированного ранее списка
     * <p/>
     * @param fileName Имя файла для записи
     */
    public void write(String fileName)
    {
        try
        {
            try (PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile()))
            {
                for (String item : this)
                {
                    out.println(item);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
