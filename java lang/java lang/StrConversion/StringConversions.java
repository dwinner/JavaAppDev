/* Преобразует целые числа в двоичные,
шестнадцатиричные и восьмеричные. */

public class StringConversions
{
    public static void main(String args[])
    {
        int num = 19648;

        System.out.println(num + " in binary form: " + Integer.toBinaryString(num));
        System.out.println(num + " in octal form: " + Integer.toOctalString(num));
        System.out.println(num + " in hexadecimal form: " + Integer.toHexString(num));
    }
}
