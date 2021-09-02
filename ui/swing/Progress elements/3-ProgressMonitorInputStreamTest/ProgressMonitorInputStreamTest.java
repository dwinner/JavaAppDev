import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 * Программа, демонстрирующая мониторинг процесса чтения данных из файла.
 * <p/>
 * @version 1.04 2007-08-01
 * @author Cay Horstmann
 */
public class ProgressMonitorInputStreamTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
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
 * Фрейм, содержащий меню, которое позволяет задавать команду загрузки текстового файла, и текстовую
 * область, предназначенную для отображения содержимого файла. Текстовая область создается после
 * загрузки файла и по окончании загрузки файла устанавливается в качестве области содержимого
 * фрейма. Это позволяет избежать мерцания в процессе загрузки файла.
 */
class TextFrame extends JFrame
{
    private JMenuItem openItem;
    private JMenuItem exitItem;
    private JTextArea textArea;
    private JFileChooser chooser;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    TextFrame() throws HeadlessException
    {
        setTitle("ProgressMonitorInputStreamTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    openFile();
                }
                catch (FileNotFoundException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });
        fileMenu.add(openItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
    }

    /**
     * Выбор файла для ввода, загрузка файла в текстовую область и установка текстовой области в
     * качестве области содержимого фрейма.
     * <p/>
     * @throws FileNotFoundException
     */
    public void openFile() throws FileNotFoundException
    {
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        final File f = chooser.getSelectedFile();

        // Создание последовательности потоков и включение в нее фильтра.

        FileInputStream fileIn = new FileInputStream(f);
        ProgressMonitorInputStream progressIn =
            new ProgressMonitorInputStream(this, "Reading " + f.getName(), fileIn);
        final Scanner in = new Scanner(progressIn);

        textArea.setText("");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    textArea.append(line);
                    textArea.append("\n");
                }
                in.close();
                return null;
            }
        };
        worker.execute();
    }
}