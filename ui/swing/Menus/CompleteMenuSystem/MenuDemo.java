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
    private ViewAction fullScreen;
    private ViewAction normal;
    private ViewAction thumbNail;

    public MenuDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Complete Menu Demo");
        // � ������ ������ ������������ ��������� ���������� �� ���������.
        // ��������� ��������� �������� ������.
        jfrm.setSize(640, 480);
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
        // �������� �������� ��� ���������� ���� � ������� ������������ View.
        makeViewsActions();
        // �������� ������ ������������.
        makeToolBar();
        // �������� ���� Options.
        makeOptionsMenu();
        // �������� ���� Help.
        makeHelpMenu();
        // �������� ���� View
        makeViewMenu();
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
     * ����� �������� ��� ���� View � ����������� ������ � ������ ������������
     */
    private class ViewAction extends AbstractAction
    {
        ViewAction(String name, int mnem, int accel, String tTip)
        {
            super(name);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_MASK));
            this.putValue(Action.MNEMONIC_KEY, new Integer(mnem));
            this.putValue(Action.SHORT_DESCRIPTION, tTip);
        }

        /**
         * ��������� �������, ��������� � ������� ������������ ��� View � ���� Debug.
         * <p/>
         * @param ae
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            String comStr = ae.getActionCommand();
            jlab.setText(comStr + " Selected");
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

    // �������� ���� Options.
    private void makeOptionsMenu()
    {
        JMenu jmOptions = new JMenu("Options");
        jmOptions.setMnemonic(KeyEvent.VK_O);
        // �������� ������� Colors.
        JMenu jmColors = new JMenu("Colors");
        jmColors.setMnemonic(KeyEvent.VK_L);
        // ������������� ������� ����� ��� ������ �����. ������ ������
        // ��������� ������������ �������� ��������� ������.
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        jmiRed.setMnemonic(KeyEvent.VK_R);
        jmiRed.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        jmiGreen.setMnemonic(KeyEvent.VK_G);
        jmiGreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
        jmiBlue.setMnemonic(KeyEvent.VK_B);
        jmiBlue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
        // ��������� ������� � ������ ���� Colors.
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);
        // �������� ������� Priority.
        JMenu jmPriority = new JMenu("Priority");
        jmPriority.setMnemonic(KeyEvent.VK_P);
        // ��� ������ ������� ����������� ������������� �����. ��������� �������������
        // ������� ������� ���� ������������ �����, ����� �������� �����������, � �����
        // �������� � ������ ������ ������� ������ ���� ��������. ��������, ��� ������
        // High ���������� �������.
        JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
        jmiHigh.setMnemonic(KeyEvent.VK_I);
        jmiHigh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
        jmiLow.setMnemonic(KeyEvent.VK_L);
        jmiLow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
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
        jmDebug.setMnemonic(KeyEvent.VK_D);
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);
        // ��������� ������� � ������ ���� Debug.
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);
        // �������� ������ ���� Reset.
        JMenuItem jmiReset = new JMenuItem("Reset", KeyEvent.VK_T);
        jmiReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
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

    /**
     * �������� ���� View
     */
    private void makeViewMenu()
    {
        JMenu jmView = new JMenu("View");
        jmView.setMnemonic(KeyEvent.VK_V);
        JMenuItem jmiFullScreen = new JMenuItem(fullScreen);
        JMenuItem jmiNormalScreen = new JMenuItem(normal);
        JMenuItem jmiThumbNailScreen = new JMenuItem(thumbNail);
        // ��������� ������� � ������ ���� View
        jmView.add(jmiFullScreen);
        jmView.add(jmiNormalScreen);
        jmView.add(jmiThumbNailScreen);
        // ���������� ���� View �� ������� ����.
        jmb.add(jmView);
    }

    private void makeHelpMenu()
    {
        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic(KeyEvent.VK_H);
        // ���������� ����������� � ������� ���� About.
        ImageIcon icon = new ImageIcon("AboutIcon.gif");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setMnemonic(KeyEvent.VK_A);
        jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        jmiAbout.setToolTipText("Info about the MenuDemo program.");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
        // ����������� ����������� ������� ��� About.
        jmiAbout.addActionListener(this);
    }

    /**
     * ������������ �������� ��� ���� Debug � ������ ������������.
     */
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

    /**
     * ������������ �������� ��� ���� View � ������ ������������ ��� View.
     */
    private void makeViewsActions()
    {
        // �������� ��������.
        fullScreen = new ViewAction("Full Screen", KeyEvent.VK_0, KeyEvent.VK_0, "Full screen resolution");
        normal = new ViewAction("Normal Screen", KeyEvent.VK_1, KeyEvent.VK_1, "Normal screen resolution");
        thumbNail = new ViewAction("Thumbnail Screen", KeyEvent.VK_2, KeyEvent.VK_2, "Thumbnail screen resolution");
    }

    /**
     * �������� ������ ������������ Debug.
     */
    private void makeToolBar()
    {
        // �������� ������ ������ ������������ � �������������� ��������.
        JButton jbtnSet = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);
        JButton jbtnFullScreen = new JButton(fullScreen);
        JButton jbtnNormalScreen = new JButton(normal);
        JButton jbtnThumbNailScreen = new JButton(thumbNail);
        // �������� ������ ������������ Debug.
        jtb = new JToolBar("Breakpoints");
        // ��������� ������ � ������ ������ ������������.
        jtb.add(jbtnSet);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);
        jtb.add(jbtnFullScreen);
        jtb.add(jbtnNormalScreen);
        jtb.add(jbtnThumbNailScreen);
    }

    /**
     * �������� ������������ ���� Edit.
     */
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