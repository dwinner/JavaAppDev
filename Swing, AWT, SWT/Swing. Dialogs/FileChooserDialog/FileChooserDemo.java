// Пример, демонстрирующий использование класса JFileChooser.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class FileChooserDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFileChooser jfc;

    public FileChooserDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JFileChooser Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбранный файл.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show File Chooser");

        // Создание выбора файла, первоначально отображающего
        // содержимое каталога по умолчанию.
        jfc = new JFileChooser();

        // Отображение окна выбора файла в ответ на активизацию кнопки Show File Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Отображение окна выбора файла. В качестве параметра, определяющего
                // владельца окна, задается значение null. В результате диалоговое окно
                // выводится по центру экрана.
                int result = jfc.showOpenDialog(null);
                // Если файл был выбран, отображается его имя.
                jlab.setText((result == JFileChooser.APPROVE_OPTION)
                   ? "Selected file is: " + jfc.getSelectedFile().getName()
                   : "No file selected");
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
                new FileChooserDemo();
            }

        });
    }

}