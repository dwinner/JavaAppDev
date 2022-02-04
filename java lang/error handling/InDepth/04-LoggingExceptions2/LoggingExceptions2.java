// Регистрация перехваченных исключений.

import java.util.logging.*;
import java.io.*;

public class LoggingExceptions2
{
    private static final Logger logger = Logger.getLogger("LoggingExceptions2");
    
    static void logException(Exception e)
    {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
    
    public static void main(String[] args)
    {
        try
        {
            throw new NullPointerException();
        }
        catch (NullPointerException e)
        {
            logException(e);
        }
    }
}