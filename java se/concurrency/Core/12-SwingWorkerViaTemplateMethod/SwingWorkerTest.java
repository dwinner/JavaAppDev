
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

/**
 * Данная программа демонстрирует принципы создания рабочего потока, в рамках которого могут
 * решаться задачи, требующие длительных вычислений.
 */
public class SwingWorkerTest
{
    public static void main(String[] args) throws Exception
    {
        JFrame frame = new SwingWorkerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private SwingWorkerTest()
    {
        assert true;
    }
}

/**
 * В данном фрейме содержится текстовая область, предназначенная для отображения содержимого
 * текстового файла, меню, позволяющее открывать файл и отказываться от процесса его загрузки, и
 * сторока состояния, в которой отражается процесс загрузки файла.
 */
class SwingWorkerFrame extends JFrame
{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private Thread workerThread;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;

    SwingWorkerFrame()
    {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea = new JTextArea();
        add(new JScrollPane(textArea));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        statusLine = new JLabel();
        add(statusLine, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Диалоговое окно выбора файла
                int result = chooser.showOpenDialog(null);

                // Если файл выбран, отображается пиктограмма
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    readFile(chooser.getSelectedFile());
                }
            }
        });

        cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (workerThread != null)
                {
                    workerThread.interrupt();
                }
            }
        });
    }

    /**
     * Читает файл в асинхронном режиме; в процессе чтения обновляет UI
     * <p/>
     * @param file Файл для чтения
     */
    public void readFile(final File file)
    {
        Runnable task = new SwingWorkerTask()
        {
            @Override
            public void init()
            {
                lineNumber = 0;
                openItem.setEnabled(false);
                cancelItem.setEnabled(true);
            }

            @Override
            public void update()
            {
                statusLine.setText("" + lineNumber);
            }

            @Override
            public void finish()
            {
                workerThread = null;
                openItem.setEnabled(true);
                cancelItem.setEnabled(false);
                statusLine.setText("Done");
            }

            @Override
            public void work()
            {
                try
                {
                    Scanner in = new Scanner(new FileInputStream(file));
                    textArea.setText("");
                    while (!Thread.currentThread().isInterrupted() && in.hasNextLine())
                    {
                        lineNumber++;
                        line = in.nextLine();
                        textArea.append(line);
                        textArea.append("\n");
                        doUpdate();
                    }
                }
                catch (IOException e)
                {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
            private String line;
            private int lineNumber;
        };

        workerThread = new Thread(task);
        workerThread.start();
    }
}

/**
 * Расширяя данный класс, можно определить задачу, выполняющуюся в асинхронном режиме и обновляющую
 * интерфейс Swing.
 */
abstract class SwingWorkerTask implements Runnable
{
    private boolean done;

    /**
     * В данном методе реализуется решение задачи. Убедитесь в том, что для обновления состояния
     * интерфейса после выполнения части работы используется метод doUpdate(), а не update()!
     */
    public abstract void work() throws InterruptedException;

    /**
     * Переопределяя данный метод, можно реализовать операции с пользовательским интерфейсом,
     * выполняемые до начала работы.
     */
    abstract public void init();

    /**
     * Переопределяя данный метод, можно реализовать операции с UI, осуществляемые после выполнения
     * части работы.
     */
    abstract public void update();

    /**
     * Переопределяя данный метод, можно реализовать операции с UI, выполняемые после завершения
     * работы.
     */
    abstract public void finish();

    private void doInit()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                init();
            }
        });
    }

    /**
     * Данный метод вызывается из метода work() и обновляет интерфейс после выполнения части работы.
     */
    protected final void doUpdate()
    {
        if (done)
        {
            return;
        }
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                update();
            }
        });
    }

    private void doFinish()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
            }
        });
    }

    @Override
    public final void run()
    {
        doInit();
        try
        {
            done = false;
            work();
        }
        catch (InterruptedException ex)
        {
        }
        finally
        {
            done = true;
            doFinish();
        }
    }
}
