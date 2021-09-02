import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Фрейм, содержащий панель для вывода двухмерного изображения, а также кнопки печати и настройки
 * параметров страницы.
 * <p/>
 */
public class PrintTestFrame extends JFrame
{
    private PrintComponent canvas;
    private PrintRequestAttributeSet attributes;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public PrintTestFrame() throws HeadlessException
    {
        setTitle("PrintTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new PrintComponent();
        add(canvas, BorderLayout.CENTER);

        attributes = new HashPrintRequestAttributeSet();

        JPanel buttonPanel = new JPanel();

        JButton printButton = new JButton("Print");
        buttonPanel.add(printButton);
        printButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPrintable(canvas);
                    if (job.printDialog(attributes))
                    {
                        job.print(attributes);
                    }
                }
                catch (HeadlessException | PrinterException e)
                {
                    JOptionPane.showMessageDialog(PrintTestFrame.this, e);
                }
            }
        });

        JButton pageSetupButton = new JButton("Page Setup");
        buttonPanel.add(pageSetupButton);
        pageSetupButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.pageDialog(attributes);
            }
        });
        
        add(buttonPanel, BorderLayout.NORTH);
    }
}
