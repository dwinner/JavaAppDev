// Демонстрация автоупаковки/автораспаковки
public class AutoBox
{
    public static void main(String args[])
    {
        Integer iOb = 100;	// автоупаковка int
        int i = iOb;		// автораспаковка
        System.out.println(i + " " + iOb);	// отображает 100 100
    }
}
