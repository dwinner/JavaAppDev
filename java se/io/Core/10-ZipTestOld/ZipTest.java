
/**
 * ������ ������ �� zip-�������.
 * <p/>
 * @version 1.31 2004-05-11
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class ZipTest
{
    public static void main(String[] args)
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    ZipTestFrame frame = new ZipTestFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    try
                    {
                        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
                        UIManager.setLookAndFeel(infos[new Random(47).nextInt(infos.length)].
                            getClassName());
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                           UnsupportedLookAndFeelException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                    SwingUtilities.updateComponentTreeUI(frame);
                }
            });
        }
        catch (InterruptedException | InvocationTargetException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}

/**
 * ����� � ��������� ��������, ��������������� ��� ����������� ����������� ����� �� ZIP-������,
 * �������������� ������� ��� ������ ������ � ������ � ���� ��� �������� ������ ������.
 */
class ZipTestFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;
    private JComboBox<String> fileCombo;
    private JTextArea fileText;
    private String zipname;

    ZipTestFrame()
    {
        setTitle("ZipTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ������ ���� � ������� Open � Exit
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new OpenAction());

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

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // ���������� ��������� ������� � ��������������� ������
        fileText = new JTextArea();
        fileCombo = new JComboBox<>();
        fileCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                loadZipFile((String) fileCombo.getSelectedItem());
            }
        });

        add(fileCombo, BorderLayout.SOUTH);
        add(new JScrollPane(fileText), BorderLayout.CENTER);
    }

    /**
     * ���������� ��� ������ ���� File->Open.
     */
    private class OpenAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            // ���� ��� ������ ����� ������.
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            ExtensionFileFilter filter = new ExtensionFileFilter();
            filter.addExtension(".zip");
            filter.addExtension(".jar");
            filter.setDescription("ZIP archives");
            chooser.setFileFilter(filter);
            int r = chooser.showOpenDialog(ZipTestFrame.this);
            if (r == JFileChooser.APPROVE_OPTION)
            {
                zipname = chooser.getSelectedFile().getPath();
                scanZipFile();
            }
        }
    }

    /**
     * �������� ����������� zip-������ � ���������� ������.
     */
    public void scanZipFile()
    {
        fileCombo.removeAllItems();
        try
        {
            try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname)))
            {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null)
                {
                    fileCombo.addItem(entry.getName());
                    zin.closeEntry();
                }
            }
        }
        catch (IOException ioEx)
        {
            throw new RuntimeException(ioEx);
        }
    }

    /**
     * ����������� ����� �� ������ � ��������� �������.
     * <p/>
     * @param name ��� ����� � ������
     */
    public void loadZipFile(String name)
    {
        try
        {
            try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname)))
            {
                ZipEntry entry;
                fileText.setText("");

                // ����� ��������, ���������������� �����.
                while ((entry = zin.getNextEntry()) != null)
                {
                    if (entry.getName().equals(name))
                    {
                        // ������ ����� � ����������� ��� � ��������� �������.
                        BufferedReader in = new BufferedReader(new InputStreamReader(zin));
                        String line;
                        while ((line = in.readLine()) != null)
                        {
                            fileText.append(line);
                            fileText.append("\n");
                        }
                    }
                    zin.closeEntry();
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}

/**
 * ������ ������ �������� ��� ����� � ��������� ������������.
 */
class ExtensionFileFilter extends FileFilter
{
    private String description = "";
    private ArrayList<String> extensions = new ArrayList<>(0x0A);

    /**
     * ���������� ����������, ������� ������ �������������� ��������.
     * <p/>
     * @param extension ���������� (�������� ".txt" or "txt")
     */
    public void addExtension(String extension)
    {
        if (!extension.startsWith("."))
        {
            extension = "." + extension;
        }
        extensions.add(extension.toLowerCase());
    }

    /**
     * ������ �������� ������, �������������� ��������.
     * <p/>
     * @param aDescription �������� �������������� ������.
     */
    public void setDescription(String aDescription)
    {
        description = aDescription;
    }

    /**
     * ���������� �������� ������, �������������� ������ ��������.
     * <p/>
     * @return �������� �������������� ������
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String name = f.getName().toLowerCase();

        // �������� ���������� �����.
        for (String e : extensions)
        {
            if (name.endsWith(e))
            {
                return true;
            }
        }
        return false;
    }
}