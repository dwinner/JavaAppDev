/*
 * Простая печать методами Java 2D.
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
        // Печатаем не более 5-ти страниц
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
        // 1) Создаем экземпляр задания
        PrinterJob pj = PrinterJob.getPrinterJob();
        // 2) Открываем диалоговое окно "Параметры страницы"
        PageFormat pf = pj.pageDialog(pj.defaultPage());
        // 3) Задаем вид задания, объект класса, рисующего страницу и выбранные параметры страницы
        pj.setPrintable(new Print2Test(), pf);
        // 4) Если нужно напечатать несколько копий, то... (по умолчанию печатается одна копия)
        pj.setCopies(2);
        // 5) Открываем диалоговое окно "Печать" (необязательно)
        if (pj.printDialog())
        { // Если ОК...
            try
            {
                pj.print(); // Обращается к print(g, pf, ind)
            }
            catch (Exception e)
            {
                System.err.println(e);
            }
        }
        // 6) Завершаем задание
        System.exit(0);
    }
}