// Демонстрация работы индикатора хода процесса.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProgressDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
    private JProgressBar jprogHoriz;
    private JProgressBar jprogVert;
    private JButton jbtn;

    public ProgressDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Progress Bars");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(280, 270);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание индикаторов хода процесса с вертикальной и горизонтальной ориентацией
        jprogVert = new JProgressBar(JProgressBar.VERTICAL);
        jprogHoriz = new JProgressBar(); // По умолчанию принимается горизонтальная ориентация

        // Отображение строки, представляющей часть выполненной задачи в процентах.
        jprogVert.setStringPainted(true);
        jprogHoriz.setStringPainted(true);

        jbtn = new JButton("Push Me");

        // Метки, отображающие текущие значения индикаторов хода процесса.
        jlabHoriz = new JLabel("Value of horizontal progress bar: " + jprogHoriz.getValue());
        jlabVert = new JLabel("Value of vertical progress bar: " + jprogVert.getValue());

        // Увеличение значения индикатора хода процесса по щелчку на кнопке Push Me.
        // Если значение компонента достигло максимума, никакие действия не предпринимаются.
        jbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int hVal = jprogHoriz.getValue();
                int vVal = jprogVert.getValue();

                if (hVal >= jprogHoriz.getMaximum())
                {
                    return;
                }
                else
                {
                    jprogHoriz.setValue(hVal + 10);
                }

                if (vVal >= jprogHoriz.getMaximum())
                {
                    return;
                }
                else
                {
                    jprogVert.setValue(vVal + 10);
                }

                jlabHoriz.setText("Value of horizontal progress bar: " + jprogHoriz.getValue());
                jlabVert.setText("Value of vertical progress bar: " + jprogVert.getValue());
            }
        });

        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jprogHoriz);
        jfrm.getContentPane().add(jprogVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVert);
        jfrm.getContentPane().add(jbtn);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ProgressDemo();
            }
        });
    }
}