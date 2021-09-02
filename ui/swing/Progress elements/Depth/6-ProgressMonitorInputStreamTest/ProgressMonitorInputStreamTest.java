
/**
 * ���������� ������ ������ �� ������
 * <p/>
 * @version 1.03 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

/**
 * ���������, ��������������� ���������� �������� ������ ������ �� �����.
 */
public class ProgressMonitorInputStreamTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TextFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, ���������� ����, ������� ��������� �������� ������� �������� ���������� �����, � ��������� �������,
 * ��������������� ��� ����������� ����������� �����. ��������� ������� ��������� ����� �������� ����� � �� ���������
 * �������� ����� ��������������� � �������� ������� ����������� ������. ��� ��������� �������� �������� � ��������
 * �������� �����.
 */
class TextFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JMenuItem openItem;
    private JMenuItem exitItem;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    TextFrame()
    {
        setTitle("ProgressMonitorInputStreamTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ����.

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    openFile();
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }
            }
        });

        fileMenu.add(openItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
    }

    /**
     * ����� ����� ��� �����, �������� ����� � ��������� ������� � ��������� ��������� ������� � �������� �������
     * ����������� ������.
     */
    public void openFile() throws IOException
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                String fname = f.getName().toLowerCase();
                return fname.endsWith(".txt") || f.isDirectory();
            }

            @Override
            public String getDescription()
            {
                return "Text Files";
            }
        });

        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        final File f = chooser.getSelectedFile();

        // �������� ������������������ ������� � ��������� � ��� �������.

        FileInputStream fileIn = new FileInputStream(f);
        ProgressMonitorInputStream progressIn =
            new ProgressMonitorInputStream(this, "Reading " + f.getName(), fileIn);
        final Scanner in = new Scanner(progressIn);

        // �������� �� ����������� ������ ����������� � ��������� ������.

        Runnable readRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                final JTextArea textArea = new JTextArea();

                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    textArea.append(line);
                    textArea.append("\n");
                }
                in.close();

                // ������� ����������� ��������������� � ������ ��������� �������.
                EventQueue.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setContentPane(new JScrollPane(textArea));
                        validate();
                    }
                });
            }
        };

        Thread readThread = new Thread(readRunnable);
        readThread.start();
    }
}