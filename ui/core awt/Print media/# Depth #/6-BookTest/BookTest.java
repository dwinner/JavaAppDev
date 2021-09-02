
/**
 * ��������������� ������. ��������������� ��������.
 * <p/>
 * @version 1.11 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;

/**
 * ������ ���������, ������� ������������� ������ ���������������� �������-�����. � �������� ����� ������������ ������
 * �� ������� �������� �������, ����� ������� ������������� �� ������ ���������. ��� ��������� ����� �������� ����������
 * ���� ���������������� ���������.
 */
public class BookTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new BookTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ����� � ����� �������������� ��� ����� ������ ������� � �������� ������, ��������� ���������� �������� �
 * ���������������� ���������.
 */
class BookTestFrame extends JFrame
{
    private JTextField text;
    private PageFormat pageFormat;
    private PrintRequestAttributeSet attributes;

    BookTestFrame()
    {
        setTitle("BookTest");

        text = new JTextField();
        add(text, BorderLayout.NORTH);

        attributes = new HashPrintRequestAttributeSet();

        JPanel buttonPanel = new JPanel();

        JButton printButton = new JButton("Print");
        buttonPanel.add(printButton);
        printButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(makeBook());
                    if (job.printDialog(attributes))
                    {
                        job.print(attributes);
                    }
                }
                catch (PrinterException e)
                {
                    JOptionPane.showMessageDialog(BookTestFrame.this, e);
                }
            }
        });

        JButton pageSetupButton = new JButton("Page setup");
        buttonPanel.add(pageSetupButton);
        pageSetupButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                PrinterJob job = PrinterJob.getPrinterJob();
                pageFormat = job.pageDialog(attributes);
            }
        });

        JButton printPreviewButton = new JButton("Print preview");
        buttonPanel.add(printPreviewButton);
        printPreviewButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                PrintPreviewDialog dialog = new PrintPreviewDialog(makeBook());
                dialog.setVisible(true);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * �������� �������-����� � ��������� ��������� � �������� ����������.
     */
    public Book makeBook()
    {
        if (pageFormat == null)
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            pageFormat = job.defaultPage();
        }
        Book book = new Book();
        String message = text.getText();
        Banner banner = new Banner(message);
        int pageCount = banner.getPageCount((Graphics2D) getGraphics(), pageFormat);
        book.append(new CoverPage(message + " (" + pageCount + " pages)"), pageFormat);
        book.append(banner, pageFormat, pageCount);
        return book;
    }
}

/**
 * ������, �������������� ����� ������ ������, ������������� �� ���������� ���������.
 */
class Banner implements Printable
{
    private String message;
    private double scale;

    /**
     * �������� �������
     * <p/>
     * @param m ������
     */
    Banner(String m)
    {
        message = m;
    }

    /**
     * ����������� ���������� ������� � ������ �������.
     * <p/>
     * @param g2 ����������� ��������
     * @param pf ������ ��������
     * <p/>
     * @return ��������� ����� �������
     */
    public int getPageCount(Graphics2D g2, PageFormat pf)
    {
        if (message.isEmpty())
        {
            return 0;
        }
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        Rectangle2D bounds = f.getStringBounds(message, context);
        scale = pf.getImageableHeight() / bounds.getHeight();
        double width = scale * bounds.getWidth();
        int pages = (int) Math.ceil(width / pf.getImageableWidth());
        return pages;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {
        Graphics2D g2 = (Graphics2D) g;
        if (page > getPageCount(g2, pf))
        {
            return Printable.NO_SUCH_PAGE;
        }
        g2.translate(pf.getImageableX(), pf.getImageableY());

        drawPage(g2, pf, page);
        return Printable.PAGE_EXISTS;
    }

    public void drawPage(Graphics2D g2, PageFormat pf, int page)
    {
        if (message.isEmpty())
        {
            return;
        }
        page--; // ���� ��������� ��������.

        drawCropMarks(g2, pf);
        g2.clip(new Rectangle2D.Double(0, 0, pf.getImageableWidth(), pf.getImageableHeight()));
        g2.translate(-page * pf.getImageableWidth(), 0);
        g2.scale(scale, scale);
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        TextLayout layout = new TextLayout(message, f, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, layout.getAscent());
        Shape outline = layout.getOutline(transform);
        g2.draw(outline);
    }

    /**
     * ����������� ��������, ���������� �������, ��������� ��� ������.
     * <p/>
     * @param g2 ����������� ��������
     * @param pf ������ ��������
     */
    public void drawCropMarks(Graphics2D g2, PageFormat pf)
    {
        final double C = 36; // ������ ������� = 1/2 �����
        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();
        g2.draw(new Line2D.Double(0, 0, 0, C));
        g2.draw(new Line2D.Double(0, 0, C, 0));
        g2.draw(new Line2D.Double(w, 0, w, C));
        g2.draw(new Line2D.Double(w, 0, w - C, 0));
        g2.draw(new Line2D.Double(0, h, 0, h - C));
        g2.draw(new Line2D.Double(0, h, C, h));
        g2.draw(new Line2D.Double(w, h, w, h - C));
        g2.draw(new Line2D.Double(w, h, w - C, h));
    }
}

/**
 * ��������� �������� � ����������.
 */
class CoverPage implements Printable
{
    private String title;

