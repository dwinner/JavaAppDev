// ������������� ������� � ���������
import java.awt.GridLayout;
import javax.swing.*;

public class SimpleTabbedPane extends JFrame
{
    public SimpleTabbedPane()
    {
        super("Simple Tabbed Pane");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ ������ � ���������
        JTabbedPane tabsOne = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
        // ��������� �������
        for (int i = 1; i < 8; i++)
        {
            JPanel tab = new JPanel();
            tab.add(new JButton("Just a button " + i));
            tabsOne.addTab("Tab: " + i, tab);
        }
        // ������ ������ � ���������
        JTabbedPane tabsTwo = new JTabbedPane(JTabbedPane.TOP);
        // ��������� �������
        for (int i = 1; i < 8; i++)
        {
            JPanel tab = new JPanel();
            tab.add(new JButton("Button again " + i));
            tabsTwo.addTab("<html><i>Tab " + i + "</i>", new ImageIcon("16-on-black.gif"), tab, "Push!");
        }
        // ��������� ������� � ������ �����������
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(tabsOne);
        getContentPane().add(tabsTwo);
        setSize(600, 250);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleTabbedPane();
    }
}