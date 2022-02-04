//: EnumClass.java
// Возможности класса Enum
import static java.lang.System.out;

enum Shrubbery
{
    GROUND,
    CRAWLING,
    HANGING
}

public class EnumClass
{
    public static void main(String[] args)
    {
        for (Shrubbery s : Shrubbery.values())
        {
            out.println(s + " ordinal: " + s.ordinal());
            out.println(s.compareTo(Shrubbery.CRAWLING) + " ");
            out.println(s.equals(Shrubbery.CRAWLING) + " ");
            out.println(s == Shrubbery.CRAWLING);
            out.println(s.getDeclaringClass());
            out.println(s.name());
            out.println("----------------------");
        }
        
        // Вывод значения перечисления из строки:
        for (String s : "HANGING CRAWLING GROUND".split(" "))
        {
            Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
            out.println(shrub);
        }
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~
