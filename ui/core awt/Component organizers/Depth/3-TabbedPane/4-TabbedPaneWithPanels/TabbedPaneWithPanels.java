/*
 * ������������� JTabbedPane ��� ����������� JPanel.
 */

import java.awt.GridLayout;
import javax.swing.*;

public class TabbedPaneWithPanels
{
    private JCheckBox jcbDVD;
    private JCheckBox jcbScanner;
    private JCheckBox jcbNtwrkRdy;
    private JCheckBox jcbWordProc;
    private JCheckBox jcbCompiler;
    private JCheckBox jcbDatabase;
    private JRadioButton jrbTower;
    private JRadioButton jrbNotebook;
    private JRadioButton jrbHandheld;

    public TabbedPaneWithPanels()
    {
        // �������� ������ ���������� JFrame. � �������� ���������� ����������
        // ����������� BorderLayout, ��������� �� ��������� � �������
        // ����������� ������� ����������.
        JFrame jfrm = new JFrame("Use JPanels with JTabbedPane");

        // ��������� ��������� �������� ������.
        jfrm.setSize(280, 140);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������ ������������� ����� ��� ������� Style.
        jrbTower = new JRadioButton("Tower");
        jrbNotebook = new JRadioButton("Notebook");
        jrbHandheld = new JRadioButton("Handheld");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbTower);
        bg.add(jrbNotebook);
        bg.add(jrbHandheld);

        // �������� ������� JPanel ��� ���������� ������ ������������� �����.
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(3, 1));
        jpnl.setOpaque(true);

        // ��������� ������ ������������� ����� � ������ ������.
        jpnl.add(jrbTower);
        jpnl.add(jrbNotebook);
        jpnl.add(jrbHandheld);

        // �������� ������� ����� ��� ������� Options.
        jcbDVD = new JCheckBox("DVD Burner");
        jcbScanner = new JCheckBox("Scanner");
        jcbNtwrkRdy = new JCheckBox("Network Ready");

        // �������� ������� JPanel ��� �������� ������� �����.
        JPanel jpnl2 = new JPanel();
        jpnl2.setLayout(new GridLayout(3, 1));
        jpnl2.setOpaque(true);

        // ��������� ������� ����� � ��������� JPanel.
        jpnl2.add(jcbDVD);
        jpnl2.add(jcbScanner);
        jpnl2.add(jcbNtwrkRdy);

        // �������� ������� ����� ��� ������� Software.
        jcbWordProc = new JCheckBox("Word Processing");
        jcbCompiler = new JCheckBox("Program Development");
        jcbDatabase = new JCheckBox("Database");

        // �������� ������� JPanel ��� �������� ������� �����.
        JPanel jpnl3 = new JPanel();
        jpnl3.setLayout(new GridLayout(3, 1));
        jpnl3.setOpaque(true);

        // ��������� ������� ����� � ��������� JPanel.
        jpnl3.add(jcbWordProc);
        jpnl3.add(jcbCompiler);
        jpnl3.add(jcbDatabase);

        // ����������� ������� � ��������� �� ������������.

        // �������� ������ � ���������. ���� ������ �������
        // �� ���������� � ����� ������, ������������ �� ���������.
        JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        // ��������� ����������� JPanel � ������ ������ � ���������.
        jtp.addTab("Style", jpnl);
        jtp.addTab("Options", jpnl2);
        jtp.addTab("Software", jpnl3);

        // ���������� ������ � ��������� � ������ �����������.
        jfrm.getContentPane().add(jtp);

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
                new TabbedPaneWithPanels();
            }
        });
    }
}