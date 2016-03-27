import java.awt.EventQueue;
import java.awt.event.*;
import java.io.*;
import java.util.prefs.*;
import javax.swing.*;

/**
 * Программа для тестирования параметров предпочтения.
 * Программа запоминает положение окна, размер и название.
 * @version 1.02 2007-06-12
 * @author Cay Horstmann
 */
public class PreferencesTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                PreferencesFrame frame = new PreferencesFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, который восстанавливает позиции и размер от предпочтений
 * пользователя и обновляет предпочтения при выходе.
 */
class PreferencesFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
   
    public PreferencesFrame()
    {
        // Извлечь позицию, размер и заголовок из файла предпочтений

        Preferences root = Preferences.userRoot();
        final Preferences node = root.node("/com/horstmann/corejava");
        int left = node.getInt("left", 0);
        int top = node.getInt("top", 0);
        int width = node.getInt("width", DEFAULT_WIDTH);
        int height = node.getInt("height", DEFAULT_HEIGHT);
        setBounds(left, top, width, height);

        // Если заголовок не задан, просим пользователя задать его

        String title = node.get("title", "");
        if (title.equals(""))
            title = JOptionPane.showInputDialog("Please supply a frame title:");
        if (title == null)
            title = "";
        setTitle(title);

        // Установка окна выбора файла, который отображает XML-файлы

        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        // Создать фильтр для файлов XML
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            public boolean accept(File f)
            {
                return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
            }

            public String getDescription()
            {
                return "XML files";
            }
        });

        // Установка меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem exportItem = new JMenuItem("Export preferences");
        menu.add(exportItem);
        exportItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (chooser.showSaveDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        OutputStream out = new FileOutputStream(chooser.getSelectedFile());
                        node.exportSubtree(out);
                        out.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        JMenuItem importItem = new JMenuItem("Import preferences");
        menu.add(importItem);
        importItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if (chooser.showOpenDialog(PreferencesFrame.this) == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        InputStream in = new FileInputStream(chooser.getSelectedFile());
                        Preferences.importPreferences(in);
                        in.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                node.putInt("left", getX());
                node.putInt("top", getY());
                node.putInt("width", getWidth());
                node.putInt("height", getHeight());
                node.put("title", getTitle());
                System.exit(0);
            }
        });
    }

}
