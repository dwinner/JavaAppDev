// За каждым освобождаемым объектом следует try-finally

class NeedsCleanup
{   // Конструирование не может завершиться неудачно
    private static long counter = 1;
    private final long id = counter++;
    
    public void dispose()
    {
        System.out.println("NeedsCleanup " + id + " has finished");
    }
}

class ConstructionException extends Exception {}

class NeedsCleanup2 extends NeedsCleanup
{
    // Возможны сбои при конструировании.
    public NeedsCleanup2() throws ConstructionException {}
}

public class CleanupIdiom
{
    public static void main(String[] args)
    {
        // Секция 1
        NeedsCleanup nc1 = new NeedsCleanup();
        try { /*...*/ }
        finally { nc1.dispose(); }
        
        // Секция 2:
        // Если сбои при конструировании исключены, объекты можно группировать
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try
        {
            // ..
        }
        finally
        {
            nc3.dispose();  // Обратный порядок конструирования
            nc2.dispose();
        }
        
        // Секция 3:
        // Если при конструировании возможны сбои, каждый объект защищается отдельно:
        try
        {
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            try
            {
                NeedsCleanup2 nc5 = new NeedsCleanup2();
                try
                {
                    // ...
                }
                finally
                {
                    nc5.dispose();
                }
            }
            catch (ConstructionException e)
            {   // Конструктор объекта nc5
                System.out.println(e);
            }
            finally
            {
                nc4.dispose();
            }
        }
        catch (ConstructionException e)
        {   // Конструктор nc4
            System.out.println(e);
        }
    }
}