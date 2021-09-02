// ������������� ������� ��������� MenuDemo.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MenuDemo implements ActionListener
{
    private JLabel jlab;
    private JMenuBar jmb;
    private JToolBar jtb;
    private JPopupMenu jpu;
    private DebugAction setAct;
    private DebugAction clearAct;
    private DebugAction resumeAct;

    public MenuDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Complete Menu Demo");
        // � ������ ������ ������������ ��������� ���������� �� ���������.
        // ��������� ��������� �������� ������.
        jfrm.setSize(360, 200);
        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� �����, � ������� ������� ������������ ���������� � ������ ������������
        jlab = new JLabel();
        // �������� ������ ����.
        jmb = new JMenuBar();
        // �������� ���� File.
        makeFileMenu();
        // �������� �������� ��� ���������� ���� � ������� ������������ Debug.
        makeActions();
        // �������� ������ ������������.
        makeToolBar();
        // �������� ���� Options.
        makeOptionsMenu();
        // �������� ���� Help.
        makeHelpMenu();
        // �������� ������������ ���� Edit.
        makeEditPUMenu();

        // �������� ����������� ������� ����, � ������� ����� ����������� �������,
        // ����������� ����� ������������ ����.
        jfrm.getContentPane().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                if (me.isPopupTrigger())
                {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
                if (me.isPopupTrigger())
                {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }

        });

        // ���������� ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlab, SwingConstants.CENTER);
        // ���������� ������ ������������ � �������� ������� ������ �����������.
        jfrm.getContentPane().add(jtb, BorderLayout.NORTH);
        // ��������� ������ ���� � ������ ������.
        jfrm.setJMenuBar(jmb);
        // ����������� ������.
        jfrm.setVisible(true);
    }

    /**
     * ��������� ������� ��������, ��������� � ������� ����. ������ ���������� �� ������������
     * �������, ������������ ���� Debug � ����������� ������� ������������.
     * <p/>
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // ��������� ������� ��������, ������������ ����� ������������.
        String comStr = ae.getActionCommand();
        // ���� ������������ �������� ����� ���� Exit, ���������� ��������� �����������.
        if (comStr.equals("Exit"))
        {
            System.exit(0);
        }
        // � ��������� ������ �� ������ ������������ ���������� � ������ ������������.
        jlab.setText(comStr + " Selected");
    }

    /**
     * ����� �������� ��� ���� Debug � ����������� ������ ������������.
     */
    private class DebugAction extends AbstractAction
    {
        DebugAction(String name, Icon image, int mnem, int accel, String tTip)
        {
            super(name, image);
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_MASK));
            this.putValue(MNEMONIC_KEY, new Integer(mnem));
            this.putValue(SHORT_DESCRIPTION, tTip);
        }

        /**
         * ��������� �������, ��������� � ������� ������������ � ���� Debug.
         * <p/>
         * @param ae
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            String comStr = ae.getActionCommand();
            jlab.setText(comStr + " Selected");
            // ������������ ��������� ��� Set Breakpoint � Clear Breakpoint.
            switch (comStr)
            {
                case "Set Breakpoint":
                    clearAct.setEnabled(true);
                    setAct.setEnabled(false);
                    break;
                case "Clear Breakpoint":
                    clearAct.setEnabled(false);
                    setAct.setEnabled(true);
                    break;
            }
        }

    }

    /**
     * �������� ���� File � ����������� ��� ���� ������������� ����������� � ������ ��������
     * �������.
     */
    private void makeFileMenu()
    {
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiClose = new JMenuItem("Close", KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        // ���������...
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        // ���������� ������������ ������� �������� � ���� File.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
    }

    /**
     * �������� ���� Options.
     */
    private void makeOptionsMenu()
    {
        JMenu jmOptions = new JMenu("Options");
        // �������� ������� Colors.
        JMenu jmColors = new JMenu("Colors");
        // ������������� ������� ����� ��� ������ �����. ������ ������
        // ��������� ������������ �������� ��������� ������.
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
        // ��������� ������� � ������ ���� Colors.
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);
        // �������� ������� Priority.
        JMenu jmPriority = new JMenu("Priority");
        // ��� ������ ������� ����������� ������������� �����. ��������� �������������
        // ������� ������� ���� ������������ �����, ����� �������� �����������, � �����
        // �������� � ������ ������ ������� ������ ���� ��������. ��������, ��� ������
        // High ���������� �������.
        JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
        // ��������� ������� � ������ ���� Priority.
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);
        // ����������� ������ ������������� ����� � ������
        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);
        // �������� ������� Debug, ��������� � ������ ���� Options. 
        // ��� �������� ������� ���� ������������ ��������.
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);
        // ��������� ������� � ������ ���� Debug.
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);
        // �������� ������ ���� Reset.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);
        // ���������� ���� Options �� ������� ����.
        jmb.add(jmOptions);
        // ���������� ������������ ������� �������� � ���� Options.
        // ��� ����������� �� ������������ ������� ���� Debug.
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
    }

    private void makeHelpMenu()
    {
        JMenu jmHelp = new JMenu("Help");
        // ���������� ����������� � ������� ���� About.
        ImageIcon icon = new ImageIcon("AboutIcon.gif");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setToolTipText("Info about the MenuDemo program.");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
        // ����������� ����������� ������� ��� About.
        jmiAbout.addActionListener(this);
    }

    // ������������ �������� ��� ���� Debug � ������ ������������.
    private void makeActions()
    {
        // �������� ����������� ��� ��������.
        ImageIcon setIcon = new ImageIcon("setBP.gif");
        ImageIcon clearIcon = new ImageIcon("clearBP.gif");
        ImageIcon resumeIcon = new ImageIcon("resume.gif");
        // �������� ��������.
        setAct = new DebugAction("Set Breakpoint", setIcon, KeyEvent.VK_S, KeyEvent.VK_B, "Set a breakpoint.");
        clearAct = new DebugAction("Clear Breakpoint", clearIcon, KeyEvent.VK_C, KeyEvent.VK_L, "Clear a breakpoint.");
        resumeAct = new DebugAction("Resume", resumeIcon, KeyEvent.VK_R, KeyEvent.VK_R, "Resume execution after breakpoint.");
        // ������������� ��������� � Clear Breakpoint ���������.
        clearAct.setEnabled(false);
    }

    // �������� ������ ������������ Debug.
    private void makeToolBar()
    {
        // �������� ������ ������ ������������ � �������������� ��������.
        JButton jbtnSet = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);
        // �������� ������ ������������ Debug.
        jtb = new JToolBar("Breakpoints");
        // ��������� ������ � ������ ������ ������������.
        jtb.add(jbtnSet);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);
    }

    // �������� ������������ ���� Edit.
    private void makeEditPUMenu()
    {
        jpu = new JPopupMenu();
        // �������� ������� ������������ ����
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");
        // ��������� ������� � ������ ������������ ����.
        jpu.add(jmiCut);
        jpu.add(jmiCopy);
        jpu.add(jmiPaste);
        // ���������� ������������ ������� � �������� ������������ ���� Edit.
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
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