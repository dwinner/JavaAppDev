import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.*;

/**
 * Фрейм с текстовой областью для отображения содержимого файла из ZIP-архива, комбинированным
 * списком для выбора различных файлов из архива и меню для загрузки нового архива.
 */
public class ZipTestFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    private JComboBox<String> fileCombo;
    private JTextArea fileText;
    private String zipname;

    public ZipTestFrame()
    {
        setTitle("ZipTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // Добавление меню и пунктов меню Open (Открыть) и Exit (Выход)
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                int r = chooser.showOpenDialog(ZipTestFrame.this);
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    zipname = chooser.getSelectedFile().getPath();
                    fileCombo.removeAllItems();
                    scanZipFile();
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Добавление текстовой области и комбинированного списка
        fileText = new JTextArea();
        fileCombo = new JComboBox<>();
        fileCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loadZipFile((String) fileCombo.getSelectedItem());
            }
        });

        add(fileCombo, BorderLayout.SOUTH);
        add(new JScrollPane(fileText), BorderLayout.CENTER);
    }

    /**
     * Сканирование содержимого ZIP-архива и заполнение им комбинированного списка.
     */
    public void scanZipFile()
    {
        new SwingWorker<Void, String>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                /*
                 * -- Java SE 6: ZipInputStream zin = new ZipInputStream(new
                 * FileInputStream(zipname)); ZipEntry entry; while ((entry = zin.getNextEntry()) !=
                 * null) { publish(entry.getName()); zin.closeEntry(); } zin.close(); return null;
                 */
                try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname)))
                {
                    ZipEntry entry = zin.getNextEntry();
                    while (entry != null)
                    {
                        publish(entry.getName());
                        zin.closeEntry();
                        entry = zin.getNextEntry();
                    }
                }
                return null;
            }

            @Override
            protected void process(List<String> names)
            {
                for (String aName : names)
                {
                    fileCombo.addItem(aName);
                }
            }
        }.execute();
    }

    /**
     * Загрузка файла из ZIP-архива в текстовую область
     * <p/>
     * @param name имя файла из архива
     */
    public void loadZipFile(final String name)
    {
        fileCombo.setEnabled(false);
        fileText.setText("");
        new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws Exception
            {
                try
                {
                    /*
                     * -- Java SE 6 ZipInputStream zin = new ZipInputStream(new
                     * FileInputStream(zipname)); ZipEntry entry;
                     *
                     * // Поиск записи с соответствующим именем в архиве while ((entry =
                     * zin.getNextEntry()) != null) { if (entry.getName().equals(name)) { //
                     * Считывание записи в текстовую область Scanner in = new Scanner(zin); while
                     * (in.hasNextLine()) { fileText.append(in.nextLine()); fileText.append("\n"); }
                     * } zin.closeEntry(); } zin.close();
                     */
                    try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname)))
                    {
                        ZipEntry entry;

                        // Поиск записи с соответствующим именем в архиве
                        entry = zin.getNextEntry();
                        while (entry != null)
                        {
                            if (entry.getName().equals(name))
                            {
                                // Считывание записи в текстовую область
                                Scanner in = new Scanner(zin);
                                while (in.hasNextLine())
                                {
                                    fileText.append(in.nextLine());
                                    fileText.append(System.getProperty("line.separator"));
                                }
                                zin.closeEntry();
                                break;
                            }
                            else
                            {
                                zin.closeEntry();
                                entry = zin.getNextEntry();
                            }
                        }
                    }
                }
                catch (IOException e)
                {
                    new RuntimeException(e);
                }

                return null;
            }

            @Override
            protected void done()
            {
                fileCombo.setEnabled(true);
            }
        }.execute();
    }
}
