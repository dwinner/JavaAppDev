/*
 * ������� ������ �������� Java 2D.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Print2Test implements Printable
{
    @Override
    public int print(Graphics g, PageFormat pf, int ind) throws PrinterException
    {
        // �������� �� ����� 5-�� �������
        if (ind > 4)
        {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Serif", Font.ITALIC, 30));
        g2.setColor(Color.black);
        g2.drawString("Page " + (ind + 1), 100, 100);
        g2.draw(new Ellipse2D.Double(100, 100, 200, 200));
        return Printable.PAGE_EXISTS;
    }

    public static void main(String[] args)
    {
        // 1) ������� ��������� �������
        PrinterJob pj = PrinterJob.getPrinterJob();
        // 2) ��������� ���������� ���� "��������� ��������"
        PageFormat pf = pj.pageDialog(pj.defaultPage());
        // 3) ������ ��� �������, ������ ������, ��������� �������� � ��������� ��������� ��������
        pj.setPrintable(new Print2Test(), pf);
        // 4) ���� ����� ���������� ��������� �����, ��... (�� ��������� ���������� ���� �����)
        pj.setCopies(2);
        // 5) ��������� ���������� ���� "������" (�������������)
        if (pj.printDialog())
        { // ���� ��...
            try
            {
                pj.print(); // ���������� � print(g, pf, ind)
            }
            catch (Exception e)
            {
                System.err.println(e);
            }
        }
        // 6) ��������� �������
        System.exit(0);
    }
}