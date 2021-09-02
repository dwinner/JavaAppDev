/*
 * ������ ������������� ���������� JTabbedPane.
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPaneDemo
{
    public TabbedPaneDemo()
    {
        // �������� ������ ���������� JFrame. ��� ���� �����������
        // ��������� ���������� �� ���������, �.�. BorderLayout.
        final JFrame jfrm = new JFrame("Tabbed Pane Demo");

        // ��������� ��������� �������� ������.
        jfrm.setSize(380, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������ � ���������.
        JTabbedPane jtp = new JTabbedPane();

        // ���������� ������� � ������.
        jtp.addTab("File Manager", new JLabel(" This is the File Manager tab."));
        jtp.addTab("Performance", new JLabel(" This is the Performance tab."));
        jtp.addTab("Reports", new JLabel(" This is the Reports tab."));
        jtp.addTab("Customize", new JLabel(" This is the Customize tab."));

        /*
         * � �������� ������ ������� ��������� ��������� ������� ��������� ��������� ��� ������ �������: ��������
         * ��������� JFrame � ������������ � ��������� ��������
         */
        jtp.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                JTabbedPane jtpFrom = (JTabbedPane) ce.getSource();
                String selTitle = jtpFrom.getTitleAt(jtpFrom.getSelectedIndex());
                jfrm.setTitle(selTitle);
            }
        });

        // ��������� ������ � ��������� � ������ ������ �����������.
        jfrm.getContentPane().add(jtp);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TabbedPaneDemo();
            }
        });
    }
}