import java.io.*;
import java.util.*;

/**
 * Эта программа печатает все системные свойства.
 * @version 1.10 2002-07-06
 * @author Cay Horstmann
 */
public class SystemInfo
{
    public static void main(String args[])
    {
        try
        {
            Properties sysprops = System.getProperties();
            sysprops.store(System.out, "System Properties");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
