// Тело цикла может быть пустым.
public class NoBody
{
    public static void main(String args[])
    {
        int i, j;

        i = 100;
        j = 200;

        // найти среднюю точку между i и j
        while (++i < --j) ;	// В этом цикле нет тела

        System.out.println("Average comma is " + i);
    }
}
