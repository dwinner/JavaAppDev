import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Ищет в файлах ключевое слово.
 */
public class SearchTask implements Runnable
{
    private BlockingQueue<File> queue;
    private String keyword;

    /**
     * Конструктор SearchTask.
     * <p/>
     * @param aQueue   Очередь, из которой извлекают файлы
     * @param aKeyword Искомое ключевое слово
     */
    public SearchTask(BlockingQueue<File> aQueue, String aKeyword)
    {
        queue = aQueue;
        keyword = aKeyword;
    }

    @Override
    public void run()
    {
        try
        {
            boolean done = false;
            while (!done)
            {
                File theFile = queue.take();
                if (theFile == FileEnumerationTask.DUMMY)
                {
                    queue.put(theFile);
                    done = true;
                }
                else
                {
                    search(theFile);
                }
            }
        }
        catch (InterruptedException | FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ищет в файле заданное ключевое слово и печатает все строки, содержащие его.
     * <p/>
     * @param aFile Файл для поиска
     * <p/>
     * @throws FileNotFoundException
     */
    public void search(File aFile) throws FileNotFoundException
    {
        try (Scanner in = new Scanner(new FileInputStream(aFile)))
        {
            int lineNumber = 0;
            while (in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                {
                    System.out.printf("%s:%d:%s%n", aFile.getPath(), lineNumber, line);
                }
            }
        }
    }
}
