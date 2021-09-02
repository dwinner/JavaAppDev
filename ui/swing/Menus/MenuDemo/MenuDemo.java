// �������� ������� ������ ����.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MenuDemo implements ActionListener
{
    private JLabel jlab;

    public MenuDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Menu Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(220, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, � ������� ������� ������������ ���������� � ������ ������������.
        jlab = new JLabel();

        // �������� ������ ����.
        JMenuBar jmb = new JMenuBar();

        // �������� ���� File.
        JMenu jmFile = new JMenu("File");

        // ���������� ������������� ����������� � ������ �������� ������� � ���� File.
        // ������ � ���� File ����� �������� � ������� ������� <F>
        jmFile.setMnemonic(KeyEvent.VK_F);

        // �������� �������, ������� ����� ���������� � ������� ���� File.
        // � ������ ������ � �������� �������������� ����������� ������������ ������
        // ����� ����� ������. ��� �������� ������� ������������ �� �� �������, �������
        // � ���������� � �������� <Ctrl>
        JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiClose = new JMenuItem("Close", KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        // ��������� ������� � ������ ���� File.
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        // �������� ���� Options.
        JMenu jmOptions = new JMenu("Options");

        // �������� ������� Colors.
        JMenu jmColors = new JMenu("Colors");
        JMenuItem jmiRed = new JMenuItem("Red");
        JMenuItem jmiGreen = new JMenuItem("Green");
        JMenuItem jmiBlue = new JMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);

        // �������� ������� Priority.
        JMenu jmPriority = new JMenu("Priority");
        JMenuItem jmiHigh = new JMenuItem("High");
        JMenuItem jmiLow = new JMenuItem("Low");
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);

        // ��������� ���� Priority � ������ ���� Options.
        jmOptions.add(jmPriority);

        // �������� ������ Reset � ��������� ��� � ���� Options.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // ���������� ���� Options �� ������� ����.
        jmb.add(jmOptions);

        // �������� ���� Help � ���������� ��� �� ������� ����.
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        // ���������� ����������� ������� � �������� ����.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
        jmiAbout.addActionListener(this);

        // ��������� ����� �� ������ �����������.
        jfrm.getContentPane().add(jlab);

        // ��������� ���� � ������ ������.
        jfrm.setJMenuBar(jmb);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ��������� ������� �������� ��� ������� ����.
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // ��������� ������� ��������, ��������������� ���������� ������.
        String comStr = ae.getActionCommand();
        // ���� ������������ ������ ����� Exit ���� File ���������� ��������� �����������.
        if (comStr.equals("Exit"))
        {
            System.exit(0);
        }
        // � ��������� ������ ������������ ���������� � ������ ������������.
        jlab.setText(comStr + " Selected");
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MenuDemo();
            }

        });
    }

}