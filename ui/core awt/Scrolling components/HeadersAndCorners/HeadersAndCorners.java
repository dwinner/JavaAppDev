// ��������� � ������ ������ ��������� JScrollPane
import javax.swing.*;
import java.awt.*;

public class HeadersAndCorners extends JFrame
{
    // ������� � ������� ������������
    private JLabel label = new JLabel(new ImageIcon("andLinux_logo.jpg"));

    public HeadersAndCorners()
    {
        super("Headers And Corners");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������ ���������
        JScrollPane scroll = new JScrollPane(label);
        
        // ������������� ���������
        scroll.setColumnHeaderView(new XHeader());
        scroll.setRowHeaderView(new YHeader());
        
        // ������������� ����� ������� ������
        scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JButton(new ImageIcon("print.gif")));
        
        // ������� ���� �� �����
        getContentPane().add(scroll);
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * ��������� �� ��� X
     */
    private class XHeader extends JPanel
    {
        // ������ ���������
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(label.getPreferredSize().width, 20);
        }

        // ������������� �������
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
     * ��������� �� ��� Y
     */
    private class YHeader extends JPanel
    {
        // ������ ���������
        @Override public Dimension getPreferredSize()
        {
            return new Dimension(20, label.getPreferredSize().height);
        }

        // ������������� �������
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