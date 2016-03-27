// Заголовки и уголки панели прокрутки JScrollPane
import javax.swing.*;
import java.awt.*;

public class HeadersAndCorners extends JFrame
{
    // Надпись с большим изображением
    private JLabel label = new JLabel(new ImageIcon("andLinux_logo.jpg"));

    public HeadersAndCorners()
    {
        super("Headers And Corners");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем панель прокрутки
        JScrollPane scroll = new JScrollPane(label);
        
        // Устанавливаем заголовки
        scroll.setColumnHeaderView(new XHeader());
        scroll.setRowHeaderView(new YHeader());
        
        // Устанавливаем левый верхний уголок
        scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JButton(new ImageIcon("print.gif")));
        
        // Выводим окно на экран
        getContentPane().add(scroll);
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * Заголовок по оси X
     */
    private class XHeader extends JPanel
    {
        // Размер заголовка
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(label.getPreferredSize().width, 20);
        }

        // Прорисовываем линейку
        @Override public void paintComponent(Graphics g)
        {
            int width = getWidth();
            for (int i = 0; i < width; i += 50)
            {
                g.drawString("" + i, i, 16);
            }
        }
    }

    /**
     * Заголовок по оси Y
     */
    private class YHeader extends JPanel
    {
        // Размер заголовка
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(20, label.getPreferredSize().height);
        }

        // Прорисовываем линейку
        @Override public void paintComponent(Graphics g)
        {
            int height = getHeight();
            for (int i = 0; i < height; i += 50)
            {
                g.drawString("" + i, 0, i);
            }
        }
    }

    public static void main(String[] args)
    {
        new HeadersAndCorners();
    }
}