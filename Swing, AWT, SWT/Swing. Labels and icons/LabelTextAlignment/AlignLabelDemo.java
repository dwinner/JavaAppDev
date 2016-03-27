// Программа, демонстрирующая выравнивание текста по горизонтали и по вертикали.

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class AlignLabelDemo
{
    public AlignLabelDemo()
    {
        JLabel[] jlabs = new JLabel[9];

        // Создание нового компонента JFrame.
        JFrame jfrm = new JFrame("Horizontal and Vertical Alignment");

        // Установка диспетчера компоновки GridLayout.
        // Создается таблица из 3-х строк и 3-х столбцов
        // с зазором 4 пикселя между компонентами.
        jfrm.getContentPane().setLayout(new GridLayout(3, 3, 4, 4));

        // Определение исходного размера фрейма.
        jfrm.setSize(500, 200);

        // Завершение программы при закрытии пользователем окна.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Горизонтальное выравнивание по левой границе и
        // вертикальное выравнивание по верхней границе.
        jlabs[0] = new JLabel("Left, Top", SwingConstants.LEFT);
        jlabs[0].setVerticalAlignment(SwingConstants.TOP);

        // Горизонтальное выравнивание по центру и
        // вертикальное выравнивание по верхней границе.
        jlabs[1] = new JLabel("Center, Top", SwingConstants.CENTER);
        jlabs[1].setVerticalAlignment(SwingConstants.TOP);

        // Горизонтальное выравнивание по правой границе и
        // вертикальное выравнивание по верхней границе.
        jlabs[2] = new JLabel("Right, Top", SwingConstants.RIGHT);
        jlabs[2].setVerticalAlignment(SwingConstants.TOP);

        // Горизонтальное выранивание по левой границе и вертикальное выранивание по центру.
        // Такое выравнивание применяется по умолчанию для большинства языков.
        jlabs[3] = new JLabel("Left, Center", SwingConstants.LEFT);

        // Горизонтальное и вертикальное выравнивание по центру.
        jlabs[4] = new JLabel("Center, Center", SwingConstants.CENTER);

        // Горизонтальное выравнивание по правой границе и
        // вертикальное выранивание по центру.
        jlabs[5] = new JLabel("Right, Center", SwingConstants.RIGHT);

        // Горизонтальное выранивание по левой границе и
        // вертикальное выранивание по нижней границе.
        jlabs[6] = new JLabel("Left, Bottom", SwingConstants.LEFT);
        jlabs[6].setVerticalAlignment(SwingConstants.BOTTOM);

        // Горизонтальное выравнивание по центру и
        // вертикальное выравнивание по нижней границе.
        jlabs[7] = new JLabel("Center, Bottom", SwingConstants.CENTER);
        jlabs[7].setVerticalAlignment(SwingConstants.BOTTOM);

        // Горизонтальное выравнивание по правой границе и
        // вертикальное выравнивание по нижней границе.
        jlabs[8] = new JLabel("Right, Bottom", SwingConstants.RIGHT);
        jlabs[8].setVerticalAlignment(SwingConstants.BOTTOM);

        // Добавление рамок для отображения вокруг меток. Создание рельефной рамки.
        Border border = BorderFactory.createEtchedBorder();

        // Добавление рамки к каждой метке.
        for (int i = 0; i < 9; i ++)
        {
            jlabs[i].setBorder(border);
        }

        // Добавление меток к панели содержимого.
        for (int i = 0; i < 9; i ++)
        {
            jfrm.getContentPane().add(jlabs[i]);
        }

        // Добавление пустой рамки к панели содержимого.
        JPanel cp = (JPanel) jfrm.getContentPane();
        // Пустая рамка создает зазор между краем фрейма
        // и содержащимися в нем элементами.
        cp.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

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
                new AlignLabelDemo();
            }

        });
    }

}