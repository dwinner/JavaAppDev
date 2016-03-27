// Простой менеджер расположения, располагает компоненты в вертикальный ряд
import java.awt.*;
import javax.swing.*;

public class VerticalLayout implements LayoutManager
{
    private Dimension size = new Dimension();

    /**
     * Сигнал расположить компоненты в контейнеры
     * @param parent родительский контейнер
     */
    public void layoutContainer(Container parent)
    {
        Component[] comps = parent.getComponents();
        int currentY = 5;
        for (int i = 0; i < comps.length; i++)
        {
            // Предпочтительный размер компонента
            Dimension pref = comps[i].getPreferredSize();
            // Указываем расположение компонента на экране
            comps[i].setBounds(5, currentY, pref.width, pref.height);
            // Увеличиваем промежуток на 5 пикселей + высота текущего компонента
            currentY += 5 + pref.height;
        }
    }

    public void addLayoutComponent(String name, Component comp) { }
    public void removeLayoutComponent(Component comp) { }

    /**
     * Минимальный размер для контейнера
     * @param parent родительский контейнер
     * @return
     */
    public Dimension minimumLayoutSize(Container parent)
    {
        return calculateBestSize(parent);
    }

    // Предпочтительный размер для контейнера
    public Dimension preferredLayoutSize(Container parent)
    {
        return calculateBestSize(parent);
    }

    // Вычисление оптимального размера контейнера
    private Dimension calculateBestSize(Container c)
    {
        // Сначала вычислим длину контейнера
        Component[] comps = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < comps.length; i++)
        {
            int width = comps[i].getWidth();
            // Поиск компонента с максимальной длиной
            if (width > maxWidth)
            {
                maxWidth = width;
            }
        }
        // Длина контейнера с учетом левого отступа
        size.width = maxWidth + 5;
        // Вычисляем высоту контейнера
        int height = 0;
        for (int i = 0; i < comps.length; i++)
        {
            height += 5;
            height += comps[i].getHeight();
        }
        size.height = height;

        return size;
    }

    // Проверяем работу нового менеджера
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Vertical Layout");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Панель будет использовать новое расположение
        JPanel contents = new JPanel(new VerticalLayout());
        // Добавляем пару кнопок и текстовое поле
        contents.add(new JButton("One"));
        contents.add(new JButton("Two"));
        contents.add(new JTextField(10));
        frame.setContentPane(contents);
        frame.setVisible(true);
    }
}