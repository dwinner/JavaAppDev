// Демонстрирует строковый класс StringTokenizer.
import java.util.StringTokenizer;

public class STDemo
{
    private static String in = "name=Java: The Complete Reference;"
        + "author=Nauton and Schildt;"
        + "publisher=Osbourne/McGraw-Hill;"
        + "copyright=1999";

    public static void main(String args[])
    {
        StringTokenizer st = new StringTokenizer(in, "=;");

        while (st.hasMoreTokens())
        {
            String key = st.nextToken();
            String val = st.nextToken();
            System.out.println(key + "\t" + val);
        }
    }
}
