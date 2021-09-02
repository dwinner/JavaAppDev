// ������ �������� ������������� � ��������.
import java.util.regex.*;

public class RegExpr
{
    public static void main(String args[])
    {
        Pattern pat;
        Matcher mat;
        boolean found;

        pat = Pattern.compile("Java");
        mat = pat.matcher("Java");
        found = mat.matches();  // ����� ����������

        System.out.println("Test for matching Java with Java.");
        if (found)
        {
            System.out.println("Matched");
        }
        else
        {
            System.out.println("Not matched");
        }
        System.out.println("Testing for matching Java with Java SE 6");
        mat = pat.matcher("Java SE 6"); // �������� ������ ������������ ����������

        found = mat.matches();  // ����� ����������

        if (found)
        {
            System.out.println("Matched");
        }
        else
        {
            System.out.println("Not matched");
        }
    }
}