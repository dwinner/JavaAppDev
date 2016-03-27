/**
 * ќпределение простого обобщенного класса.
 * @version 1.00 2004-05-10
 * @author Cay Horstmann
 */
public class PairTest1
{
    public static void main(String[] args)
    {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

class ArrayAlg
{
    /**
     * ѕолучает минимальное и максимальное значение массива строк.
     * @param a ћассив строк
     * @return a ѕара минимального и максимального значений или null, если a пуст
     */
    public static Pair<String> minmax(String[] a)
    {
        if (a == null || a.length == 0)
            return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<String>(min, max);
    }
}
