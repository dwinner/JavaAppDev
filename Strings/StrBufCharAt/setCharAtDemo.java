// Демонстрирует charAt() и setCharAt().
public class setCharAtDemo
{
    public static void main(String args[])
    {
        StringBuffer sb = new StringBuffer("Hello");
        System.out.println("buffer before = " + sb);	// Hello
        System.out.println("charAt(1) before = " + sb.charAt(1));	// e
        sb.setCharAt(1, 'i');	// Hillo
        sb.setLength(2);	// Hi
        System.out.println("buffer after = " + sb);
        System.out.println("charAt(1) after = " + sb.charAt(1));	// i
    }
}