    /**
     * �������� ��������� ��������.
     * <p/>
     * @param t ���������
     */
    CoverPage(String t)
    {
        title = t;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {
        if (page >= 1)
        {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.black);
        g2.translate(pf.getImageableX(), pf.getImageableY());
        FontRenderContext context = g2.getFontRenderContext();
        Font f = g2.getFont();
        TextLayout layout = new TextLayout(title, f, context);
        float ascent = layout.getAscent();
        g2.drawString(title, 0, ascent);
        return Printable.PAGE_EXISTS;
    }
}

/**
 * ������ ����� ��������� ������������� ���������� ���� ���������������� ���������.
 */
class PrintPreviewDialog extends JDialog
{
    private PrintPreviewCanvas canvas;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    /**
     * �������� ����������� ���� ���������������� ���������.
     * <p/>
     * @param p     ������ Printable
     * @param pf    ������ ��������
     * @param pages ����� ������� � p
     */
    PrintPreviewDialog(Printable p, PageFormat pf, int pages)
    {
        Book book = new Book();
        book.append(p, pf, pages);
        layoutUI(book);
    }

    /**
     * �������� ����������� ���� ���������������� ���������.
     * <p/>
     * @param b ������ Book
     */
    PrintPreviewDialog(Book b)
    {
        layoutUI(b);
    }

    /**
     * ���������� UI � ���������� ���� ���������������� ���������.
     * <p/>
     * @param book ������ Book, ��������������� ��� ���������
     */
    private void layoutUI(Book book)
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new PrintPreviewCanvas(book);
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton nextButton = new JButton("Next");
        buttonPanel.add(nextButton);
        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.flipPage(1);
            }
        });

        JButton previousButton = new JButton("Previous");
        buttonPanel.add(previousButton);
        previousButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.flipPage(-1);
            }
        });

        JButton closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                setVisible(false);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }
}

/**
 * ����� ��� ���������������� ���������.
 */
class PrintPreviewCanvas extends JPanel
{
    private Book book;
    private int currentPage;

    /**
     * �������� ������ ��� ���������������� ���������.
     * <p/>
     * @param b ������ Book, ��������������� ��� ���������
     */
    PrintPreviewCanvas(Book b)
    {
        book = b;
        currentPage = 0;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        PageFormat pageFormat = book.getPageFormat(currentPage);

        double xoff; // �������� ������ �������� � ���� �� ��� x.
        double yoff; // �������� ������ �������� � ���� �� ��� y.
    	/*
         * ����������� ��������������� ��� ���������� �������� ������������ �������� � ������������ � ��������� ����.
         */
        double scale;
        double px = pageFormat.getWidth();
        double py = pageFormat.getHeight();
        double sx = getWidth() - 1;
        double sy = getHeight() - 1;
        if (px / py < sx / sy)
        { // ������������� �� �����������
            scale = sy / py;
            xoff = 0.5 * (sx - scale * px);
            yoff = 0;
        }
        else
        {	// ������������� �� ���������.
            scale = sx / px;
            xoff = 0;
            yoff = 0.5 * (sy - scale * py);
        }
        g2.translate((float) xoff, (float) yoff);
        g2.scale((float) scale, (float) scale);

        // ����������� �������� �������� (���� ������������)
        Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
        g2.setPaint(Color.white);
        g2.fill(page);
        g2.setPaint(Color.black);
        g2.draw(page);

        Printable printable = book.getPrintable(currentPage);
        try
        {
            printable.print(g2, pageFormat, currentPage);
        }
        catch (PrinterException e)
        {
            g2.draw(new Line2D.Double(0, 0, px, py));
            g2.draw(new Line2D.Double(px, 0, 0, py));
        }
    }

    /**
     * ������� � �������� � �������� �������.
     * <p/>
     * @param by ����� ������� ��� ��������������. ������������� �������� ������������� �������������� �����
     */
    public void flipPage(int by)
    {
        int newPage = currentPage + by;
        if (0 <= newPage && newPage < book.getNumberOfPages())
        {
            currentPage = newPage;
            repaint();
        }
    }
}
