// Сортировка строк: SortArray.java 
public class SortArray
{
    public static void main(String[] args)
    {
        String a[] =
        {
            "  Alena",
            "Alice  ",
            " alina",
            "  albina",
            "   Anastasya",
            "   ALLA ",
            "AnnA  "
        };

        for (int j = 0; j < a.length; j++)
        {
            a[j] = a[j].trim().toLowerCase();
        }

        for (int j = 0; j < a.length - 1; j++)
        {
            for (int i = j + 1; i < a.length; i++)
            {
                if (a[i].compareTo(a[j]) < 0)
                {
                    String temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        int i = -1;
        while (++i < a.length)
        {
            System.out.print(a[i] + " ");
        }
    }
}