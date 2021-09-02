// "Подавление" контролируемых исключений.
import java.io.*;

class WrapCheckedException
{
    void throwRuntimeException(int type)
    {
        try
        {
            switch (type)
            {
                case 0:  throw new FileNotFoundException();
                case 1:  throw new IOException();
                case 2:  throw new RuntimeException("Where am I?!");
                default: return;
            }
        }
        catch (Exception e)
        {
            // Превращаем в неконтролируемое:
            throw new RuntimeException(e);
        }
    }
}

class SomeOtherException extends Exception {}

public class TurnOffChecking
{
    public static void main(String[] args)
    {
        WrapCheckedException wce = new WrapCheckedException();
        
        // Можно вызвать throwRuntimeException() без блока try,
        // и позволить исключению RuntimeException покинуть метод
        wce.throwRuntimeException(3);
        // Или перехватить исключение:
        for (int i = 0; i < 4; i++)
        {
            try
            {
                if (i < 3)
                {
                    wce.throwRuntimeException(i);
                }
                else
                {
                    throw new SomeOtherException();
                }
            }
            catch (SomeOtherException e)
            {
                System.out.println("SomeOtherException " + e);
            }
            catch (RuntimeException re)
            {
                try
                {
                    throw re.getCause();
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("FileNotFoundException " + e);
                }
                catch (IOException e)
                {
                    System.out.println("IOException " + e);
                }
                catch (Throwable e)
                {
                    System.out.println("Throwable " + e);
                }
            }
        }
    }
}