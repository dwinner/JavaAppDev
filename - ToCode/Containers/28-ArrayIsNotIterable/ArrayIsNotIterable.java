//: ArrayIsNotIterable.java
import java.util.*;

public class ArrayIsNotIterable
{
    static <T> void test(Iterable<T> ib)
    {
        for (T t : ib)
            System.out.print(t + " ");
    }
  
    public static void main(String[] args)
    {
        test(Arrays.asList(1, 2, 3));
        String[] strings = { "A", "B", "C" };
        // Массив работает в foreach, но не является Iterable:
        //! test(strings);
        // Его необходимо явно преобразовывать к Iterable:
        test(Arrays.asList(strings));
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
1 2 3 A B C
*///:~
