//: UpcastEnum.java
// ≈сли вы выполните не€вное низход€щее преобразование к типу Enum, вы "потер€ете" доступ
// к методу values(), однако есть альтернативное решение...

enum Search { HITHER, YON }

public class UpcastEnum
{
    public static void main(String[] args)
    {
        Search[] vals = Search.values();
        Enum e = Search.HITHER; // Upcast
        // e.values(); // No values() in Enum
        for (Enum en : e.getClass().getEnumConstants())
            System.out.println(en);
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
HITHER
YON
*///:~
