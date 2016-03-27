
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
import javax.swing.Timer;

/**
 * Данный класс расширяет JPanel.
 * Он переопределяет метод paintComponent() так,
 * что случайные данные рисуются в панели.
 */
class PaintPanel extends JPanel
{
    private Insets ins; // Хранит отступы для панели
    private Random rand; // Используется для генерации случайных значений

    PaintPanel(int w, int h)
    {
        // Делаем панель непрозрачной.
        setOpaque(true);
        // Создаем линейную рамку красного цвета.
        setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        // Установка предпочтительных размеров.
        setPreferredSize(new Dimension(w, h));
        rand = new Random();
    }

    /**
     * Переопределение метода paintComponent(g)
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        // Сперва вызовем метод суперкласса.
        super.paintComponent(g);
        // Получить высоту и ширину компонента.
        int height = getHeight();
        int width = getHeight();
        // Получить отступы.
        ins = getInsets();
        // Заполним панель рисуя случайные данные в форме некой диаграммы.
        for (int i = ins.left + 5; i <= width - ins.right - 5; i += 4)
        {
            // Генерируем случайное число между 0 и максимальной высотой области
            int h = rand.nextInt(height - ins.bottom);
            if (h <= ins.top)
            {
                h = ins.top + 1;
            }
            // Рисуем линию.
            g.drawLine(i, height - ins.bottom, i, h);
        }
    }

    // Смена размера рамки и ее цвета.
    public void changeBorder(Color color, int size)
    {
        setBorder(BorderFactory.createLineBorder(color, size));
    }

}

public class PaintDemo
{
    private JButton jbtnMore;
    private JButton jbtnSize;
    private JLabel jlab;
    private PaintPanel pp;
    private int bsize = 1; // Текущий размер рамки
    private int cidx = 0; // Текущий индекс цвета.
    private Color colors[] =
    {
        Color.RED, Color.BLUE, Color.GREEN
    };
    private boolean big;
    private Timer borderSizeT;  // Таймер для изменения размера рамки
    private Timer borderColorT; // Таймер для изменения цвета рамки

    public PaintDemo()
    {
        JFrame jfrm = new JFrame("Painting Demo");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(240, 260);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pp = new PaintPanel(100, 100);

        jbtnMore = new JButton("Show More Data");
        jbtnSize = new JButton("Change Border Size");

        jlab = new JLabel("Bar Graph of Random Data");

        // Change the border color. 
        ActionListener borderColor = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                pp.changeBorder(colors[cidx], bsize);
                cidx ++;
                if (cidx == 3)
                {
                    cidx = 0;
                }
            }

        };

        // Создать и запутить таймеры.

        borderSizeT = new Timer(200, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pp.changeBorder(colors[cidx], bsize);
                if ( ++ bsize > 5)
                {
                    bsize = 1;
                }
            }

        });

        borderColorT = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pp.changeBorder(colors[cidx], bsize);
                if ( ++ cidx == 3)
                {
                    cidx = 0;
                }
            }

        });

        borderSizeT.start();
        borderColorT.start();

        // Перерисовка панели по нажатию кнопки. 
        jbtnMore.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                pp.repaint();
            }

        });

        jbtnSize.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                bsize = ( ! big) ? 5 : 1;
                pp.changeBorder(colors[cidx], bsize);
                big =  ! big;
            }

        });

        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(pp);
        jfrm.getContentPane().add(jbtnMore);
        jfrm.getContentPane().add(jbtnSize);

        big = false;

        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
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