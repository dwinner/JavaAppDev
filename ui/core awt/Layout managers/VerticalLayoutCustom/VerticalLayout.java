// ������� �������� ������������, ����������� ���������� � ������������ ���
import java.awt.*;
import javax.swing.*;

public class VerticalLayout implements LayoutManager
{
    private Dimension size = new Dimension();

    /**
     * ������ ����������� ���������� � ����������
     * @param parent ������������ ���������
     */
    public void layoutContainer(Container parent)
    {
        Component[] comps = parent.getComponents();
        int currentY = 5;
        for (int i = 0; i < comps.length; i++)
        {
            // ���������������� ������ ����������
            Dimension pref = comps[i].getPreferredSize();
            // ��������� ������������ ���������� �� ������
            comps[i].setBounds(5, currentY, pref.width, pref.height);
            // ����������� ���������� �� 5 �������� + ������ �������� ����������
            currentY += 5 + pref.height;
        }
    }

    public void addLayoutComponent(String name, Component comp) { }
    public void removeLayoutComponent(Component comp) { }

    /**
     * ����������� ������ ��� ����������
     * @param parent ������������ ���������
     * @return
     */
    public Dimension minimumLayoutSize(Container parent)
    {
        return calculateBestSize(parent);
    }

    // ���������������� ������ ��� ����������
    public Dimension preferredLayoutSize(Container parent)
    {
        return calculateBestSize(parent);
    }

    // ���������� ������������ ������� ����������
    private Dimension calculateBestSize(Container c)
    {
        // ������� �������� ����� ����������
        Component[] comps = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < comps.length; i++)
        {
            int width = comps[i].getWidth();
            // ����� ���������� � ������������ ������
            if (width > maxWidth)
            {
                maxWidth = width;
            }
        }
        // ����� ���������� � ������ ������ �������
        size.width = maxWidth + 5;
        // ��������� ������ ����������
        int height = 0;
        for (int i = 0; i < comps.length; i++)
        {
            height += 5;
            height += comps[i].getHeight();
        }
        size.height = height;

        return size;
    }

    // ��������� ������ ������ ���������
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Vertical Layout");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ ����� ������������ ����� ������������
        JPanel contents = new JPanel(new VerticalLayout());
        // ��������� ���� ������ � ��������� ����
        contents.add(new JButton("One"));
        contents.add(new JButton("Two"));
        contents.add(new JTextField(10));
        frame.setContentPane(contents);
        frame.setVisible(true);
    }
}