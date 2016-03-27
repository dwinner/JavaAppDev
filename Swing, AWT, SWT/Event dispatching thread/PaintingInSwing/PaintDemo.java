// Рисование графика.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Данный класс является подклассом JPanel. В нем переопределен метод
// paintComponent(), с помощью которого в составе панели отображаются
// данные, сгенерированные случайным образом.
class PaintPanel extends JPanel
{
    private Insets ins; // Размеры обрамления панели.
    private Random rand; // Используется для генерации псевдослучайных значений.

    PaintPanel(int w, int h)
    {
        // Панель должна быть непрозрачной.
        setOpaque(true);

        // Использование рамки в виде линии красного цвета.
        setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        // Установка предпочтительных размеров.
        setPreferredSize(new Dimension(w, h));

        rand = new Random();
    }

    // Переопределение метода paintComponent() для отображения данных
    // на поверхности компонента.
    @Override
    protected void paintComponent(Graphics g)
    {
        // Тело метода начинается с вызова метода суперкласса.
        super.paintComponent(g);

        // Определение высоты и ширины компонента.
        int height = getHeight();
        int width = getWidth();

        // Получение размеров обрамления.
        ins = getInsets();

        // В составе панели выводятся псевдослучайные значения,
        // представленные в виде гистограммы.
        for (int i = ins.left + 5; i <= width - ins.right - 5; i += 4)
        {
            // Получение псевдослучайного значения в интервале от 0 до
            // максимальной высоты области отображения.
            int h = rand.nextInt(height - ins.bottom);

            // Коррекция значения, располагающегося слишком близко к рамке.
            if (h <= ins.top)
            {
                h = ins.top + 1;
            }

            // Вывод линии, представляющей значение.
            g.drawLine(i, height - ins.bottom, i, h);
        }
    }

    // Изменение размера рамки.
    public void changeBorderSize(int size)
    {
        setBorder(BorderFactory.createLineBorder(Color.RED, size));
    }

}

// Демонстрация рисования на поверхности панели.
public class PaintDemo
{
    private JButton jbtnMore;
    private JButton jbtnSize;
    private JLabel jlab;
    private PaintPanel pp;
    private boolean big; // Используется для изменения размеров панели.

    public PaintDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Painting Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 260);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание панели для вывода данных.
        pp = new PaintPanel(100, 100);

        // Создание кнопок.
        jbtnMore = new JButton("Show More Data");
        jbtnSize = new JButton("Change Border Size");

        // Описание графика.
        jlab = new JLabel("Bar Graph of Random Data");

        // Перерисовка панели по щелчку на кнопке Show More Data.
        jbtnMore.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Запрос на перерисовку панели.
                pp.repaint();
            }

        });

        // Установка размеров обрамления по щелчку на кнопке Change Border Size.
        // Изменение размеров обрамления автоматически приводит к перерисовке компонента.
        jbtnSize.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if ( ! big)
                {
                    pp.changeBorderSize(5);
                }
                else
                {
                    pp.changeBorderSize(1);
                }
                big =  ! big;
            }

        });

        // Включение кнопок, метки и панели в состав панели содержимого.
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(pp);
        jfrm.getContentPane().add(jbtnMore);
        jfrm.getContentPane().add(jbtnSize);

        big = false;

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
                new PaintDemo();
            }

        });
    }

}