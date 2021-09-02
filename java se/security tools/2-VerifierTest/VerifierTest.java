
import java.awt.*;
import java.applet.*;

/**
 * ������, ��������������� ������ ������������ ��������� ���� ����������� ������. � ������
 * ������������� ������������������ ��������� ��� ��������� ����� ������ ����������� ������ ������
 * ���� �������� ���������� ������.
 * <p/>
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */
public class VerifierTest extends Applet
{
    public static void main(String[] args)
    {
        System.out.println("1 + 2 == " + fun());
    }

    /**
     * �����, ����������� 1 + 2.
     * <p/>
     * @return 3, ���� ��� �� ���������.
     */
    public static int fun()
    {
        int m;
        int n;
        m = 1;
        n = 2;
        // � ����� ������ �������� ��������� ��������� �� "m = 2". ��� �����
        // ���������� �������� ����������������� ��������.
        int r = m + n;
        return r;
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawString("1 + 2 == " + fun(), 20, 20);
    }
}