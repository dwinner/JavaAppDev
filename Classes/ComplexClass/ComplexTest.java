// Проверим работу класса Complex
public class ComplexTest
{
    public static void main(String args[])
    {
        Complex z1 = new Complex();
        Complex z2 = new Complex(1.5);
        Complex z3 = new Complex(3.6, -2.2);
        Complex z4 = new Complex(z3);
        System.out.println();	// Оставляем пустую строку
        System.out.print("z1 = ");
        z1.pr();
        System.out.print("z2 = ");
        z2.pr();
        System.out.print("z3 = ");
        z3.pr();
        System.out.print("z4 = ");
        z4.pr();
        System.out.print(z4);	// Работает метод toString()
        z2.add(z3);
        System.out.print("z2 + z3 = ");
        z2.pr();
        z2.div(z3);
        System.out.print("z2 / z3 = ");
        z2.pr();
        z2 = z2.plus(z2);
        System.out.print("z2 + z2 = ");
        z2.pr();
        z3 = z2.slash(z1);
        System.out.print("z2 / z1 = ");
        z3.pr();
    }
}
