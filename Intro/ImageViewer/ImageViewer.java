
import java.awt.EventQueue;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Программа для просмотра изображений.
 * @version 1.22 2007-05-21
 * @author Cay Horstmann
 */
public class ImageViewer
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с текстовой меткой для вывода изображения.
 */
class ImageViewerFrame extends JFrame
{
    private JLabel label;
    private JFileChooser chooser;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    public ImageViewerFrame()
    {
        setTitle("ImageViewer");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Для вывода изображений используется метка.
        label = new JLabel();
        add(label);

        // Диалоговое окно для выбора файлов.
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        // Создание строки меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                // Отображение диалогового окна для выбора файла.
                int result = chooser.showOpenDialog(null);

                // Если файл выбран, он задается в качестве пиктограммы для метки.
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    String name = chooser.getSelectedFile().getPath();
                    label.setIcon(new ImageIcon(name));
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
    }
}
