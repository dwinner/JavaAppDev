//: EnvironmentVariables.java
import java.util.*;
import java.io.*;

public class EnvironmentVariables
{
    private static File aFile;
    
    static
    {
        aFile = new File("Env.txt");
    }
    
    public static void main(String[] args) throws IOException
    {
        FileWriter out = new FileWriter(aFile);
        
        for (Map.Entry<String,String> entry : System.getenv().entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            out.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        out.close();
    }
}