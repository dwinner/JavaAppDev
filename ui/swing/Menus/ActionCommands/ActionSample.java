// ������������ ���������� ������� �������������� Command ��� �������� Swing.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ActionSample extends JFrame
{
    // �������� Swing
    private Action sampleAction;
    private Action exitAction;

    // ����������� ActionSample
    public ActionSample()
    {
        super("Using Actions");

        // �������� ��������� AbstractAction ��� sampleAction
        sampleAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // ����������� ���������, ������������ �� ����� sampleAction
                JOptionPane.showMessageDialog(ActionSample.this, "The sampleAction was invoked");
                // ���������� �������� exitAction � ��������� ��������������� GUI.
                exitAction.setEnabled(true);
            }

        };

        // ������� ����� ��������
        sampleAction.putValue(Action.NAME, "Sample Action");

        // ��������� ������ ��� ��������
        sampleAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("images/help.gif")));

        // ������� ������ �������� ���������
        sampleAction.putValue(Action.SHORT_DESCRIPTION, "A Sample Action");

        // ������� "�������" ������� ��� ��������
        sampleAction.putValue(Action.MNEMONIC_KEY, new Integer('S'));

        // �������� ��������� AbstractAction ��� �������� exitAction
        exitAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ����������� ���������, ������������ �� ����� exitAction
                JOptionPane.showMessageDialog(ActionSample.this, "The exitAction was invoked");
                System.exit(0);
            }

        };

        // ��������� ����� ��������
        exitAction.putValue(Action.NAME, "Exit");

        // ������� ������ ��� ��������
        exitAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("images/exit.gif")));

        // ������� ������ �������� ���������
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit Application");

        // ������� "�������" ������� ��� ��������
        exitAction.putValue(Action.MNEMONIC_KEY, new Integer('x'));

        // ������ �������� exitAction � ������������ ��������������� ����������� GUI
        exitAction.setEnabled(false);

        // �������� ���� File
        JMenu fileMenu = new JMenu("File");

        // ���������� sampleAction � exitAction � ���� File ��� �������� ���������
        // ���� JMenuItem ��� ������� ��������
        fileMenu.add(sampleAction);
        fileMenu.add(exitAction);

        fileMenu.setMnemonic('F');

        // �������� ������� JMenuBar � ���������� � ���� File
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // �������� ������ JToolBar
        JToolBar toolBar = new JToolBar();

        // ���������� sampleAction � exitAction � ������ JToolBar ��� ��������
        // ������ JButtons ��� ������� �� ��������
        toolBar.add(sampleAction);
        toolBar.add(exitAction);

        // �������� ������� JButton � ������� � �������� �������� sampleAction
        JButton sampleButton = new JButton();
        sampleButton.setAction(sampleAction);
        // �������� ������� JButton � ������� � �������� �������� exitAction
        JButton exitButton = new JButton(exitAction);

        // ���������� ������ JButtons �� ������ JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sampleButton);
        buttonPanel.add(exitButton);
        // ���������� ������� toolBar � buttonPanel � ������ JFrame
        Container container = getContentPane();
        container.add(toolBar, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
    }

    // ����������
    public static void main(String[] args)
    {
        ActionSample sample = new ActionSample();
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sample.pack();
        sample.setVisible(true);
    }

}