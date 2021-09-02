import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Задача подсчитывает файлы в каталоге и всех его подкаталогах,
 * которые содержат указанное ключевое слово.
 */
public class MatchCounter implements Callable<Integer>
{
    private File directory;
    private String keyword;
    private int count;

    /**
     * Конструктор MatchCounter.
     * @param directory Каталог, с которого начинается поиск.
     * @param keyword Искомое ключевое слово.
     */
    public MatchCounter(File directory, String keyword)
    {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception
    {
        count = 0;
        try
        {
            File[] files = directory.listFiles();
            ArrayList<Future<Integer>> results = new ArrayList<>();
            for (File aFile : files)
            {
                if (aFile.isDirectory())
                {
                    MatchCounter counter = new MatchCounter(aFile, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                }
                else
                {
                    if (search(aFile))
                    {
                        count++;
                    }
                }
            }
            for (Future<Integer> aResult : results)
            {
                try
                {
                    count += aResult.get();
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (InterruptedException e)
        {
            // TODO: Приемлемый выход
        }
        return count;
    }
    
    /**
     * Ищет в файле заданное ключевое слово.
     * @param aFile Файл, в котором идет поиск.
     * @return true, если слово найдено в файле.
     */
    public boolean search(File aFile)
    {   // TODO: Данный метод определенно можно вынести в статический класс.
        try
        {
            boolean found;
            try (Scanner in = new Scanner(new FileInputStream(aFile)))
            {
                found = false;
                while (!found && in.hasNextLine())
                {                
                    String line = in.nextLine();
                    if (line.contains(keyword))
                    {
                        found = true;
                    }
                }
            }
            return found;
        }
        catch (IOException e)
        {
            return false;
        }
    }
}
