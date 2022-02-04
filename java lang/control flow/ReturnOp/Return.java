// Оператор return.
public class Return
{
    public static void main(String args[])
    {
        boolean t = true;

        System.out.println("Before return-operator");

        if (t)
        {
            return;	// Возврат в вызывающую программу.
        }
        System.out.println("This operator has never been executed.");
    }
}
