import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Эта задача перебирает все файлы в каталоге и его подкаталогах.
 */
public class FileEnumerationTask implements Runnable
{
    public static File DUMMY = new File("");
    private BlockingQueue<File> queue;
    private File startingDirectory;
    
    /**
     * Конструктор FileEnumerationTask.
     * @param aQueue Блокирующая очередь, в которую добавляются перечисленные файлы
     * @param aStartingDirectory Каталог, с которого начинается перечисление
     */
    public FileEnumerationTask(BlockingQueue<File> aQueue, File aStartingDirectory)
    {
        queue = aQueue;
        startingDirectory = aStartingDirectory;
    }
    
    /**
     * Рекурсивно перебирает все файлы в данном каталоге и его подкаталогах.
     * @param aDirectoryFile Каталог, с которого начинается поиск
     * @throws InterruptedException 
     */
    public void enumerate(File aDirectoryFile) throws InterruptedException
    {
        File[] files = aDirectoryFile.listFiles();
        for (File aFile : files)
        {
            if (aFile.isDirectory())
            {
                enumerate(aFile);
            }
            else
            {
                queue.put(aFile);
            }
        }
    }

    @Override
    public void run()
    {
        try
        {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        }
        catch (InterruptedException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
