// Регистрация исключений с использованием Logger
import java.util.logging.*;
import java.io.*;

class LoggingException extends Exception
{
    private static final Logger logger = Logger.getLogger("LoggingException");
    
    public LoggingException()
    {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class LoggingExceptions
{
    public static void main(String[] args)
    {
        try
        {
            throw new LoggingException();
        }
        catch (LoggingException e)
        {
            System.err.println("Catched " + e);
        }
        try
        {
            throw new LoggingException();
        }
        catch (LoggingException e)
        {
            System.out.println("Catched " + e);
        }
    }
}