//: io/DirList3.java
// —оздание анонимного внутреннего класса "на месте".
// {Args: "D.*\.java"}
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class DirList3
{
    public static void main(final String[] args)
    {
        File path = new File(".");
        String[] list;
        list = args.length == 0
            ? path.list()
            : path.list(new FilenameFilter()
                {
                    private Pattern pattern = Pattern.compile(args[0]);
                    public boolean accept(File dir, String name)
                    {
                        return pattern.matcher(name).matches();
                    }
                }
              );
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
