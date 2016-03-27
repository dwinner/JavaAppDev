//: io/DirList2.java
// »спользование анонимных внутренних классов.
// {Args: "D.*\.java"}
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class DirList2
{
    public static FilenameFilter filter(final String regex)
    {
        // —оздание анонимных внутренних классов:
        return new FilenameFilter()
        {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name)
            {
                return pattern.matcher(name).matches();
            }
        }; //  онец анонимного внутреннего класса
    }
  
    public static void main(String[] args)
    {
        File path = new File(".");
        String[] list;
        list = args.length == 0 ? path.list() : path.list(filter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}
/* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
