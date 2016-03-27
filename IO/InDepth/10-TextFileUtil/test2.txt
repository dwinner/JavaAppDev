import java.io.*;
import java.util.*;

/**
 * TextFile.java
 * Статические функции для построчного считывания и записи
 * текстовых файлов, а также манипуляции файлом как списком ArrayList
 * @author Bruce Ekkel
 * @version 1.0.0
 */
public class TextFile extends ArrayList<String>
{
	/**
	 * Чтение файла как одной строки;
	 */
	public static String read(String fileName)
	{
		StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader in = 
                new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try
            {
                String s;
                while ((s = in.readLine()) != null)
                {
                    sb.append(s);
                    sb.append("\n");
                }
            }
            finally
            {
                in.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        return sb.toString();
    }
  
    /**
     * Запись файла за один вызов метода.
     */
    public static void write(String fileName, String text)
    {
        try
        {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try
            {
                out.print(text);
            }
            finally
            {
                out.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
  
    /**
     * Чтение файла с разбиением по регулярному выражению.
     */
    public TextFile(String fileName, String splitter)
    {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Вызов split() часто оставляет пустой объект
        // String в начальной позиции
        if (get(0).equals("")) remove(0);
    }
  
    /**
     * Обычное построчное чтение
     */
    public TextFile(String fileName)
    {
        this(fileName, "\n");
    }
  
    public void write(String fileName)
    {
        try
        {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try
            {
                for (String item : this)
                    out.println(item);
            }
            finally
            {
                out.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
  
    // Простая проверка
    public static void main(String[] args)
    {
        String file = read("TextFile.java");
        write("test.txt", file);
        TextFile text = new TextFile("test.txt");
        text.write("test2.txt");
        
        // Разбиение на уникальный отсортированный список слов
        TreeSet<String> words =
            new TreeSet<String>(new TextFile("TextFile.java", "\\W+"));
        // Вывод слов, начинающихся с прописной буквы
        System.out.println(words.headSet("a"));
    }
}
/* Output:
[0, ArrayList, Arrays, Break, BufferedReader, BufferedWriter, Clean, Display, File, FileReader, FileWriter, IOException, Normally, Output, PrintWriter, Read, Regular, RuntimeException, Simple, Static, String, StringBuilder, System, TextFile, Tools, TreeSet, W, Write]
*///:~
