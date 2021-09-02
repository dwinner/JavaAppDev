// ������ ������� � ������� �����������
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;

public class Print2Book
{
    public static void main(String[] args)
    {
        PrinterJob pj = PrinterJob.getPrinterJob();
        // ��� ���������� ����� ���������� ��������� ����������
        PageFormat pf1 = pj.defaultPage();
        pf1.setOrientation(PageFormat.LANDSCAPE);
        // ��������� ������ ������� �������� � ���������� ����
        PageFormat pf2 = pj.pageDialog(new PageFormat());
        Book bk = new Book();
        // ������ �������� - ��������� ����
        bk.append(new Cover(), pf2);
        // ��� ������ ��������
        bk.append(new Content(), pf2, 2);
        // ������������ ��� ������ - Pageable Job
        pj.setPageable(bk);
        if (pj.printDialog())
        {
            try
            {
                pj.print();
            }
            catch (Exception e)
            {
            }
        }
        System.exit(0);
    }
}

class Cover implements Printable
{
    @Override
    public int print(Graphics g, PageFormat pf, int ind) throws PrinterException
    {
        g.setFont(new Font("Helvetica-Bold", Font.PLAIN, 40));
        g.setColor(Color.black);
        int y = (int) (pf.getImageableY() + pf.getImageableHeight() / 2);
        g.drawString("��� ���������", 72, y);
        g.drawString("�� ���������� ����� �������", 72, y + 60);
        g.drawString("������� ����� ������.", 72, y + 120);
        return Printable.PAGE_EXISTS;
    }
}

class Content implements Printable
{
    @Override
    public int print(Graphics g, PageFormat pf, int ind) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Serif", Font.PLAIN, 12));
        g2.setColor(Color.black);
        int x = (int) pf.getImageableX() + 30;
        int y = (int) pf.getImageableY();
        g2.drawString("��� ������ �������� ������", x, y += 16);
        g2.drawString("��� ���������� � �����������", x, y += 16);
        g2.drawString("���������� � ���������� ����", x, y += 16);
        return Printable.PAGE_EXISTS;
    }
}