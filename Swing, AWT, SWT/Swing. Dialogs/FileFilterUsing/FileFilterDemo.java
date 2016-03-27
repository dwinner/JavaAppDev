// Использование фильтра файлов.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

// Создание фильтра файлов, обеспечивающего отображение исходных файлов Java и каталогов. 
class JavaFileFilter extends FileFilter
{
    @Override public boolean accept(File file)
    {
        // Метод возвращает значение true, если в качестве параметра ему был
        // передан исходный файл Java или каталог.
        return (file.getName().endsWith(".java") || file.isDirectory())
            ? true
            : false;
    }

    @Override public String getDescription() { return "Java Source Code Files"; }
}

public class FileFilterDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFileChooser jfc;

    public FileFilterDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("File Filter Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей имя выбранного файла.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show File Chooser");

        // Создание окна выбора файлов.
        jfc = new JFileChooser();

        // Установка фильтра файлов.
        jfc.setFileFilter(new JavaFileFilter());

        // Отображение окна выбора файла при активизации кнопки Show File Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // В качестве параметра, определяющего владельца окна, задается значение null.
                // В результате диалоговое окно выводится по центру экрана.
                int result = jfc.showOpenDialog(null);
                jlab.setText((result == JFileChooser.APPROVE_OPTION)
                    ? "Selected file is: " + jfc.getSelectedFile().getName()
                    : "No file selected."
                );
            }
        });

        // Включение кнопки Show File Chooser и метки в состав панели содержимого.
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new FileFilterDemo();
            }
        });
    }
}