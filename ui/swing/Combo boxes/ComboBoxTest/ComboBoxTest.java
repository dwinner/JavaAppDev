import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class ComboBoxTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ComboBoxFrame frame = new ComboBoxFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ����� � ��������� ������ � ���������� �������, ��������������� ��� ������ ����������.
 */
class ComboBoxFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    private JComboBox faceCombo;
    private JLabel label;
    private static final int DEFAULT_SIZE = 12;
   
    public ComboBoxFrame()
    {
        setTitle("ComboBoxTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ��������� �����.

        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
        add(label, BorderLayout.CENTER);

        // �������� ����������� ������ � ��������� � ���� �������, ��������������� �����������.

        faceCombo = new JComboBox();
        faceCombo.setEditable(true);
        faceCombo.addItem("Serif");
        faceCombo.addItem("SansSerif");
        faceCombo.addItem("Monospaced");
        faceCombo.addItem("Dialog");
        faceCombo.addItem("DialogInput");

        // ��������� �������, ��������� � ���������� �������,
        // �������� �����, ������� ������������ �����.

        faceCombo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                label.setFont(
                    new Font((String) faceCombo.getSelectedItem(),
                    Font.PLAIN,
                    DEFAULT_SIZE)
                );
            }
        });

        // ��������� ����������� ������ � ������ ������
        // (������ ������������� � ������ ����� ������).

        JPanel comboPanel = new JPanel();
        comboPanel.add(faceCombo);
        add(comboPanel, BorderLayout.SOUTH);
    }
}
