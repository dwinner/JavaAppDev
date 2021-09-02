
/**
 * Поточные службы печати
 * <p/>
 * @version 1.01 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;

/**
 * Пример использования поточной службы печати для вывода двухмерной графики в файл формата PostScript.
 */
public class StreamPrintServiceTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new StreamPrintServiceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с панелью для вывода двухмерной графики и с кнопками для печати в формате PostScript и указания формата
 * страницы.
 */
class StreamPrintServiceFrame extends JFrame
{
    private PrintPanel canvas;
    private PrintRequestAttributeSet attributes;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    StreamPrintServiceFrame()
    {
        setTitle("StreamPrintServiceTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new PrintPanel();
        add(canvas, BorderLayout.CENTER);

        attributes = new HashPrintRequestAttributeSet();

        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("Print");
        buttonPanel.add(printButton);
        printButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                String fileName = getFile();
                if (fileName != null)
                {
                    printPostScript(fileName);
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
                job.pageDialog(attributes);
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
    }

    /**
     * Данный метод предоставляет пользователю возможность выбрать PostScript-файл.
     * <p/>
     * @return Имя файла или null, если файл не выбран
     */
    public String getFile()
    {
        // Диалоговое окно выбора файла
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        // Выбор файлов с суффиксом .ps
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".ps") || f.isDirectory();
            }

            @Override
            public String getDescription()
            {
                return "PostScript Files";
            }
        });

        // Вывод диалогового окна выбора файла.
        int r = chooser.showSaveDialog(this);

        // Если файл выбран, возвращается путь.
        return (r == JFileChooser.APPROVE_OPTION) ? chooser.getSelectedFile().getPath() : null;
    }

    /**
     * Вывод двумерной графики в PostScript-файл.
     * <p/>
     * @param fileName Имя PostScript-файла
     */
    public void printPostScript(String fileName)
    {
        try
        {
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            String mimeType = "application/postscript";
            StreamPrintServiceFactory[] factories =
                StreamPrintServiceFactory.lookupStreamPrintServiceFactories(flavor, mimeType);

            FileOutputStream out = new FileOutputStream(fileName);
            if (factories.length == 0)
            {
                return;
            }
            StreamPrintService service = factories[0].getPrintService(out);

            Doc doc = new SimpleDoc(canvas, flavor, null);
            DocPrintJob job = service.createPrintJob();
            job.print(doc, attributes);
        }
        catch (FileNotFoundException | PrintException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}

/**
 * Панель с двухмерным изображением для вывода на экран и печати.
 */
class PrintPanel extends JPanel implements Printable
{
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {
        if (page >= 1)
        {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.draw(new Rectangle2D.Double(0, 0, pf.getImageableWidth(), pf.getImageableHeight()));

        drawPage(g2);
        return Printable.PAGE_EXISTS;
    }

    /**
     * Данный метод рисует изображение на экране и в графическом контексте принтера.
     * <p/>
     * @param g2 Графический контекст
     */
    public void drawPage(Graphics2D g2)
    {
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        GeneralPath clipShape = new GeneralPath();

        TextLayout layout = new TextLayout("Hello", f, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
        Shape outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        layout = new TextLayout("World", f, context);
        transform = AffineTransform.getTranslateInstance(0, 144);
        outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        g2.draw(clipShape);
        g2.clip(clipShape);

        final int NLINES = 50;
        Point2D p = new Point2D.Double(0, 0);
        for (int i = 0; i < NLINES; i++)
        {
            double x = (2 * getWidth() * i) / NLINES;
            double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
            Point2D q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p, q));
        }
    }
}
