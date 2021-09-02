/*
 * ������ � ��������� ��������� � ��������� �������.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabSelection extends JFrame
{
    public TabSelection()
    {
        super("Tab Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ���� ������ � ���������
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("�������", new JPanel());
        tabs.addTab("��� �������", new JPanel());
        // ��������� ��������� �������
        tabs.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                // ��������� �� ������� ����� ���������
                JPanel panel = (JPanel) ((JTabbedPane) e.getSource()).getSelectedComponent();
                panel.add(new JButton("RunTime Button " + Math.random()));
            }
        });
        // ������ � ��������������� ���������
        tabs.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                // ������, �� ����� ������� ��� ������
                int idx = ((JTabbedPane) me.getSource()).indexAtLocation(me.getX(), me.getY());
                System.out.println("Index: " + idx);
            }
        });
        // ������� ���� �� �����
        getContentPane().add(tabs);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TabSelection();
    }
}