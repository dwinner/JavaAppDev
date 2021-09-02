// ���������� ��������� ��������� � ������� ���������� Scrollable
import javax.swing.*;
import java.awt.*;

public class ScrollingControl extends JFrame
{
    public ScrollingControl()
    {
        super("Scrolling control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��������� � ����� ������ ��������� � ����� �����������
        getContentPane().add(new JScrollPane(new GridComponent()));
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }
    
    /**
     * ���������-����� � ������ ������� ���������
     */
    private class GridComponent extends JPanel implements Scrollable
    {
        // ������ ������ �����
        private static final int CELL_SIZE = 20;
        // ���������� ����� �����
        private static final int CELL_COUNT = 50;
        
        // ���������������� ������ ����������
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(CELL_SIZE * CELL_COUNT, CELL_SIZE * CELL_COUNT);
        }
        
        // ���������� ����������
        @Override public void paintComponent(Graphics g)
        {
            // ����� ������� ����� �������� ������
            super.paintComponent(g);
            for (int x = 0; x < CELL_COUNT; x++)
            {
                for (int y = 0; y < CELL_COUNT; y++)
                {
                    // ������ ������
                    g.setColor(Color.BLACK);
                    g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // ���������������� ������ ������� ���������
        public Dimension getPreferredScrollableViewportSize()
        {
            return getPreferredSize();
        }

        // ���������� ��� ��������� �� ���� �������
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
        {
            return CELL_SIZE;
        }

        // ���������� ��� ��������� ������
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
        {
            return CELL_SIZE * 10;
        }

        // ����� �� ������� �� �������� ������� ���������
        public boolean getScrollableTracksViewportWidth()   { return false; }
        public boolean getScrollableTracksViewportHeight()  { return false; }

    }
    
    public static void main(String[] args)
    {
        new ScrollingControl();
    }

}