
/**
 * ������ ������.
 * <p/>
 * @version 1.01 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.*;
import javax.swing.*;

/**
 * ������ ������������� ����� ������. ��������� ��������� ������� ����������� GIF, ��������� ����� ������ ������,
 * ������� ������������ ������ ��� ���������.
 */
public class PrintServiceTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PrintServiceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, � ������� ������������ �����������, ��������������� ��� ������. �� �������� ����, ����������� ���������
 * ����������� ����, �������� ������ ������ � �������� ������.
 */
class PrintServiceFrame extends JFrame
{
    private JLabel label;
    private String fileName;
    private PrintService currentService;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    PrintServiceFrame()
    {
        setTitle("PrintServiceTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ������ ����.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                openFile();
            }
        });

        JMenuItem printItem = new JMenuItem("Print");
        menu.add(printItem);
        printItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                printFile();
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });

        menu = new JMenu("Printer");
        menuBar.add(menu);
        DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
        addPrintServices(menu, flavor);

        // ��� ������������� ����������� ������������ ������ �����.
        label = new JLabel();
        add(label);
    }

    /**
     * ���������� � ���� ����� ������.
     * <p/>
     * @param menu   ����, � ������� ����������� ������ ������
     * @param flavor ��� ���������, ������� ������ ��������������
     */
    private void addPrintServices(JMenu menu, DocFlavor flavor)
    {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < services.length; i++)
        {
            final PrintService service = services[i];
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(service.getName());
            menu.add(item);
            if (i == 0)
            {
                item.setSelected(true);
                currentService = service;
            }
            group.add(item);
            item.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    currentService = service;
                }
            });
        }
    }

    /**
     * �������� GIF-����� � ����� �����������.
     */
    public void openFile()
    {
        // ���������� ���� ������ ������.
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        // ����� ���� ������ � ����������� .gif
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
            }

            @Override
            public String getDescription()
            {
                return "GIF Images";
            }
        });

        // ����������� ����������� ����.
        int r = chooser.showOpenDialog(PrintServiceFrame.this);

        // ���� ����������� ���� ������, �� ������������ � ������� �����.
        if (r == JFileChooser.APPROVE_OPTION)
        {
            fileName = chooser.getSelectedFile().getPath();
            label.setIcon(new ImageIcon(fileName));
        }
    }

    /**
     * ����� �� ������ ����� � �������������� ������� ������ ������.
     */
    public void printFile()
    {
        try
        {
            if (fileName == null)
            {
                return;
            }
            if (currentService == null)
            {
                return;
            }
            FileInputStream in = new FileInputStream(fileName);
            DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
            Doc doc = new SimpleDoc(in, flavor, null);
            DocPrintJob job = currentService.createPrintJob();
            job.print(doc, null);
        }
        catch (FileNotFoundException | PrintException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}
