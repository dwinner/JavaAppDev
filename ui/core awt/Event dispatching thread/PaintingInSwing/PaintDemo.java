// ��������� �������.

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

// ������ ����� �������� ���������� JPanel. � ��� ������������� �����
// paintComponent(), � ������� �������� � ������� ������ ������������
// ������, ��������������� ��������� �������.
class PaintPanel extends JPanel
{
    private Insets ins; // ������� ���������� ������.
    private Random rand; // ������������ ��� ��������� ��������������� ��������.

    PaintPanel(int w, int h)
    {
        // ������ ������ ���� ������������.
        setOpaque(true);

        // ������������� ����� � ���� ����� �������� �����.
        setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        // ��������� ���������������� ��������.
        setPreferredSize(new Dimension(w, h));

        rand = new Random();
    }

    // ��������������� ������ paintComponent() ��� ����������� ������
    // �� ����������� ����������.
    @Override
    protected void paintComponent(Graphics g)
    {
        // ���� ������ ���������� � ������ ������ �����������.
        super.paintComponent(g);

        // ����������� ������ � ������ ����������.
        int height = getHeight();
        int width = getWidth();

        // ��������� �������� ����������.
        ins = getInsets();

        // � ������� ������ ��������� ��������������� ��������,
        // �������������� � ���� �����������.
        for (int i = ins.left + 5; i <= width - ins.right - 5; i += 4)
        {
            // ��������� ���������������� �������� � ��������� �� 0 ��
            // ������������ ������ ������� �����������.
            int h = rand.nextInt(height - ins.bottom);

            // ��������� ��������, ���������������� ������� ������ � �����.
            if (h <= ins.top)
            {
                h = ins.top + 1;
            }

            // ����� �����, �������������� ��������.
            g.drawLine(i, height - ins.bottom, i, h);
        }
    }

    // ��������� ������� �����.
    public void changeBorderSize(int size)
    {
        setBorder(BorderFactory.createLineBorder(Color.RED, size));
    }

}

// ������������ ��������� �� ����������� ������.
public class PaintDemo
{
    private JButton jbtnMore;
    private JButton jbtnSize;
    private JLabel jlab;
    private PaintPanel pp;
    private boolean big; // ������������ ��� ��������� �������� ������.

    public PaintDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Painting Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 260);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������ ��� ������ ������.
        pp = new PaintPanel(100, 100);

        // �������� ������.
        jbtnMore = new JButton("Show More Data");
        jbtnSize = new JButton("Change Border Size");

        // �������� �������.
        jlab = new JLabel("Bar Graph of Random Data");

        // ����������� ������ �� ������ �� ������ Show More Data.
        jbtnMore.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ������ �� ����������� ������.
                pp.repaint();
            }

        });

        // ��������� �������� ���������� �� ������ �� ������ Change Border Size.
        // ��������� �������� ���������� ������������� �������� � ����������� ����������.
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

        // ��������� ������, ����� � ������ � ������ ������ �����������.
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(pp);
        jfrm.getContentPane().add(jbtnMore);
        jfrm.getContentPane().add(jbtnSize);

        big = false;

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
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