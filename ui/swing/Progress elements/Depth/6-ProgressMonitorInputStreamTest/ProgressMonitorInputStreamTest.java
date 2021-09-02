
/**
 * Мониторинг чтения данных из потока
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
 * Программа, демонстрирующая мониторинг процесса чтения данных из файла.
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
 * Фрейм, содержащий меню, которое позволяет задавать команду загрузки текстового файла, и текстовую область,
 * предназначенную для отображения содержимого файла. Текстовая область создается после загрузки файла и по окончании
 * загрузки файла устанавливается в качестве области содержимого фрейма. Это позволяет избежать мерцания в процессе
 * загрузки файла.
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

        // Создание меню.

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
     * Выбор файла для ввода, загрузка файла в текстовую область и установка текстовой области в качестве области
     * содержимого фрейма.
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

        // Создание последовательности потоков и включение в нее фильтра.

        FileInputStream fileIn = new FileInputStream(f);
        ProgressMonitorInputStream progressIn =
            new ProgressMonitorInputStream(this, "Reading " + f.getName(), fileIn);
        final Scanner in = new Scanner(progressIn);

        // Действия по мониторингу должны выполняться в отдельном потоке.

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

                // Область содержимого устанавливается в потоке обработки событий.
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