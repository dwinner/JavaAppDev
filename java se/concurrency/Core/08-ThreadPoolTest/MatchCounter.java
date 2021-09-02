import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Задача подсчитывает файлы в каталоге и всех его подкаталогах,
 * которые содержат указанное ключевое слово.
 */
public class MatchCounter implements Callable<Integer>
{
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;
    
    /**
     * Конструктор MatchCounter
     * @param aDirectory Каталог, с которого начинается поиск
     * @param aKeyword Искомое ключевое слово
     * @param aPool Пул потоков для запуска задач
     */
    public MatchCounter(File aDirectory, String aKeyword, ExecutorService aPool)
    {
        directory = aDirectory;
        keyword = aKeyword;
        pool = aPool;
    }

    @Override
    public Integer call()
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
                    MatchCounter counter = new MatchCounter(aFile, keyword, pool);
                    Future<Integer> futureResult = pool.submit(counter);
                    results.add(futureResult);
                }
                else
                {
                    if (search(aFile))
                    {
                        count++;
                    }
                }
            }
            for (Future<Integer> aFutureResult : results)
            {
                try
                {
                    count += aFutureResult.get();
                }
                catch (ExecutionException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        return count;
    }

    /**
     * Ищет в файле заданное ключевое слово.
     * @param aFile Файл, в котором идет поиск
     * @return true, если слово найдено в файле
     */
    private boolean search(File aFile)
    {
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
