// Программа сравнения файлов.
// В данном проекте используются классы JOptionPane и JFileChooser.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CompareFiles
{
    private JLabel jlabFirst;
    private JLabel jlabSecond;
    private JButton jbtnGetFirst;
    private JButton jbtnGetSecond;
    private JButton jbtnCompare;
    private JTextField jtfFirst;
    private JTextField jtfSecond;
    private JFileChooser jfc;

    public CompareFiles()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Compare Files");
        jfrm.setResizable(false);
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 200);
        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание меток для полей редактирования.
        jlabFirst = new JLabel("First file");
        jlabFirst.setPreferredSize(new Dimension(70, 20));
        jlabFirst.setHorizontalAlignment(SwingConstants.RIGHT);

        jlabSecond = new JLabel("Second file:");
        jlabSecond.setPreferredSize(new Dimension(70, 20));
        jlabSecond.setHorizontalAlignment(SwingConstants.RIGHT);

        // Создание полей редактирования для ввода имен файлов.
        jtfFirst = new JTextField(20);
        jtfSecond = new JTextField(20);

        // Создание кнопок Browse.
        jbtnGetFirst = new JButton("Browse");
        jbtnGetSecond = new JButton("Browse");

        // Создание кнопки, запускающей процесс сравнения файлов.
        jbtnCompare = new JButton("Compare Files");

        // Создание окна выбора файла, отображаемого по щелчку на кнопке Browse.
        jfc = new JFileChooser();

        // Выбор первого файла.
        jbtnGetFirst.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                int result = jfc.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfc.getSelectedFile();
                    jtfFirst.setText(f.getPath());
                }
            }

        });

        // Выбор второго файла.
        jbtnGetSecond.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                int result = jfc.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfc.getSelectedFile();
                    jtfSecond.setText(f.getPath());
                }
            }

        });

        // Сравнение файлов.
        jbtnCompare.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Проверить, введены ли имена файлов.
                if (jtfFirst.getText().length() == 0 || jtfSecond.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "Please specify the files to compare.",
                       "Filename Not Specified",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Создание объектов File на основе имен файлов.
                File f1 = new File(jtfFirst.getText());
                File f2 = new File(jtfSecond.getText());

                // Проверить, существуют ли указанные файлы.
                if ( ! f1.exists())
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "The first file does not exists.",
                       "File Not Found",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ( ! f2.exists())
                {
                    JOptionPane.showMessageDialog(null,
                       "The second file does not exists.",
                       "File Not Found",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Сравнение файлов.
                if (compare(f1, f2))
                {
                    JOptionPane.showMessageDialog(null,
                       "Files compare equal",
                       "Comparison Result",
                       JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "Files differ.",
                       "Comparison Result",
                       JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jlabFirst);
        jfrm.getContentPane().add(jtfFirst);
        jfrm.getContentPane().add(jbtnGetFirst);
        jfrm.getContentPane().add(jlabSecond);
        jfrm.getContentPane().add(jtfSecond);
        jfrm.getContentPane().add(jbtnGetSecond);
        jfrm.getContentPane().add(jbtnCompare);
        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    /**
     * Метод сравнения двух файлов.
     * <p/>
     * @param fileA Первый файл
     * @param fileB Второй файл
     * <p/>
     * @return true если файлы имеют идентичное содержимое
     */
    private boolean compare(File fileA, File fileB)
    {
        // Если файлы имеют разную длину, они не совпадают.
        if (fileA.length() != fileB.length())
        {
            return false;
        }
        FileInputStream f1, f2;
        int i, j;
        byte buf1[] = new byte[1024];
        byte buf2[] = new byte[1024];

        try
        {
            f1 = new FileInputStream(fileA);
            f2 = new FileInputStream(fileB);

            do
            {
                // Данная версия метода read() возвращает число прочитанных байтов или
                // значение -1, если был достигнут конец файла.
                i = f1.read(buf1, 0, 1024);
                j = f2.read(buf2, 0, 1024);

                // Если содержимое буферов не совпадает, файлы различаются. В этом случае
                // файлы закрываются и возвращается значение false.
                if ( ! Arrays.equals(buf1, buf2))
                {
                    f1.close();
                    f2.close();
                    return false;
                }
            }
            while (i != -1 && j != -1);

            f1.close();
            f2.close();
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null,
               exc,
               "File Error!",
               JOptionPane.WARNING_MESSAGE);
            // В случае ошибки возвращается значение false.
            return false;
        }

        // Файлы совпадают. Возвращается значение true.
        return true;
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new CompareFiles();
            }

        });
    }

}