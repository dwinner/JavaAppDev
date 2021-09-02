import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

/**
 * Этот фрейм содержит текстовую область для отображения текстового файла, меню для открытия файла и
 * прерывания процесса открытия, а также строку состояния для отображения хода загрузки файла.
 */
public class SwingWorkerFrame extends JFrame
{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder, ProgressData> textReader;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;

    public SwingWorkerFrame()
    {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        textArea = new JTextArea();
        add(new JScrollPane(textArea));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        statusLine = new JLabel(" ");
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
            public void actionPerformed(ActionEvent e)
            {
                // Отобразить диалог для выбора файла.
                int result = chooser.showOpenDialog(null);
                // Если файл выбран, начать процесс загрузки в текстовую область.
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    textArea.setText("");
                    openItem.setEnabled(false);
                    textReader = new TextReader(chooser.getSelectedFile());
                    textReader.execute();
                    cancelItem.setEnabled(!openItem.isEnabled());
                }
            }
        });
        cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textReader.cancel(true);
            }
        });
    }

    private class ProgressData
    {
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder, ProgressData>
    {
        private File file;
        private StringBuilder text = new StringBuilder(0xFFFF);

        TextReader(File selectedFile)
        {
            file = selectedFile;
        }

        @Override
        public StringBuilder doInBackground() throws IOException, InterruptedException
        {   // Этот метод выполняется в рабочем потоке,
            // он не затрагивает компоненты Swing.
            int lineNumber = 0;
            Scanner in = new Scanner(new FileInputStream(file));
            while (in.hasNextLine())
            {
                String line = in.nextLine();
                lineNumber++;
                text.append(line);
                text.append("\n");
                ProgressData data = new ProgressData();
                data.number = lineNumber;
                data.line = line;
                publish(data);
                // TimeUnit.SECONDS.sleep(1);  // для проверки возможности отмены; в реале не нужен
            }

            return text;
        }

        @Override
        public void process(List<ProgressData> data)
        {   // Этот метод выполняется в потоке диспетчера событий.
            if (isCancelled())
            {
                return;
            }
            StringBuilder b = new StringBuilder(0xFF/2);
            statusLine.setText("" + data.get(data.size() - 1).number);
            for (ProgressData d : data)
            {
                b.append(d.line);
                b.append("\n");
            }
            textArea.append(b.toString());
        }

        @Override
        public void done()
        {   // Завершающие действия.
            try
            {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            catch (CancellationException ex)
            {
                textArea.setText("");
                statusLine.setText("Cancelled");
            }
            catch (ExecutionException ex)
            {
                statusLine.setText("" + ex.getCause());
            }
            cancelItem.setEnabled(false);
            openItem.setEnabled(!cancelItem.isEnabled());
        }
    }
}
