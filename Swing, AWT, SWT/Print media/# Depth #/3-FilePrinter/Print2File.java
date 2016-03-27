/*
 * ѕечать текстового файла.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Print2File
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Usage: java Print2File path");
            System.exit(0);
        }
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
        pj.setPrintable(new FilePagePainter(args[0]), pf);
        if (pj.printDialog())
        {
            try
            {
                pj.print();
            }
            catch (PrinterException e)
            {
            }
        }
        System.exit(0);
    }
}

class FilePagePainter implements Printable
{
    private BufferedReader br;
    private String file;
    private int page = -1;
    private boolean eof;
    private String[] line;
    private int numLines;

    FilePagePainter(String file)
    {
        this.file = file;
        try
        {
            br = new BufferedReader(new FileReader(file));
        }
        catch (IOException e)
        {
            eof = true;
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int ind) throws PrinterException
    {
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        int h = (int) pf.getImageableHeight();
        int x = (int) pf.getImageableX() + 10;
        int y = (int) pf.getImageableY() + 12;
        try
        {
            if (ind != page)
            {   // ≈сли система печати запросила эту страницу первый раз
                if (eof)
                {
                    return Printable.NO_SUCH_PAGE;
                }
                page = ind;
                line = new String[h / 12];    // массив строк на странице
                numLines = 0;   // число строк на странице
                // читаем строки из файла и формируем массив строк
                while (y + 48 < pf.getImageableY() + h)
                {
                    line[numLines] = br.readLine();
                    if (line[numLines] == null)
                    {
                        eof = true;
                        break;
                    }
                    numLines++;
                    y += 12;
                }
            }
            // –азмещаем колонтитул
            y = (int) pf.getImageableY() + 12;
            g.drawString("File: " + file + ", page " + (ind + 1), x, y);
            // ќставл€ем две пустые строки
            y += 36;
            // –азмещаем строки текста текущей страницы
            for (int i = 0; i < numLines; i++)
            {
                g.drawString(line[i], x, y);
                y += 12;
            }
            return Printable.PAGE_EXISTS;
        }
        catch (IOException e)
        {
            return Printable.NO_SUCH_PAGE;
        }
    }
}