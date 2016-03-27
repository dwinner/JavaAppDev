// Демонстрирует delete() и deleteCharAt().
public class deleteDemo
{
    public static void main(String args[])
    {
        StringBuffer sb = new StringBuffer("This is a test.");

        System.out.println("Before delete: " + sb);
        sb.delete(4, 7);
        System.out.println("After delete: " + sb);

        sb.deleteCharAt(0);
        System.out.println("After deleteCharAt: " + sb);
    }
}
