// Область действия блока.
public class Scope
{
    public static void main(String args[])
    {
        int x;	// известна всему коду внутри main

        x = 10;
        if (x == 10)
        {	// начало новой области действия
            int y = 20;	// известна только в этом блоке

            // здесь известны x и y
            System.out.println("x and y: " + x + " " + y);
            x = y * 2;
        }
        // y = 100;	// Ошибка! y здесь не известна

        // x здесь ещё известна
        System.out.println("x is " + x);
    }
}
