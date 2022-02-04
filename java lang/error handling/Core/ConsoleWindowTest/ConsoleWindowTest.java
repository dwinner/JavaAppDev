
/**
 * @version 1.23 2007-06-12
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsoleWindowTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                // ��������� ��� ������������ ��������� �������
                ButtonFrame frame = new ButtonFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // �������������� ���������� ���� -- ����� System.out �����
                // ������������� � ����
                ConsoleWindow.init();
            }
        });
    }
}

/**
 * ����� ��� ������ ������
 */
class ButtonFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    ButtonFrame()
    {
        setTitle("ButtonTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ������ � ������

        ButtonPanel panel = new ButtonPanel();
        add(panel);
    }
}

/**
 * ������ � ����� ��������.
 */
class ButtonPanel extends JPanel
{
    ButtonPanel()
    {
        // �������� ������.

        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        // ���������� ������ � ������.

        add(yellowButton);
        add(blueButton);
        add(redButton);

        // �������� �������� ��� ������.

        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);

        // ���������� �������� � ��������.

        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
    }

    /**
     * �������� �� ������� ����� ������.
     */
    private class ColorAction implements ActionListener
    {
        private Color backgroundColor;
        
        ColorAction(Color c)
        {
            backgroundColor = c;
        }

        public void actionPerformed(ActionEvent event)
        {
            System.out.println(event); // ������� � ���������� ����
            setBackground(backgroundColor);
        }
    }
}
