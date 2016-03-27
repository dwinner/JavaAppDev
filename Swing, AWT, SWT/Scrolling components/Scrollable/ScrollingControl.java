// Управление процессом прокрутки с помощью интерфейса Scrollable
import javax.swing.*;
import java.awt.*;

public class ScrollingControl extends JFrame
{
    public ScrollingControl()
    {
        super("Scrolling control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем в центр панель прокрутки с нашим компонентом
        getContentPane().add(new JScrollPane(new GridComponent()));
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }
    
    /**
     * Компонент-сетка с особым режимом прокрутки
     */
    private class GridComponent extends JPanel implements Scrollable
    {
        // Размер ячейки сетки
        private static final int CELL_SIZE = 20;
        // Количество ячеек сетки
        private static final int CELL_COUNT = 50;
        
        // Предпочтительный размер компонента
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(CELL_SIZE * CELL_COUNT, CELL_SIZE * CELL_COUNT);
        }
        
        // Прорисовка компонента
        @Override public void paintComponent(Graphics g)
        {
            // Нужно вызвать метод базового класса
            super.paintComponent(g);
            for (int x = 0; x < CELL_COUNT; x++)
            {
                for (int y = 0; y < CELL_COUNT; y++)
                {
                    // Рисуем ячейку
                    g.setColor(Color.BLACK);
                    g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // Предпочтительный размер области прокрутки
        public Dimension getPreferredScrollableViewportSize()
        {
            return getPreferredSize();
        }

        // Приращение при прокрутке на один элемент
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
        {
            return CELL_SIZE;
        }

        // Приращение при прокрутке блоком
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
        {
            return CELL_SIZE * 10;
        }

        // Нужно ли следить за размером области прокрутки
        public boolean getScrollableTracksViewportWidth()   { return false; }
        public boolean getScrollableTracksViewportHeight()  { return false; }

    }
    
    public static void main(String[] args)
    {
        new ScrollingControl();
    }

}