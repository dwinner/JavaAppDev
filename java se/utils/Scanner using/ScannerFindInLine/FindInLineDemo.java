// ������������ ���������� findInLine().
import java.util.*;

public class FindInLineDemo
{
    public static void main(String args[])
    {
        String instr = "Name: Tom Age: 28 ID: 77";

        Scanner conin = new Scanner(instr);

        // ����� � ���������� �������.
        conin.findInLine("Age:"); // ����� "�������"

        System.out.println(conin.hasNext() ? conin.next() : "Error!");
    }
}